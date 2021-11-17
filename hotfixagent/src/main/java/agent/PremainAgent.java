package agent;


import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class PremainAgent {
    private static Instrumentation INST;

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("This is my premain");
        System.out.println("args: " + agentArgs);
        INST = inst;
        process();
    }

    private static void process() {
        INST.addTransformer(new ClassFileTransformer() {
                                public byte[] transform(ClassLoader loader, String className,
                                                        Class<?> clazz,
                                                        ProtectionDomain protectionDomain,
                                                        byte[] byteCode) throws IllegalClassFormatException {
//                                    System.out.println(String.format("Process by ClassFileTransformer,target class = %s", className));
                                    return byteCode;
                                }
                            }
        );
    }
}
