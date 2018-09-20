package pl.coderslab.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.provider.model.Event;
import pl.coderslab.provider.model.Team;
import pl.coderslab.provider.repository.EventRepository;
import pl.coderslab.provider.repository.TeamRepository;
import pl.coderslab.provider.service.MadeUpEventService;

@Controller
public class TestController
{
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MadeUpEventService madeUpEventService;

    @Autowired
    EventRepository eventRepository;

    @RequestMapping("/play")
    @ResponseBody
    public String play()
    {
        Event event = eventRepository.findFirstById(new Long(1));
        madeUpEventService.sampleMatch(event);
        return "Hello from the other side";
    }

}
