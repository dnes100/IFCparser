/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aalto.controllers;

import static com.aalto.controllers.MainController.logger;
import com.aalto.model.Project;
import com.aalto.repo.ProjectRepoImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {
    
    @Autowired
    private ProjectRepoImpl projectRepo;
    
    
    @RequestMapping(value = "/newProject", method = RequestMethod.POST)
    public Project createProject(@RequestParam Map<String,String> reqParams){
        logger.log(Level.INFO, "log: ProjectController newProject!!");
        logger.log(Level.INFO, reqParams.toString());
        String name = reqParams.get("name");
        
        Project newProject = new Project(name);
        
        Long pid = this.projectRepo.save(newProject);
        Project savedProject = this.projectRepo.find(pid);
        
        return savedProject;
    }
    
    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public Map<Long, Project> getAllProjects(){
        logger.log(Level.INFO, "log: ProjectController projects!!");
        
        
        Map<Long, Project> projects = (HashMap) this.projectRepo.findAll();
        
        return projects;
    }
    
    @RequestMapping(value = "/getProjectByPid/{pidString}", method = RequestMethod.GET)
    public Project getProjectByPid(@PathVariable String pidString){
        logger.log(Level.INFO, "log: ProjectController getProjectByPid !!");
        Long pid = Long.valueOf(pidString);
        Project project = this.projectRepo.find(pid);
        return project;
    }
    
    @RequestMapping(value = "/removeProject", method = RequestMethod.POST)
    public Long removeProject(@RequestParam Map<String, String> reqParams){
        logger.log(Level.INFO, "log: ProjectController removeProject !!");
        
        String pidString = (String)reqParams.get("pid");
        Long pid = Long.valueOf(pidString);
        Long projects = this.projectRepo.delete(pid);
        
        return projects;
    }
    
}
