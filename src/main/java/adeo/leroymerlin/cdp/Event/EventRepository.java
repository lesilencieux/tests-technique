package adeo.leroymerlin.cdp.Event;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface EventRepository extends Repository<Event, Long> {

    List<Event> findAllBy();

    Optional<Event> findById(Long id);

    Event save(Event event);

    void delete(Long eventId);
}
