package com.vishnukurup.bookyourtickets.services;

import com.vishnukurup.bookyourtickets.models.User;
import com.vishnukurup.bookyourtickets.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public String logCheck(String username,String password){


        try{
            User target = userRepo.findById(username).get();
            if(target.getPassword().equals(password))
                return target.toString()+" "+true;
            else
                return  "invalid password";
        }catch(NoSuchElementException e){
            return "invalid username";
        }

    }

    public String addUser(User user) {
        try{
            User exists = userRepo.findById(user.getUsername()).get();
            return "User exists"+user.toString();
        }catch(NoSuchElementException e){
            userRepo.save(user);
            return "Saved";
        }
    }
}
