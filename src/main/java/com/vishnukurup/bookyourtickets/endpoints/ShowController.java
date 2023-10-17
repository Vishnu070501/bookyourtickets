package com.vishnukurup.bookyourtickets.endpoints;

import com.vishnukurup.bookyourtickets.models.Show;
import com.vishnukurup.bookyourtickets.models.Ticket;
import com.vishnukurup.bookyourtickets.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShowController {
    @Autowired
    private ShowService showService;

//    @RequestMapping(method = RequestMethod.GET,value="/getShows")
//    public List<Show> getShows(){
//        return showService.getShows();
//    }

    @RequestMapping(method = RequestMethod.POST,value = "/addShow")
    public String addShow(@RequestBody Show newShow){
        return showService.addNewShow(newShow);

    }
    @RequestMapping(method = RequestMethod.PUT,value="/{id}/updateShow")
    public String updateShow(@RequestBody Show updatedShow){
        return showService.updateShowDetails(updatedShow);
    }
    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteShow/{id}")
    public String deleteShow(@PathVariable Integer id){
        return showService.deleteShow(id);
    }


}
