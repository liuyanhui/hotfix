# agent client+服务端合体 
## 可以实现server自己线程attach到自己的形式，免去使用client调用agent。
---
1. main方式启动demo
 + 执行service.AppService.main()即可
2. tomcat启动demo：
 + 或者在启动参数中增加-Xbootclasspath/a:$JAVA_HOME/lib/tools.jar。
   * 参考资料：
     - https://blog.csdn.net/wk52525/article/details/87972068
     - https://www.cnblogs.com/duanxz/p/3482311.html
 + 或者把tools.jar中com.sun.tools.attach包中的相关类copy到工程中，就想这个demo中的做法一样。
  * 这种方式太bug了。

