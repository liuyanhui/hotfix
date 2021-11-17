package client;

import com.sun.tools.attach.VirtualMachine;

public class Client {

    private static String agentPath = "/Users/liuyanhui/Documents/project/hotfix/hotfixagentmain/target/hotfixagentmain-1.1-SNAPSHOT.jar";

    public static void main(String[] args) throws Exception {
        //注意，是jre的bin目录，不是jdk的bin目录
        //VirtualMachine need the attach.dll in the jre of the JDK.
//        System.setProperty("java.library.path",
//                "/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home/lib/tools.jar");
//        Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
//        fieldSysPath.setAccessible(true);
//        fieldSysPath.set(null, null);
        //目标进程的进程id -- 记得改成正确的数字

//        hotfixSpringboot();
//        hotfix();
        hotfixNormal();
    }
    private static void hotfix() throws Exception {
        VirtualMachine vm = VirtualMachine.attach("42645");

        //参数1：代理jar的位置
        //参数2， 传递给代理的参数
        vm.loadAgent(agentPath, "/Users/liuyanhui/Documents/project/hotfix/hotfixclient/target/classes/org/bruce/entity/User.class");
    }

    private static void hotfixNormal() throws Exception {
        VirtualMachine vm = VirtualMachine.attach("43111");

        //参数1：代理jar的位置
        //参数2， 传递给代理的参数
        vm.loadAgent(agentPath, "/Users/liuyanhui/Documents/project/hotfix/hotfixclient/target/classes/org/bruce/springmvchotfixserver/entity/NotSpringBeanEntity.class");
    }

    private static void hotfixSpringboot() throws Exception {
        VirtualMachine vm = VirtualMachine.attach("42601");

        //参数1：代理jar的位置
        //参数2， 传递给代理的参数
        vm.loadAgent(agentPath, "/Users/liuyanhui/Documents/project/hotfix/hotfixclient/target/classes/org/bruce/springmvchotfixserver/entity/UserSpring.class");
    }

}
