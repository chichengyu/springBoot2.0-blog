# springBoot2.0-blog

执行顺序：`Filter(过滤器) -> Intercpter(拦截器) -> Aspect(aop切面) -> Controller(请求的控制器)`，控制器抛出的异常的捕获顺序是 `Controller(请求的控制器) -> Aspect(aop切面) -> ControllerAdvice(全局异常) -> Intercpter(拦截器) -> Filter(过滤器)`

使用 springBoot2.0 进行开发的一个博客，文章使用 [markdown](https://pandao.github.io/editor.md/) 语法，[prism代码高亮](https://github.com/PrismJS/prism)，[Tocbot文章目录生成](https://tscanlin.github.io/tocbot/)，[动画animate.css](https://daneden.github.io/animate.css/)，[waypoints滚到侦测](http://imakewebthings.com/waypoints/)，[Jquery平滑滚动](https://github.com/flesler/jquery.scrollTo)，[qrcode二维码](https://davidshimjs.github.io/qrcodejs/) 以及 `springBoot AOP运用`

模板：thymeleaf

页面开发使用 [semantic-ui官网](https://semantic-ui.com/)，[semantic-ui中文网](http://www.semantic-ui.cn/)，[背景图片资源](https://www.toptal.com/designers/subtlepatterns/)

自定义 `MarkdownUtils`工具类(`markdown`转`html`)

用到的jar包

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.0.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.xiaochi</groupId>
    <artifactId>blog</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>blog</name>
    <description>blog project for Spring Boot</description>
    <packaging>jar</packaging>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <!-- 打包时用到 -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <scope>provided</scope>
        </dependency>
        <!-- ********************* spring start ********************** -->
        <!-- web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <!--<exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                </exclusion>
            </exclusions>-->
        </dependency>
        <!-- thymeleaf -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <!-- aop -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <!-- jpa -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <!-- devtools -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
        <!-- ********************* spring end ********************** -->
        <!-- mysql -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!-- lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <!-- 表示两个项目之间依赖不传递；不设置optional或者optional是false，表示传递依赖 -->
            <optional>true</optional>
        </dependency>
        <!-- jbcrypt 加密 -->
        <dependency>
            <groupId>org.mindrot</groupId>
            <artifactId>jbcrypt</artifactId>
            <version>0.4</version>
        </dependency>
        <!-- ********************* commonmark end ********************** -->
        <!-- commonmark 将 markdown 字符串转成 html 特点：小而快 -->
        <dependency>
            <groupId>com.atlassian.commonmark</groupId>
            <artifactId>commonmark</artifactId>
            <version>0.14.0</version>
        </dependency>
        <!-- 标题 -->
        <dependency>
            <groupId>com.atlassian.commonmark</groupId>
            <artifactId>commonmark-ext-heading-anchor</artifactId>
            <version>0.14.0</version>
        </dependency>
        <!-- table -->
        <dependency>
            <groupId>com.atlassian.commonmark</groupId>
            <artifactId>commonmark-ext-gfm-tables</artifactId>
            <version>0.14.0</version>
        </dependency>
        <!-- ********************* commonmark end ********************** -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-text</artifactId>
            <version>1.8</version>
        </dependency>

        <!-- test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <!--<exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>-->
        </dependency>
    </dependencies>

    <build>
        <finalName>blog</finalName>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```