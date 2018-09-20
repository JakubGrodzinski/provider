package pl.coderslab.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.provider.model.Event;
import pl.coderslab.provider.model.League;
import pl.coderslab.provider.model.Team;
import pl.coderslab.provider.repository.EventRepository;
import pl.coderslab.provider.repository.LeagueRepository;
import pl.coderslab.provider.repository.TeamRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class MadeUpEventService
{
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    LeagueRepository leagueRepository;

    public Set<Event> pairTeams (League league)
    {
        Set<Event> result = new HashSet<>();
        List<Team> allTeamsInLeague =  teamRepository.findAllByLeague(league);
        while (!allTeamsInLeague.isEmpty())
        {
            Event event = new Event();
            int team1 = new Random().nextInt(allTeamsInLeague.size());
            Team team1t = allTeamsInLeague.get(team1);
            allTeamsInLeague.remove(team1);
            int team2 = new Random().nextInt(allTeamsInLeague.size());
            Team team2t = allTeamsInLeague.get(team2);
            allTeamsInLeague.remove(team2);
            event.setTeam1(team1t);
            event.setTeam2(team2t);
            result.add(event);
        }
        return result;
    }

    @Async
    public void sampleMatch (Event event)
    {
        Team team1 = event.getTeam1();
        Team team2 = event.getTeam2();
        Team attackingTeam;
        Team defendingTeam;
        int attack1 = team1.getAttackPotential();
        int attack2 = team2.getAttackPotential();
        LocalDateTime counterDate = LocalDateTime.now();
        while ( counterDate.isAfter(  event.getBeginning().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() ) &&
                counterDate.isBefore( event.getEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() ) )
        {
            int whoAttacks = new Random().nextInt(attack1 + attack2);
            if(whoAttacks < attack1)
            {
                attackingTeam = team1;
                defendingTeam = team2;
            }
            else
            {
                attackingTeam = team2;
                defendingTeam = team1;
            }
            int goodSituation = new Random().nextInt(attackingTeam.getAttackPotential() + defendingTeam.getDefencePotential());
            if (goodSituation < attackingTeam.getAttackPotential())
            {
                System.out.println(attackingTeam.getName() + " has good situation");
                int isGoal = new Random().nextInt(attackingTeam.getAttackPotential());
                if (isGoal < attackingTeam.getAttackPotential() - defendingTeam.getDefencePotential()/1.5)
                {
                    System.out.println(attackingTeam.getName() + " scored");
                    if (attackingTeam.equals(team1))
                    {
                        event.setTeam1score(event.getTeam1score() + 1);
                    }
                    else
                    {
                        event.setTeam2score(event.getTeam2score() + 1);
                    }
                    eventRepository.save(event);
                }
            }
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            // sprawa daty
//            counterDate = new Date();
            counterDate = LocalDateTime.now();
        }
        event.setFinished(true);
        eventRepository.save(event);

    }

    public void setTimesEvents(Set<Event> events)
    {
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext())
        {
            Event event = iterator.next();
            Date beginning = new Date();
            Calendar date = Calendar.getInstance();
            long t = date.getTimeInMillis();
            Date end = new Date(t + 10000);
            event.setBeginning(beginning);
            event.setEnd(end);
        }
    }

    @Scheduled(fixedRate = 10000)
    public void playSeries ()
    {
        List<League> leagues = leagueRepository.findAll();
        League league = leagues.get(new Random().nextInt(4));
        Set<Event> events = pairTeams(league);
        setTimesEvents(events);
        for (Event event: events)
        {
            eventRepository.save(event);
        }
        for (Event event: events)
        {
            sampleMatch(event);
        }
    }

}
