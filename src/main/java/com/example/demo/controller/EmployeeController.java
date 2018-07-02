package com.example.demo.controller;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Chao on 2018/7/1.
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    //@RequestMapping(value = "/emp/login" , method = RequestMethod.POST)
    @PostMapping("/emp/login")
    public String login(@RequestParam("username") String username , @RequestParam("password") String password ,
                        Map<String , Object> map , HttpSession session) {
        if ( !StringUtils.isEmpty("username") && "123".equals(password)) {
            session.setAttribute("Emp" , username);
            return "redirect:/main.html";
        }else {
            map.put("errorMessage" , "账号或密码错误...");
            return "login";
        }
    }


    @RequestMapping("/emp/showAll")
    public String showAll(Map<String , Object> map) {
        Collection<Employee> emps = employeeDao.getAll();
        map.put("emps" , emps);

        for (Employee emp : emps) {
            System.out.println(emp);
        }
        System.out.println("--------push--------------");
        return "list";
    }

}
