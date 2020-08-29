package assignment2.model;

public class Booking {

    private Long bookingId;
    private Long seatId;
    private Long eventId;
    private Long userId;

    public Booking(Long seatId, Long eventId, Long userId) {
        this.seatId = seatId;
        this.eventId = eventId;
        this.userId = userId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public Long getEventId() {
        return eventId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", seatId=" + seatId +
                ", eventId=" + eventId +
                ", userId=" + userId +
                '}';
    }
}
