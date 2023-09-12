package adeo.leroymerlin.cdp;

import adeo.leroymerlin.cdp.Event.Event;
import adeo.leroymerlin.cdp.Event.EventRepository;
import adeo.leroymerlin.cdp.Event.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@SpringBootTest
public class AdeoLeroyMerlinCDPRecruitmentApplicationTest {

    @Autowired
    private EventService eventService;

    @MockBean
    private EventRepository repository;

    @Test
    void contextLoads() {
    }

    @Test
    public void getEvents(){
        Event event = new Event();
        event.setTitle("Event Title");
        event.setComment("A good event");
        when(repository.findAllBy()).thenReturn(Stream.of(event).collect(Collectors.toList()));

        assertFalse(eventService.getEvents().isEmpty());
    }
}
