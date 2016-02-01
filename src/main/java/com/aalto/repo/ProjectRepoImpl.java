/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aalto.repo;

import com.aalto.model.Project;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepoImpl {

    @Autowired
    private RedisTemplate<String, Project> redisTemplate;

    private static final String PROJECT_KEY = "Project";
    
    @Autowired
    private RedisAtomicLong projectCounter;

//    public ProjectRepoImpl(RedisTemplate<String, Project> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//        this.projectIdCounter = new RedisAtomicLong(PROJECT_COUNTER_KEY, this.redisTemplate.getConnectionFactory());
//    }
    
    
        public ProjectRepoImpl() {

        }

        public RedisAtomicLong getProjectIdCounter() {
            return this.projectCounter;
        }

        public void setProjectIdCounter(RedisAtomicLong projectCounter) {
            this.projectCounter = projectCounter;
        }

	public RedisTemplate<String, Project> getRedisTemplate()
	{
			return redisTemplate;
	}
        
        public void setRedisTemplate(RedisTemplate<String, Project> redisTemplate)
	{
			this.redisTemplate = redisTemplate;
	}

	public Long save(Project project)
	{
            Long pid = this.projectCounter.incrementAndGet();
            project.setPid(pid);
            Logger.getLogger("repo").log(Level.INFO, "Project repo impl save project");
            Logger.getLogger("repo").log(Level.INFO, String.valueOf(project.getPid()));
            this.redisTemplate.opsForHash().put(PROJECT_KEY, project.getPid(), project);
            return pid;
            
	}

	public Project find(Long pid)
	{
		return (Project) this.redisTemplate.opsForHash().get(PROJECT_KEY, pid);
	}

	public Map<Object,Object> findAll()
	{
		return this.redisTemplate.opsForHash().entries(PROJECT_KEY);
	}

	public Long delete(Long pid)
	{
		this.redisTemplate.opsForHash().delete(PROJECT_KEY, pid);
                return pid;
	}
        
}
