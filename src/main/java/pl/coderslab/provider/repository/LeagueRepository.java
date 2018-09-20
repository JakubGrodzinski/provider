package pl.coderslab.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.provider.model.League;

public interface LeagueRepository extends JpaRepository<League, Long>
{

}
