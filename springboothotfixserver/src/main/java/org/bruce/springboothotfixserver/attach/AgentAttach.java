package org.bruce.springboothotfixserver.attach;

import com.sun.tools.attach.VirtualMachine;
import org.bruce.springboothotfixserver.util.ClassReadUtil;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class AgentAttach {

    private String beanName;
    private String className;
    private String filePath;
    private final String AGENT_FILE = "/Users/liuyanhui/Documents/project/hotfix/hotfixagentmain/target/hotfixagentmain-1.1-SNAPSHOT.jar";

    public AgentAttach(String filePath) {
        this.filePath = filePath;
        try {
            this.className = ClassReadUtil.getClassNameFromFile(filePath);
            this.beanName = ClassReadUtil.getDefaultBeanNameFromClassName(className);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void attachSpringSingletonBean() throws Exception {
        attachNormal();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //是否修改成beandefinition？貌似不可beanFactory.registerSingleton()方法中需要的是bean，而且其中singletonObjects.get()返回的就是bean。
        Class bean = classLoader.loadClass(className);
        GenericApplicationContext context = (GenericApplicationContext) BeanManager.getContext();
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        // 判断是否是springbean
        if (beanFactory.isFactoryBean(beanName)) {
            beanFactory.removeBeanDefinition(beanName);
//            beanFactory.destroySingleton(beanName);//把上面这行替换了也可以正确执行
            beanFactory.registerSingleton(beanName, bean);
        }
    }

    public void attachNormal() throws Exception {
        //注意，是jre的bin目录，不是jdk的bin目录
        //VirtualMachine need the attach.dll in the jre of the JDK.
//        System.setProperty("java.library.path",
//                "/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home/lib/tools.jar");
//        Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
//        fieldSysPath.setAccessible(true);
//        fieldSysPath.set(null, null);
        VirtualMachine vm = VirtualMachine.attach(getCurrentThreadIDStr());
        //参数1：代理jar的位置
        //参数2， 传递给代理的参数
        vm.loadAgent(AGENT_FILE, filePath);
    }

    private String getCurrentThreadIDStr() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String threadId = runtimeMXBean.getName().split("@")[0];
        System.out.println("当前线程id=" + threadId);
        return threadId;
    }

}
