package pl.coderslab.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.coderslab.provider.model.Event;
import pl.coderslab.provider.model.League;
import pl.coderslab.provider.model.Team;
import pl.coderslab.provider.repository.EventRepository;
import pl.coderslab.provider.repository.TeamRepository;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class MadeUpEventService
{
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    EventRepository eventRepository;

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
        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();
        Date afterAHalf = new Date(t + 3 * 60000);
        Date beginning = date.getTime();
        Date counterDate = beginning;
        while (!counterDate.equals(afterAHalf))
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
                    event.setUpdate(new Date());
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
            Calendar dateAfterAction = Calendar.getInstance();
            long s = dateAfterAction.getTimeInMillis();
            counterDate = new Date(s);
        }
    }


}
