package com.vishnukurup.bookyourtickets.repositories;

import com.vishnukurup.bookyourtickets.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket,Integer> {
    List<Ticket> findByShowId(Integer showId);

    Integer deleteByShowId(Integer showId);
}
