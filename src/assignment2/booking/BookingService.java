package assignment2.booking;

import assignment2.booking.BookingRepository;
import assignment2.event.EventService;
import assignment2.model.Booking;
import assignment2.model.Event;
import assignment2.screen.ScreenService;

public class BookingService {

    private BookingRepository bookingRepository;

    private ScreenService screenService;

    private EventService eventService;

    public BookingService(BookingRepository bookingRepository, ScreenService screenService, EventService eventService) {
        this.bookingRepository = bookingRepository;
        this.screenService = screenService;
        this.eventService = eventService;
    }

    public Long bookEvent(Long seatId, Long eventId, Long screenId, Long userId) {

        validateEvent(eventId);

        if(screenService.lockSeat(screenId, seatId)) {
            // initiate transaction
            boolean transactionSuccess = true;

            if(transactionSuccess) {

                Booking booking = new Booking(seatId, eventId, userId);
                Long bookingId = bookingRepository.save(booking);
                System.out.printf("%s: Booking %d by user %d was successful\n", Thread.currentThread().getName(), booking.getBookingId(), userId);
                return bookingId;
            } else {
                System.out.println("Booking failed - Transaction failure");
                return null;
            }
        }
        System.out.println(Thread.currentThread().getName() + ": Booking failed because seat was already booked / invalid seat number");
        return null;
    }

    public Booking fetchBooking(Long bookingId) {
        return bookingRepository.get(bookingId);
    }

    public boolean cancelBooking(Long bookingId) {

        // Check if booking is valid
        Booking booking = null;
        if((booking = bookingRepository.get(bookingId)) == null) {
            System.out.println(Thread.currentThread().getName() + ": Booking " + bookingId + " does not exist");
            return false;
        }

        Event event = eventService.getEvent(booking.getEventId());

        if(screenService.unlockSeat(event.getScreenId(), booking.getSeatId())) {
            boolean success = bookingRepository.remove(bookingId);
            if(success) {
                System.out.println(Thread.currentThread().getName() + ": Booking " + bookingId + " successfully cancelled!");
                return true;
            }
        }
        System.out.println(Thread.currentThread().getName() + ": Error: Booking could not get cancelled");
        return false;
    }

    private void validateEvent(Long eventId) {

        if(eventService.getEvent(eventId) == null)
            throw new RuntimeException("Event " + eventId + " not found!");
    }

}
