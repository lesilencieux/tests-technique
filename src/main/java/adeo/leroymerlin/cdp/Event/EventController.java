package adeo.leroymerlin.cdp.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/")
    public List<Event> findEvents() {
        return eventService.getEvents();
    }

    @GetMapping(value = "/search/{query}")
    public List<Event> findEvents(@PathVariable String query) {
        return eventService.getFilteredEvents(query);
    }

    @PutMapping(value = "/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
