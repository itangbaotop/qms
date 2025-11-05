# QMS 质量管理系统

## 项目概述

QMS (Quality Management System) 是一个基于 Spring Boot 的质量管理系统，采用插件化架构设计，支持动态加载客户定制的审批策略。

## 项目结构

```
qms/
├── qms-api/                    # API 接口模块
├── qms-core-service/           # 核心服务模块
├── customer-a-qms-plugin/      # 客户 A 定制插件
└── README.md
```

### 模块说明

- **qms-api**: 定义了系统的核心接口和 DTO 对象
- **qms-core-service**: 主要的业务逻辑和 Web 服务
- **customer-a-qms-plugin**: 客户 A 的定制审批策略插件

## 技术栈

- Java 17
- Spring Boot 3.5.3
- Maven 3.x
- Spring Boot PropertiesLauncher (支持插件动态加载)

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+

### 构建项目

```bash
# 克隆项目
git clone <repository-url>
cd qms

# 编译打包
mvn clean package -DskipTests
```

### 运行应用

```bash
# 进入核心服务目录
cd qms-core-service/target

# 启动应用
java -jar qms-core-service-1.0-SNAPSHOT.jar
```

应用启动后，访问 http://localhost:8080

### 测试接口

```bash
# 基础测试
curl http://localhost:8080/test/test

# 审批功能测试
curl http://localhost:8080/test/hello
```

## 插件化架构

### 插件加载机制

系统使用 Spring Boot 的 PropertiesLauncher 来实现插件动态加载：

1. **打包配置**: 使用 `layout=ZIP` 配置，确保使用 PropertiesLauncher
2. **依赖注入**: 插件中的 Spring 组件会自动被扫描和注册
3. **策略模式**: 通过接口 `IQmsApprovalStrategy` 实现不同的审批策略

### 创建新插件

1. 创建新的 Maven 模块
2. 添加对 `qms-api` 的依赖
3. 实现 `IQmsApprovalStrategy` 接口
4. 使用 `@Component` 注解注册策略
5. 在 `qms-core-service` 中添加插件依赖

示例插件代码：

```java
@Component("customStrategy")
public class CustomApprovalStrategy implements IQmsApprovalStrategy {
    
    @Override
    public ApprovalResult execute(QmsReport report) {
        // 自定义审批逻辑
        return new ApprovalResult("通过", "符合要求");
    }
    
    @Override
    public String getStrategyName() {
        return "custom";
    }
}
```

## 配置说明

### application.properties

```properties
# PropertiesLauncher 配置
loader.path=BOOT-INF/lib/,BOOT-INF/classes/,lib/

# 服务端口
server.port=8080

# 日志配置
logging.level.top.itangbao.qms=INFO
```

### Maven 配置要点

#### qms-core-service/pom.xml

```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <layout>ZIP</layout>
        <includeSystemScope>true</includeSystemScope>
    </configuration>
</plugin>
```

#### 插件模块/pom.xml

```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <skip>true</skip>
    </configuration>
</plugin>
```

## API 文档

### 测试接口

#### GET /test/test
- **描述**: 基础连通性测试
- **响应**: `"test"`

#### GET /test/hello
- **描述**: 审批功能测试
- **响应**: 
```json
{
    "result": "通过/不通过",
    "reason": "审批原因"
}
```

## 开发指南

### 添加新的审批策略

1. 在对应的插件模块中创建策略类
2. 实现 `IQmsApprovalStrategy` 接口
3. 使用 `@Component` 注解并指定唯一的 bean 名称
4. 重新打包部署

### 扩展 API

1. 在 `qms-api` 模块中定义新的接口或 DTO
2. 在 `qms-core-service` 中实现业务逻辑
3. 插件模块可以依赖新的 API 进行扩展

## 部署说明

### 生产环境部署

1. 确保目标环境有 JDK 17+
2. 将打包好的 jar 文件上传到服务器
3. 配置 JVM 参数（可选）：
```bash
java -Xms512m -Xmx1024m -jar qms-core-service-1.0-SNAPSHOT.jar
```

### Docker 部署（可选）

```dockerfile
FROM openjdk:17-jre-slim
COPY qms-core-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 故障排除

### 常见问题

1. **插件未加载**
   - 检查插件是否在 `BOOT-INF/lib/` 目录中
   - 确认插件中的组件使用了正确的包名和注解

2. **启动失败**
   - 检查 JDK 版本是否为 17+
   - 查看启动日志中的错误信息

3. **端口冲突**
   - 修改 `application.properties` 中的 `server.port` 配置
   - 或使用命令行参数：`--server.port=8081`

### 日志配置

```properties
# 开启调试日志
logging.level.top.itangbao.qms=DEBUG
logging.level.org.springframework.boot.loader=DEBUG
```

## 贡献指南

1. Fork 项目
2. 创建特性分支
3. 提交更改
4. 推送到分支
5. 创建 Pull Request

## 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 联系方式

- 项目维护者: [维护者姓名]
- 邮箱: [邮箱地址]
- 项目地址: [项目仓库地址]