package com.vishnukurup.bookyourtickets.repositories;

import com.vishnukurup.bookyourtickets.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
}
