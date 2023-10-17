package com.vishnukurup.bookyourtickets.services;

import com.vishnukurup.bookyourtickets.models.Show;
import com.vishnukurup.bookyourtickets.models.Ticket;
import com.vishnukurup.bookyourtickets.repositories.TicketRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepo ticketRepo;

    //add ticket normally
    public void addTickets(int showId, List<Ticket> tickets){

        for(Ticket ticket: tickets){
            ticketRepo.save(ticket);
        }
    }

    //gets tickets for a show
    public List<Ticket> getTickets(Integer showId) {
        List<Ticket> myTickets = new ArrayList<>();
        ticketRepo.findByShowId(showId).forEach(myTickets::add);
        return myTickets;
    }

    //adds five tickets numbered A1 to A5 for each new show added
    public void addTicketsFornewShow(Integer id) {
        List<Ticket> newTickets = new ArrayList<>();
        for(int i = 0 ; i < 5 ; i++){
            newTickets.add(new Ticket(null,"A"+(i+1),150,"Not Booked",null));
            newTickets.get(i).setShow(new Show(id,null,null,null));
        }
        ticketRepo.saveAll(newTickets);
    }

    @Transactional
    public void deleteShowTickets(Integer showId) {
        ticketRepo.deleteByShowId(showId);
    }

    public List<Ticket> getAvailableTickets(Integer showId) {
         List<Ticket> availableTickets = ticketRepo.findByShowId(showId);
         for(Ticket available : availableTickets){
             if(available.getStatus().equals("Booked")){
                 availableTickets.remove(available);
             }
         }
         return availableTickets;
    }
}
