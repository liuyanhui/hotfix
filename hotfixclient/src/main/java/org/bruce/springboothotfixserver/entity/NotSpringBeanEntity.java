package org.bruce.springboothotfixserver.entity;

public class NotSpringBeanEntity {
    private int code = 10;
    private String msg = "dirty msg";

    @Override
    public String toString() {
        return "[hotfix not springbean-->NotSpringBeanEntity{" +
                "code=" + code +
                ", msg='" + msg + "}]";
    }
}
