package agent;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AgentMainClassloader extends ClassLoader {
//    protected Class<?> findClass(String filename) {
//        byte[] cLassBytes = null;
//        try {
//            File f = new File(agentArgs);
//            cLassBytes = new byte[(int) f.length()];
//            DataInputStream in = new DataInputStream(new FileInputStream(f));
//            in.readFully(reporterClassFile);
//            in.close();
//            path = Paths.get(new URI(filename));
//            cLassBytes = Files.readAllBytes(path);
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//        }
//        Class clazz = defineClass("com.infosec.manager.controller.login.bug", cLassBytes, 0, cLassBytes.length);
//        return clazz;
//    }
}
