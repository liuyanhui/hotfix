package service;

import com.sun.tools.attach.VirtualMachine;
import org.bruce.entity.User;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class AppService {
    public static void main(String[] args) throws Exception {
        while (true) {
            //替换前，打印出 firstName.lastName
            //被替换后，打印lastName.firstName
            System.out.println(new User("Michael", "Jordan").getName());
            Thread.sleep(5000);
            //attach到自己
//            attach("/Users/liuyanhui/Documents/project/hotfix/hotfixclient/target/classes/org/bruce/entity/User.class");
        }
    }

    public static void attach(String file) throws Exception {
        //注意，是jre的bin目录，不是jdk的bin目录
        //VirtualMachine need the attach.dll in the jre of the JDK.
//        System.setProperty("java.library.path",
//                "/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home/lib/tools.jar");
//        Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
//        fieldSysPath.setAccessible(true);
//        fieldSysPath.set(null, null);
        //目标进程的进程id -- 记得改成正确的数字
        VirtualMachine vm = VirtualMachine.attach(getCurrentThreadIDStr());

        //参数1：代理jar的位置
        //参数2， 传递给代理的参数
        vm.loadAgent("/Users/liuyanhui/Documents/project/hotfix/hotfixagentmain/target/hotfixagentmain-1.1-SNAPSHOT.jar", file);
    }

    public static String getCurrentThreadIDStr() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String threadId = runtimeMXBean.getName().split("@")[0];
        System.out.println("当前线程id=" + threadId);
        return threadId;
    }
}
