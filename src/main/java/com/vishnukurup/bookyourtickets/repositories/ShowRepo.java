package com.vishnukurup.bookyourtickets.repositories;


import com.vishnukurup.bookyourtickets.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowRepo extends JpaRepository<Show,Integer> {
    public List<Show> findByMovieName(String movieName);
}
