package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {
    /*
    redis模板对象，我们要通过它来操作redis
     */
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @ResponseBody
    @RequestMapping("/hello")
    public String test(){

        return "hello";
    }
    /*
    操作String类型  opsForValue()
     */
    @ResponseBody//返回的数据会被解析为JSON格式
    @RequestMapping("/setString")
    public String setString(String name,String value){
        //opsForValue() 操作String类型
        redisTemplate.opsForValue().set(name,value);
        return  redisTemplate.opsForValue().get(name);

    }
    @ResponseBody
    @RequestMapping("/setList")
    public List<String> setList(){

        redisTemplate.opsForList().leftPush("age","22");
        List<String> age = redisTemplate.opsForList().range("age", 0, -1);
        return age;
    }
    /*
    操作hash
     */
    @ResponseBody
    @RequestMapping("/setHash")
    public Object setHash(){
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put("hash","address","重庆");
        Object o = hash.get("hash", "address");
        return o;

    }
}
