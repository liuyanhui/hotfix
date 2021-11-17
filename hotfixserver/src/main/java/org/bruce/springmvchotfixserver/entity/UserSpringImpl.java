package org.bruce.springmvchotfixserver.entity;

import org.springframework.stereotype.Component;

@Component
public class UserSpringImpl implements UserSpring {
    private String firstName = "Leon";

    private String lastName = "Messi";

    public String getName() {
        return firstName + "." + lastName;
    }

    public String toString() {
        return "[firstName=" + firstName + ";lastName=" + lastName + "]";
    }

}
