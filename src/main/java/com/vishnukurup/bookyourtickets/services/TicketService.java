package com.vishnukurup.bookyourtickets.services;

import com.vishnukurup.bookyourtickets.models.Show;
import com.vishnukurup.bookyourtickets.models.Ticket;
import com.vishnukurup.bookyourtickets.models.User;
import com.vishnukurup.bookyourtickets.repositories.TicketRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
            newTickets.add(new Ticket(null,"A"+(i+1),150,null,null));
            newTickets.get(i).setShow(new Show(id,null,null,null));
        }
        ticketRepo.saveAll(newTickets);
    }

    //method to delete tickets
    @Transactional//is used in methods whose queries are grouped into a transaction
    public void deleteShowTickets(Integer showId) {
        ticketRepo.deleteByShowId(showId);
    }

    //gets non-booked tickets for a show
    public List<Ticket> getAvailableTickets(Integer showId) {
        //first find all the tickets of that show
        List<Ticket> availableTickets = ticketRepo.findByShowId(showId);
         Iterator<Ticket> myIterator = availableTickets.listIterator();
         //remove the tickets which already booked by a user
         while(myIterator.hasNext()){
             if(myIterator.next().getUser()!=null)
                 myIterator.remove();
         }
         return availableTickets;
    }

    public String bookTickets(List<String> seatNos, Integer showId, String username) {
        //first get the available seats
        List<Ticket> availabe =  this.getAvailableTickets(showId);
        //if no seats available
        if(availabe.isEmpty()){
            return "no seats available for this show.";
        }
        //to store the booked and non booked seats to display to the user
        String booked = "";
        String nonBooked = "";

        for(Ticket ticket:availabe){
            //if the seat no sent by the user is free the book it with the username
            if(seatNos.contains(ticket.getSeatNo())){
                ticket.setUser(new User(username,null,null,null,null));
                ticketRepo.save(ticket);
                seatNos.remove(ticket.getSeatNo());
                //save that to the booked message
                booked += ticket.getSeatNo()+",";
            }
        }
        for(String seatNo : seatNos){
            //adding the remaining seats to the non booked message
            nonBooked += seatNo+",";
        }
        return "Booked Seats:"+booked+"\nAlready Reserved Seats:"+nonBooked;
    }

    public void updateTicket(Ticket ticket){
        ticketRepo.save(ticket);
    }
}
