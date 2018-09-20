package pl.coderslab.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.provider.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>
{
    Event findFirstById(Long id);

}
