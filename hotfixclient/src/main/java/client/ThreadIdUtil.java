package client;

import sun.jvmstat.monitor.*;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

public class ThreadIdUtil {

    public static int getCurrentThreadID() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        int threadId = Integer.valueOf(runtimeMXBean.getName().split("@")[0]);
        System.out.println("当前线程id=" + threadId);
        return threadId;
    }

    public static String getCurrentThreadIDStr() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String threadId = runtimeMXBean.getName().split("@")[0];
        System.out.println("当前线程id=" + threadId);
        return threadId;
    }

    public static void getAllJavaThreadID() throws Exception {
        // 获取监控主机
        MonitoredHost local = MonitoredHost.getMonitoredHost("localhost");// 取得所有在活动的虚拟机集合
        Set vmlist = new HashSet(local.activeVms());
        // 遍历集合，输出PID和进程名
        for (Object process : vmlist) {
            MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + process));
            // 获取类名
            String processname = MonitoredVmUtil.mainClass(vm, true);
            System.out.println(process + " ------> " + processname);
        }
    }

    /**
     * 直接根据类找到对应Java进程ID
     * @param cls
     * @return
     * @throws MonitorException
     * @throws URISyntaxException
     */
    public static int getProcess(Class<?> cls) throws MonitorException, URISyntaxException {
        if (cls == null) {
            return -1;
        }

        // 获取监控主机
        MonitoredHost local = MonitoredHost.getMonitoredHost("localhost");
        // 取得所有在活动的虚拟机集合
        Set<?> vmlist = new HashSet<Object>(local.activeVms());
        // 遍历集合，输出PID和进程名
        for (Object process : vmlist) {
            MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + process));
            // 获取类名
            String processname = MonitoredVmUtil.mainClass(vm, true);
            if (cls.getName().equals(processname)) {
                System.out.println(processname + " ------> " + ((Integer) process).intValue());
                return ((Integer) process).intValue();
            }
        }
        return -1;
    }

    /**
     * 根据类名获取线程id
     * @param cls
     * @return
     * @throws MonitorException
     * @throws URISyntaxException
     */
    public static int getProcess(String cls) throws MonitorException, URISyntaxException {
        if (cls == null) {
            return -1;
        }

        // 获取监控主机
        MonitoredHost local = MonitoredHost.getMonitoredHost("localhost");
        // 取得所有在活动的虚拟机集合
        Set<?> vmlist = new HashSet<Object>(local.activeVms());
        // 遍历集合，输出PID和进程名
        for (Object process : vmlist) {
            MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + process));
            // 获取类名
            String processname = MonitoredVmUtil.mainClass(vm, true);
            if (cls.equals(processname)) {
                System.out.println(cls + " ------> " + ((Integer) process).intValue());
                return ((Integer) process).intValue();
            }
        }
        return -1;
    }


    public static void main(String[] args) throws Exception {
        getCurrentThreadID();
        getAllJavaThreadID();
//        getProcess(service.class);
        getProcess("service.AppService");
    }
}
