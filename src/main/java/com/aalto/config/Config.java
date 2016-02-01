
package com.aalto.config;

import com.aalto.model.Project;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;



@Configuration
@ComponentScan("com.aalto")
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter {
    
    @Bean  
    public UrlBasedViewResolver setupViewResolver() {  
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();  
        resolver.setPrefix("/WEB-INF/jsp/");  
        resolver.setSuffix(".jsp");  
        resolver.setViewClass(JstlView.class);  
        return resolver;  
    } 
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("WEB-INF/resources/");
        registry.addResourceHandler("/jsp/**").addResourceLocations("WEB-INF/jsp/");
    }
    
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    < String, Object >RedisTemplate< String, Object > redisTemplate() {
        final RedisTemplate< String, Object > template =  new RedisTemplate<  >();
        template.setConnectionFactory( jedisConnectionFactory() );
//        template.setKeySerializer( new StringRedisSerializer() );
//          template.setValueSerializer( new GenericToStringSerializer< Object >(Object.class));
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer( new JacksonJsonRedisSerializer< >( Project.class ) );
        return template;
    }
    
    @Bean
    RedisAtomicLong projectCounter() {
        return new RedisAtomicLong("Global:ProjectCounter", redisTemplate().getConnectionFactory());
    }
 
}
