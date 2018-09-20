package pl.coderslab.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.provider.model.League;
import pl.coderslab.provider.model.Team;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository <Team, Long>
{
    List<Team> findAllByLeague(League league);
}
