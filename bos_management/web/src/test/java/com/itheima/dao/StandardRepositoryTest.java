package com.itheima.dao;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.domain.base.Customer;
import com.itheima.domain.base.Standard;
import com.itheima.dao.base.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StandardRepositoryTest {
    
    @Autowired
    private StandardRepository standardRepository;
    
    @Test
    public void test1(){
        Standard standard = new Standard();
        standard.setMaxLength(300);
        standardRepository.save(standard);
    }
    @Test
    public void test2(){
        standardRepository.delete(1L);
    }
    @Test
    public void test3(){
        Standard standard = new Standard();
        standard.setId(2L);
        standard.setMaxLength(500);
        standardRepository.save(standard);
    }
    @Test
    public void test4(){
        Standard standard = standardRepository.findOne(3L);
        System.out.println(standard);
    }
    
    @Test
    public void test5(){
        List<Standard> list = standardRepository.findAll();
        for (Standard standard2 : list) {
            System.out.println(standard2);
        }
    }
    
}
  
