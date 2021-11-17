package org.bruce.springboothotfixserver.service;

import org.bruce.springboothotfixserver.attach.AgentAttach;
import org.bruce.springboothotfixserver.attach.BeanManager;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotfix")
public class HotfixService {
    @GetMapping("/attach-component")
    public String attach() throws Exception {
        AgentAttach agentAttach = new AgentAttach("/Users/liuyanhui/Documents/project/hotfix/hotfixclient/target/classes/org/bruce/springboothotfixserver/entity/UserSpringImpl.class");
        agentAttach.attachSpringSingletonBean();
        return "OK";
    }

    @GetMapping("/attach-normal")
    public String attachNotSpring() throws Exception {
        AgentAttach agentAttach = new AgentAttach("/Users/liuyanhui/Documents/project/hotfix/hotfixclient/target/classes/org/bruce/springboothotfixserver/entity/NotSpringBeanEntity.class");
        agentAttach.attachNormal();
        return "OK";
    }


    @GetMapping("/attach-controller")
    public String attachController() throws Exception {
        AgentAttach agentAttach = new AgentAttach("/Users/liuyanhui/Documents/project/hotfix/hotfixclient/target/classes/org/bruce/springboothotfixserver/service/UserService.class");
        agentAttach.attachSpringSingletonBean();
        return "OK";
    }

}
