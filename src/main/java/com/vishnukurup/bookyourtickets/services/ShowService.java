package com.vishnukurup.bookyourtickets.services;

import com.vishnukurup.bookyourtickets.models.Show;
import com.vishnukurup.bookyourtickets.repositories.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    private ShowRepo showRepo;
    @Autowired
    private TicketService ticketService;

    public List<Show> getShows(){
        return showRepo.findAll();
    }

    //returns the exact show given name , time and date of the movie else returns null if not found
    public Show getShowWithmovieNameTimeDate(String movieName, String movieTime, Date movieDate){
        List<Show> shows = showRepo.findByMovieName(movieName);
//        System.out.println(shows.get(0).toString());
        if(shows.size() == 0){
            return null;
        }else {
            for (Show show : shows) {
                if (show.getShowDate().equals(movieDate) &&
                        show.getShowTime().equals(movieTime))
                    return show;
            }
        }
        return null;
    }
    public String addNewShow(Show newShow) {
        //checking if the show already exists
        Show exists = this.getShowWithmovieNameTimeDate(newShow.getMovieName(),newShow.getShowTime(),
                newShow.getShowDate());
        //does'nt exist->save
        if(exists == null){
            showRepo.save(newShow);
            //now get the added show to take the generated id from the db
            Show TargetShow =
                    this.getShowWithmovieNameTimeDate(newShow.getMovieName(),newShow.getShowTime(),
                            newShow.getShowDate());
            //add tickets
            ticketService.addTicketsFornewShow(TargetShow.getId());

            return "show added";
        }
        //else return show already exists
        else
            return "same show already exists "+exists.toString();
    }

    public String updateShowDetails(Show updatedShow) {
        showRepo.save(updatedShow);
        return "updated the show"+showRepo.findById(updatedShow.getId()).get();
    }

    public String deleteShow(Integer id) {
        Show deletedShow = showRepo.findById(id).get();
        //first delete the tickets as they are liked to the show using foreign key
        ticketService.deleteShowTickets(id);
        showRepo.deleteById(id);
        return "The show "+deletedShow.toString()+" has been deleted.";
    }
}
