# springbootdemo02 
springboot 整合 docker 部署（两种构建 Docker 镜像方式）

## 前置条件
 - dcoker 环境 
 - 对应版本的 Maven 、jdk
 
## Dockerfile 配置文件详解

Dockerfile
```dockerfile
FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD springbootdemo02-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
```
- `VOLUME` 指定了临时文件目录为 `/tmp`。其效果是在主机 `/var/lib/docker` 目录下创建了一个临时文件，并链接到容器的 `/tmp`。改步骤是可选的，如果涉及到文件系统的应用就很有必要了。`/tmp` 目录用来持久化到 Docker 数据文件夹，因为 Spring Boot 使用的内嵌 Tomcat 容器默认使用/tmp作为工作目录
- 项目的 jar 文件作为 `app.jar` 添加到容器的
- `ENTRYPOINT` 执行项目 `app.jar`。为了缩短 Tomcat 启动时间，添加一个系统属性指向 `/dev/urandom` 作为 Entropy Source

## 非 Docker 方式运行程序
1、使用 Maven 命令打包
```$xslt
mvn package
```
2、运行项目
```$xslt
java -jar target/springbootdemo02-0.0.1-SNAPSHOT.jar
```
3、访问项目
如果程序正确运行，浏览器访问 `http://localhost:8010/`，可以看到页面 `Hello World` 字样。
## 在 docker 开始部署 springBoot 项目(方法一)
1、在 centos7 ~ 创建一个文件夹 docker 里面放置 上面的 Dockerfile 和 springBoot 打包的项目 springbootdemo02-0.0.1-SNAPSHOT.jar

在该docker文件下 指令：
`docker build -t docker .`
执行 `docker build` 命令，docker 就会根据 `Dockerfile` 里你定义好的命令进行构建新的镜像。

- `-t` 代表要构建的镜像的 tag，
- `.` 代表当前目录，也就是 `Dockerfile` 所在的目录。

然后就可以看到在下载各种依赖的 maven、各种 jar，构建完毕后，启动项目。

2、在该docker文件下使用 指令：
```$xslt
docker run -d -p 8010:8010 docker
```
运行该 springBoot 项目，可以看到构建完毕的镜像 docker 了
3、最后，访问本地浏览器：`http://localhost:8010/`，可以看到页面 `Hello World` 字样。

## 在docker开始部署springBoot项目(方法二)
1、编译代码，发布镜像
在 `E:\Git\springbootdemo02` 目录下运行命令：
```
mvn package docker:build
```
看到build success说明该项目的镜像创建成功，查看一下
```$xslt
E:\Git\springbootdemo02>docker images
REPOSITORY                          TAG                 IMAGE ID            CREATED             SIZE
springio/springbootdemo02           latest              3c1a2afa31a8        5 minutes ago       199MB
```
2、发布容器

```$xslt
E:\Git\springbootdemo02>docker run -p 8010:8010 -t springio/springbootdemo02

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::       (v2.1.10.RELEASE)

2019-12-07 02:28:20.699  INFO 1 --- [           main] c.z.s.Springbootdemo02Application        : Starting Springbootdemo02Application v0.0.1-SNAPSHOT on 9a26109198d2 with PID 1 (/app.jar started by root in /)
2019-12-07 02:28:20.712  INFO 1 --- [           main] c.z.s.Springbootdemo02Application        : No active profile set, falling back to default profiles: default
2019-12-07 02:28:24.325  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8010 (http)
2019-12-07 02:28:24.428  INFO 1 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2019-12-07 02:28:24.430  INFO 1 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.27]
2019-12-07 02:28:24.788  INFO 1 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2019-12-07 02:28:24.789  INFO 1 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 3861 ms
2019-12-07 02:28:25.433  INFO 1 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2019-12-07 02:28:25.992  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8010 (http) with context path ''
2019-12-07 02:28:26.010  INFO 1 --- [           main] c.z.s.Springbootdemo02Application        : Started Springbootdemo02Application in 6.873 seconds (JVM running for 7.886)
2019-12-07 02:28:42.438  INFO 1 --- [nio-8010-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2019-12-07 02:28:42.439  INFO 1 --- [nio-8010-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2019-12-07 02:28:42.475  INFO 1 --- [nio-8010-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 35 ms
```
## 推送 Image 到 Docker hub
首先，你在 Docker Hub 要有注册账号，且创建了相应的库；

其次，docker 推送前，先要登录，否则报unauthorized: access to the requested resource is not authorized的错误

执行：
```
docker login
```
输出为：
```jshelllanguage
[root@node203 spring-boot]# docker login
Username: yichaozhang
Password: 
Email: yichao0803@gmail.com
WARNING: login credentials saved in /root/.docker/config.json
Login Succeeded
```
执行推送
```jshelllanguage

docker push yichaozhang/springbootdemo02
[root@node203 spring-boot]# docker push yichaozhang/springbootdemo02 
The push refers to a repository [docker.io/yichaozhang/springbootdemo02 ]
751d29eef02e: Layer already exists 
4da3741f39c7: Pushed 
5f70bf18a086: Layer already exists 
7e4d0cb13643: Layer already exists 
8f045733649f: Layer already exists 
latest: digest: sha256:eb4d5308ba1bb27489d808279e74784bda6761b3574f4298d746abba59b35164 size: 9415

```

## 问题记录
无
## 参考资料

- [用 Docker 构建、运行、发布一个 Spring Boot 应用](https://waylau.com/docker-spring-boot/)
- [springboot整合docker部署（两种构建Docker镜像方式）](https://www.cnblogs.com/shamo89/p/9201513.html)
- [第一个 spring Boot 应用通过Docker 来实现构建、运行、发布](https://blog.csdn.net/u010046908/article/details/56008445?fps=1&locationNum=13)
- [docker_File 执行报错总结](https://www.cnblogs.com/liaojiafa/p/8901522.html)