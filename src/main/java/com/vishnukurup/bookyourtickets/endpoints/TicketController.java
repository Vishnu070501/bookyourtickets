package com.vishnukurup.bookyourtickets.endpoints;

import com.vishnukurup.bookyourtickets.models.Show;
import com.vishnukurup.bookyourtickets.models.Ticket;
import com.vishnukurup.bookyourtickets.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @RequestMapping(method = RequestMethod.POST , value = "/{showId}/addTickets")
    public void saveTicket(@PathVariable Integer showId, @RequestBody List<Ticket> tickets){
        for(Ticket ticket : tickets){
            ticket.setShow(new Show(showId,null,null,null));
        }
        ticketService.addTickets(showId,tickets);
    }
    @RequestMapping(method = RequestMethod.GET , value ="{showId}/getTickets")
    public List<Ticket> getTickets(@PathVariable Integer showId){
        return ticketService.getTickets(showId);
    }

    @RequestMapping( method = RequestMethod.GET, value = "/{showId}/showAvailableTickets")
    public List<Ticket> showAvailableTickets(@PathVariable Integer showId){
        return ticketService.getAvailableTickets(showId);
    }
}
