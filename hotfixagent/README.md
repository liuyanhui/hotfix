# premain方式
---
通过premain的方式也可以实现热修复。思路如下：
1. 当目标JVM启动时，agent中的Instrumentation已经被加载并初始化。
2. 只需要通过反射得到Instrumentation对象，在通过调用Instrumentation.redefineClasses()就可以实现热修复。
3. 需要通过-javaagent:agent.jar引入agent。

```java
class HotFixDemo{
    Instrumentation inst = null;
    private Instrumentation tryGetInstrumentation(){
        Class cls = Class.forName("agent.PremainAgent");
        Field field = cls.getDeclaredField("INST");
        if(field != null) {
            inst = (Instrumentation)field.get(cls.getClass());
        }
        return inst;
    }
}

```
 