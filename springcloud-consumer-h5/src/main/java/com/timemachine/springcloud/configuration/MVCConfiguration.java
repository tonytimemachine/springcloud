package com.timemachine.springcloud.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * MVCConfiguration
 *
 * @author Liuguanglei liugl@ekeyfund.com
 * @create 2017-06-下午1:01
 */
@Configuration
@EnableWebMvc
public class MVCConfiguration extends WebMvcConfigurerAdapter{




    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){

        return new MappingJackson2HttpMessageConverter();
    }


    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter){
        RequestMappingHandlerAdapter requestMappingHandlerAdapter=new RequestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> messageConverters=new ArrayList<>();
        messageConverters.add(mappingJackson2HttpMessageConverter);
        requestMappingHandlerAdapter.setMessageConverters(messageConverters);
        return requestMappingHandlerAdapter;
    }


    @Bean
    @LoadBalanced//开启客户端负载均衡
    public RestTemplate restTemplate(){

     return new RestTemplate();
    }
}
