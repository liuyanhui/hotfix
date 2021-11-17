package org.bruce.springmvchotfixserver.entity;

import org.springframework.stereotype.Component;

@Component
public class UserSpringImpl implements UserSpring {
    private String firstName = "Leon";

    private String lastName = "Messi";

    public String getName() {
        return "[hotfix @Component-->" + lastName + "." + firstName + "]";
    }

    public String toString() {
        return "[hotfix1 @Component-->UserSpring.toString:firstName=" + lastName + ";lastName=" + firstName + "]";
    }
}
