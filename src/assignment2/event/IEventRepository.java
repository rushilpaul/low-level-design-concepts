package assignment2.event;

import assignment2.model.Event;
import assignment2.model.PriceRange;

import java.util.Collection;

public interface IEventRepository {

    Collection<Event> getEvents(String genre, PriceRange priceRange);

    Event getById(Long eventId);

    void save(Collection<Event> events);
}
