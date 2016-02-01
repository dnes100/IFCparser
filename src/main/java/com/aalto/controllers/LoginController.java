package com.aalto.controllers;

import static com.aalto.controllers.MainController.logger;
import java.util.Map;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    
    @Autowired 
    private RedisTemplate< String, Object > template;

    private static String USER_KEY = "User:";
    
    public Object getValue( final String key ) {
        return template.opsForValue().get( USER_KEY+key );
    }

    public void setValue( final String key, final String value ) {
        template.opsForValue().set( USER_KEY+key, value );
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(@RequestParam Map<String,String> reqParams){
        logger.log(Level.INFO, "log: LoginController login!!");
        logger.log(Level.INFO, reqParams.toString());
        String username = reqParams.get("username");
        String inputPassword = reqParams.get("password");
        
        boolean isAuthenticated = authenticate(username, inputPassword);
//        boolean isAuthenticated = true
        return isAuthenticated;
    }
    
    private boolean authenticate(String username, String password) {
        String dbPassword = (String) getValue(username);
        logger.log(Level.INFO, dbPassword);
        boolean match = password.equals(dbPassword);
       // match = true;
        return match;
    }
}
