package org.bruce.springboothotfixserver.util;

import com.sun.tools.classfile.ClassFile;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class ClassReadUtil {
    /**
     * 根据class文件获取类名
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static String getClassNameFromFile(String path) throws Exception {
        if (path == null || path.trim().equals(""))
            throw new Exception("The input param must not be null");
        String classname = null;
        //获取类名
        File f = new File(path);
        DataInputStream in = new DataInputStream(new FileInputStream(f));
        classname = ClassFile.read(in).getName().replaceAll("/", ".");
        in.close();
        return classname;
    }

    public static String getDefaultBeanNameFromClassName(String classname) throws Exception {
        if (classname == null || classname.trim().equals(""))
            throw new Exception("The input param must not be null");

        String beanName = classname.substring(classname.lastIndexOf('.') + 1, classname.length());
        if (!Character.isLowerCase(beanName.charAt(0))) {
            Character.toLowerCase(beanName.charAt(0));
            beanName = new StringBuilder().append(Character.toLowerCase(beanName.charAt(0))).append(beanName.substring(1)).toString();
        }

        return beanName;
    }

    public static void main(String[] args) throws Exception {
        String path = "/Users/liuyanhui/Documents/project/hotfix/hotfixclient/target/classes/org/bruce/springboothotfixserver/entity/UserSpringImpl.class";
        String classname = getClassNameFromFile(path);
        System.out.println("classname:" + classname);
        System.out.println("beanName:" + getDefaultBeanNameFromClassName(classname));
    }
}
