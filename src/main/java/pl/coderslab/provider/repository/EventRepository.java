package pl.coderslab.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.provider.model.Event;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>
{
    Event findFirstById(Long id);

    List<Event> findAllByBeginningBeforeAndFinished(Date beginning, boolean finished);
    List<Event> findAllByEndBetween (Date minusFiveMinutes, Date now);
}
