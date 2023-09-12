package adeo.leroymerlin.cdp.Event;

import adeo.leroymerlin.cdp.Band.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() { return eventRepository.findAllBy(); }

    @Transactional
    public Event updateEvent(Long id, Event event){
        Optional<Event> oldEvent = eventRepository.findById(event.getId());
        Event updatedEvent = oldEvent.get();
        updatedEvent.setNbStars(event.getNbStars());
        updatedEvent.setComment(event.getComment());

        return eventRepository.save(updatedEvent);
    }

    @Transactional
    public void deleteEvent(Long id) { eventRepository.delete(id); }

    public List<Event> getFilteredEvents(String query) {
        List<Event> events = eventRepository.findAllBy();
        // Filter the events list in pure JAVA here
        List<Event> filteredEvents = events.stream()
                                            .filter(e -> e.getBands().stream().anyMatch(
                                                    b -> b.getMembers().stream()
                                                            .anyMatch(m -> m.getName().contains(query))
                                            ))
                .map(e -> this.formatEvent(e))
                .collect(Collectors.toList());
        return filteredEvents;
    }

    private Event formatEvent(Event event){
        event.setTitle(String.format("%s [%d]", event.getTitle(), event.getBands().stream().count()));
        event.getBands().stream().map(b -> this.formatBand(b))
                                    .collect(Collectors.toList());
        return event;
    }

    private Band formatBand(Band band){
        band.setName(String.format("%s [%d]", band.getName(), band.getMembers().stream().count()));
        return  band;
    }
}
