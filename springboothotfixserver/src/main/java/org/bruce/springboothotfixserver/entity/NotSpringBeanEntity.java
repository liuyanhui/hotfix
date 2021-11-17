package org.bruce.springboothotfixserver.entity;

public class NotSpringBeanEntity {
    private int code = -1;
    private String msg = "raw msg";

    @Override
    public String toString() {
        return "NotSpringBeanEntity{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
