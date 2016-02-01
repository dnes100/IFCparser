package com.aalto.controllers;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    
    static final Logger logger = Logger.getLogger("config");
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map){
        logger.log(Level.INFO, "log: MainController index!!");
         map.put("msg", "Hello Spring 4 Web MVC!");
       return "index";
    }
    
}
