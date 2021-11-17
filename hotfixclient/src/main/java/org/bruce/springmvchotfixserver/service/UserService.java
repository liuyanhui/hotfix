package org.bruce.springmvchotfixserver.service;

import org.bruce.springmvchotfixserver.entity.NotSpringBeanEntity;
import org.bruce.springmvchotfixserver.entity.UserSpring;
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
        return "[hotfix1 @RestController-->" + userSpring.toString() + "   >---<  " + notSpringBeanEntity.toString();
    }
}
