package assignment2;

import assignment2.booking.BookingRepository;
import assignment2.event.impl.EventRepository;
import assignment2.model.Event;
import assignment2.screen.ScreenRepository;
import assignment2.screen.ScreenService;
import assignment2.booking.BookingService;
import assignment2.event.EventService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Tester {

    public static void main(String[] args) throws Exception {

        EventRepository eventRepository = new EventRepository();
        BookingRepository bookingRepository = new BookingRepository();
        ScreenRepository screenRepository = new ScreenRepository();
        EventService eventService = new EventService(eventRepository);
        ScreenService screenService = new ScreenService(screenRepository);
        BookingService bookingService = new BookingService(bookingRepository, screenService, eventService);

        Boolean seats[][] = new Boolean[2][3];
        Arrays.fill(seats[0], true);
        Arrays.fill(seats[1], true);
        screenService.addLayout(seats, 10L);
        screenService.addLayout(seats, 11L);

        List<Event> events = new ArrayList<>();
        Event event = new Event(1L, 10L, "Bahubali", "Comedy", "English", 300, 10);
        Event event2 = new Event(2L, 11L, "Bahubali", "COMEDY", "Tamil", 300, 10);
        events.add(event);
        events.add(event2);
        eventService.addOrUpdateEvents(events);

        Filter filter = new Filter();
        filter.setGenre("comedy");
        eventService.fetchEvents(filter).stream().forEach(x -> System.out.println(x));
        
        Runnable bookerJob = () -> {
            Long bookingId = null;
            try {
                bookingId = bookingService.bookEvent(1L, 1L, 10L, 1L);
                if(bookingId == null)
                    throw new Exception("Booking failed");
                System.out.println("Booking success with ID " + bookingId);
                System.out.println(bookingRepository.get(bookingId));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(bookerJob);
        executorService.submit(bookerJob);
        executorService.submit(bookerJob);
        executorService.submit(bookerJob);
        executorService.submit(bookerJob);
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
