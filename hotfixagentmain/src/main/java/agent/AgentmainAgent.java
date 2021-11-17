package agent;

import com.sun.tools.classfile.ClassFile;
import com.sun.tools.classfile.ConstantPoolException;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.util.Arrays;

public class AgentmainAgent {
    public static void agentmain(String agentArgs, Instrumentation inst) {
        agentmain0(agentArgs, inst);
    }

    public static void agentmain0(String agentArgs, Instrumentation inst) {
        try {
            System.out.println("This is my agentmain");
            System.out.println("args: " + agentArgs);
            System.out.println("重新定义 -- 开始");
//            System.out.println("inst.getAllLoadedClasses()中存在类名列表：");
//            Arrays.stream(inst.getAllLoadedClasses()).forEach(System.out::println);

            //获取类名
            File f = new File(agentArgs);
            byte[] reporterClassFile = new byte[(int) f.length()];
            DataInputStream in = new DataInputStream(new FileInputStream(f));
            String classname = ClassFile.read(in).getName().replaceAll("/", ".");
            in.close();
            //获取类二进制文件
            in = new DataInputStream(new FileInputStream(f));
            in.readFully(reporterClassFile);
            in.close();

            //把User类的定义与新的类文件关联起来
            Class cls = Arrays.stream(inst.getAllLoadedClasses()).filter(c -> c.getName().equals(classname)).limit(1).findFirst().get();
            if(cls!=null) {
                System.out.println("inst.getAllLoadedClasses()中存在类：" + cls.getName());
            }else {
                System.out.println("inst.getAllLoadedClasses()中没有类：" + classname);
            }

            ClassDefinition reporterDef = new ClassDefinition(cls, reporterClassFile);
            //重新定义User类
            inst.redefineClasses(reporterDef);
            System.out.println("重新定义 -- 完成");

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
        }
    }

    public static void printAllLoadedClassed(Instrumentation inst) {
        System.out.println("所有已经加载的类 -- beg");
        Arrays.stream(inst.getAllLoadedClasses()).forEach(System.out::println);
        System.out.println("所有已经加载的类 -- end");
    }

    public static void main(String[] agentArgs) throws ConstantPoolException {
//        String classname = "";
//        final int MAGIC = 0xCAFEBABE;
//        try {
//            DataInputStream in = new
//                    DataInputStream(new FileInputStream("/Users/liuyanhui/Documents/project/hotfix/hotfixclient/target/classes/org.bruce.entity/User.class"));
//            System.out.println(ClassFile.read(in).getName().replaceAll("/", "."));
//            in.close();
//            in = new
//                    DataInputStream(new FileInputStream("/Users/liuyanhui/Documents/project/hotfix/hotfixclient/target/classes/org.bruce.entity/User.class"));
//            if (in.readInt() != MAGIC) {
//// 不是一个.class文件
//                throw new IOException("Not a class file");
//            }
//
//            System.out.println(in.readUnsignedShort());// 次版本号
//            System.out.println(in.readUnsignedShort());// 主版本号
//            System.out.println(in.readUnsignedShort());// 长度
//            in.readByte();// CLASS=7
//            in.readUnsignedShort();// 忽略这个地方
//            in.readByte();// UTF8=1
////            System.out.println(classname);
//            in.close();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
    }
}
