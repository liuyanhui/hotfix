agent client 服务端 
main方式启动
tomcat启动：
1.需要在启动参数中增加-Xbootclasspath/a:$JAVA_HOME/lib/tools.jar。
参考资料：
https://blog.csdn.net/wk52525/article/details/87972068
https://www.cnblogs.com/duanxz/p/3482311.html
2.或者让tomcat启动时加载tools.jar。

