package assignment2.booking;

import assignment2.model.Booking;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class BookingRepository {

    private volatile Map<Long, Booking> bookingById = new ConcurrentHashMap<>();

    public Long save(Booking booking) {

        Long bookingId = booking.getBookingId();
        if(bookingId == null)
            booking.setBookingId(bookingId = generateBookingId());
        bookingById.put(booking.getBookingId(), booking);
        return bookingId;
    }

    public Booking get(Long id) {
        return bookingById.get(id);
    }

    public boolean remove(Long id) {
        return bookingById.remove(id) != null;
    }

    private Long generateBookingId() {
        Random random = new Random();
        return random.nextLong();
    }
}
