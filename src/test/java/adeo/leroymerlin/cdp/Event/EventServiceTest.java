package adeo.leroymerlin.cdp.Event;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Any;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EventServiceTest {

    @Test
    void shouldGetEvents() {
        EventRepository eventRepository = mock(EventRepository.class);

        Event event = new Event();
        event.setTitle("Event Title");
        event.setComment("A good event");

        when(eventRepository.findAllBy()).thenReturn(Stream.of(event).collect(Collectors.toList()));
        List<Event> events = eventRepository.findAllBy();

        assertFalse(events.isEmpty());
        assertEquals(events.get(0).getTitle(), "Event Title");
    }

    @Test
    void ShouldUpdateEvent() {
        EventRepository eventRepository = mock(EventRepository.class);

        Event event = new Event();
        event.setTitle("Event Title");
        event.setComment("A good event");

        when(eventRepository.save(any())).thenReturn(event);
        Event updatedEvent = eventRepository.save(event);
        assertEquals(updatedEvent.getTitle(), "Event Title");
        assertEquals(updatedEvent.getComment(), "A good event");
    }

    @Test
    void deleteEvent() {

    }

    @Test
    void getFilteredEvents() {

    }
}