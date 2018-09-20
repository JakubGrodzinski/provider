package pl.coderslab.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.provider.model.Country;
import pl.coderslab.provider.model.Event;
import pl.coderslab.provider.model.League;
import pl.coderslab.provider.model.Team;
import pl.coderslab.provider.repository.CountryRepository;
import pl.coderslab.provider.repository.EventRepository;
import pl.coderslab.provider.repository.LeagueRepository;
import pl.coderslab.provider.repository.TeamRepository;

import java.util.List;
@Service
public class DataService
{
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private EventRepository eventRepository;
    public List<Country> getAllCountries()
    {
        return countryRepository.findAll();
    }

    public List<League> getAllLeagues()
    {
        return leagueRepository.findAll();
    }

    public List<Team> getAllTeams()
    {
        return teamRepository.findAll();
    }
    @Scheduled(fixedRate = 5000)
    public List<Event> getAllEvents()
    {
        return eventRepository.findAll();
    }
}
