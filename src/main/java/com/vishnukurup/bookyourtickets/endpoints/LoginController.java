package com.vishnukurup.bookyourtickets.endpoints;

import com.vishnukurup.bookyourtickets.models.User;
import com.vishnukurup.bookyourtickets.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping(method = RequestMethod.GET ,value = "/login")
    public String login(@Param("username") String username,@Param("password") String password){
        return userService.logCheck(username,password);
    }
    @RequestMapping(method = RequestMethod.POST,value = "/usersignup")
    public String registerUser(@RequestBody User user){
        String message = userService.addUser(user);
        return message;
    }
}
