# 随记

## 2023  
### 2/14  
Merge the incoming changes into the current branch:将传入的更改合并到当前分支中
    
Rebase the current branch on top of the incoming changes:在传入更改的基础上重新建立当前分支

### 2/13  
快捷键：  
https://docs.github.com/zh/get-started/using-github/keyboard-shortcuts  
ctrl+k 打开命令面板  

阅读代码技巧：  
+ 在仓库详情页按下。（句号键），项目会在网页版VSCode中打开。  
 
在线运行项目：  
+ 项目在网页版VsCode打开之后，在项目地址前加上：gitpod.io/#前缀
例如：https://github.dev/seasky-wjl/MyBatisIndoor 加上前缀  https://gitpod.io/#https://github.com/seasky-wjl/MyBatisIndoor

 


### 2/3

+ 根据分支名push到指定分支  
```  
git push -u origin branchName
```

+ 查看当前分支  
```  
git branch  
```  
#### 重命名本地分支
+ 在当前分支时  

```
git branch -m new_branch_name
```  

+ 不在当前分支时  
```
git branch -m old_branch_name new_branch_name
```
#### 重命名远端分支(假设是在当前分支，并且远端分支与本地分支名是一致的)  
思路是：改本地分支名，删除远端分支，再把改过的分支推到远端，最后关联这两个分支
+ 重命名本地分支  
```  
git branch -m new_branch_name  
``` 
+ 删除远程分支  
```   
 git push --delete origin old_branch_name  
``` 
+ 上传新命名的本地分支  
``` 
git push  origin new_branch_name  
``` 
+ 关联修改后的本地分支与远程分支  
```   
git branch --set-upstream-to origin/new_branch_name  
``` 

项目由Github托管，团队协同工作的流程大致是：  
1. 远端有更新，每天打开电脑开发之后，首先把远端的内容同步到本地，并在此基础上进行开发。
2. 开发时注意切换到dev分支，开发完成之后提交更新内容，并合并到main分支，最后将所有提交同步到远端。


<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
                        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <p>
        Hello Web!
    </p>
    <p>
        <%
            Date now = new Date();
        %>
        服务器时间：<fmt:formatDate value="<%=now%>" pattern="yyyy-MM-dd HH:mm:ss"/>
    </p>


</body>
</html>
















    <groupId>tk.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <!--        设置源代码编码方式为UTF-8-->
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>

        <!-- Junit依赖-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>

        <!-- MyBatis依赖-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.2</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.38</version>
        </dependency>

        <!-- Log4j依赖-->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.12</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.7.12</version>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-maven-plugin</artifactId>
            <version>1.3.2</version>
        </dependency>

        <!-- 集成EhCache缓存的依赖-->
        <dependency>
            <groupId>org.mybatis.caches</groupId>
            <artifactId>mybatis-ehcache</artifactId>
            <version>1.0.3</version>
        </dependency>

        <!--使用MyBatis官方提供的redis-cache集成Redis数据库-->
        <dependency>
            <groupId>org.mybatis.caches</groupId>
            <artifactId>mybatis-redis</artifactId>
            <version>1.0.0-beta2</version>
        </dependency>

        <!--web-->
        <!--支持servlet-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
            <scope>provided</scope>
        </dependency>

        <!--支持JSP-->
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.1.3-b06</version>
            <scope>provided</scope>
        </dependency>

        <!--支持JSTL-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
            <scope>provided</scope>
        </dependency>

    </dependencies>
</project>