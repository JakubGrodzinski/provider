package pl.coderslab.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.provider.model.Country;
import pl.coderslab.provider.model.Event;
import pl.coderslab.provider.model.League;
import pl.coderslab.provider.model.Team;
import pl.coderslab.provider.service.DataService;

import java.util.Date;
import java.util.List;

@RestController
public class DataController
{
    @Autowired
    DataService dataService;

    @RequestMapping("/countries")
    public List<Country> getCountries ()
    {
        return dataService.getAllCountries();
    }

    @RequestMapping("/leagues")
    public List<League> getLeagues ()
    {
        return dataService.getAllLeagues();
    }

    @RequestMapping("/teams")
    public List<Team> getTeams ()
    {
        return dataService.getAllTeams();
    }

    @RequestMapping("/events")
    public List<Event> getEvents ()
    {
        return dataService.getAllEvents();
    }

    @RequestMapping("/ongoing")
    public List<Event> getOngoingEvents ()
    {
        return dataService.getOngoingEvents();
    }

    @RequestMapping("/finished")
    public List<Event> getFinished()
    {
        return dataService.getFinishedEvents();
    }
}
