package org.bruce.springmvchotfixserver.service;

import org.bruce.springmvchotfixserver.attach.AgentAttach;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotfix")
public class HotfixService {
    @GetMapping("/attach-component")
    public String attach() throws Exception {
        AgentAttach agentAttach = new AgentAttach("/Users/liuyanhui/Documents/project/hotfix/hotfixclient/target/classes/org/bruce/springmvchotfixserver/entity/UserSpringImpl.class");
        agentAttach.attachNormal();
        return "OK";
    }

    @GetMapping("/attach-normal")
    public String attachNotSpring() throws Exception {
//        AgentAttach agentAttach = new AgentAttach("/Users/liuyanhui/Documents/project/hotfix/hotfixclient/target/classes/org/bruce/springmvchotfixserver/entity/NotSpringBeanEntity.class");
        AgentAttach agentAttach = new AgentAttach("/Users/liuyanhui/Documents/project/hotfix/hotfixclient/target/classes/org/bruce/springmvchotfixserver/entity/NotSpringBeanEntity.class","","");
        agentAttach.attachNormal();
        return "OK";
    }


    @GetMapping("/attach-controller")
    public String attachController() throws Exception {
        AgentAttach agentAttach = new AgentAttach("/Users/liuyanhui/Documents/project/hotfix/hotfixclient/target/classes/org/bruce/springmvchotfixserver/service/UserService.class");
        agentAttach.attachNormal();
        return "OK";
    }

}
