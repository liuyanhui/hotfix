package org.bruce.springboothotfixserver.service;

import org.bruce.springboothotfixserver.entity.NotSpringBeanEntity;
import org.bruce.springboothotfixserver.entity.UserSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService {
    @Autowired
    private UserSpring userSpring;

    @GetMapping("/user")
    public String getUserSpring() {
        NotSpringBeanEntity notSpringBeanEntity = new NotSpringBeanEntity();
        return "[hotfix1 @RestController-->" + userSpring.getName() + "   >---<  " + notSpringBeanEntity.toString();
    }
}
