# sx_spring

Spring 框架学习笔记与手写注解示例代码仓库，用于理解 IoC、依赖注入与 Bean 作用域等核心概念。

## 项目简介

`sx_spring` 是一个轻量级 Maven Java 项目（Java 11），主要存放 Spring 相关的**学习示例**与**手写注解实验**，而非完整的 Web 应用。代码按包划分：`com.javaSpring` 下含 `note`（注解说明）、`class1`（课堂示例）、`port`（接口实验）；`com.wenjin` 含补充练习代码。

## 技术栈

- Java 11
- Maven
- Spring 核心概念（手写 `@Autowired`、`@Scope`、`@Component` 等注解示例）

## 主要功能

- **note 包**：`Autowired.java`、`Scope.java`、`GitBean.java`、`GitBeanClass.java` 等注解与 Bean 生命周期学习代码
- **class1 / port 包**：Spring IoC 与接口注入练习
- **wenjin 包**：额外实验代码

## 项目结构

```
sx_spring/
├── pom.xml
└── src/main/java/com/
    ├── javaSpring/
    │   ├── note/           # 注解与 Bean 示例
    │   ├── class1/         # 课堂练习
    │   └── port/           # 接口实验
    └── wenjin/             # 补充代码
```

## 快速开始

**环境要求**：JDK 11、Maven 3.x

```bash
git clone https://github.com/hubaolong3632/sx_spring.git
cd sx_spring
mvn clean compile
```

在 IDE 中直接运行各示例类的 `main` 方法进行学习。

## 相关仓库

- 完整 Spring Boot 实践：[daliy_spring_v1](https://github.com/hubaolong3632/daliy_spring_v1)
- Spring MVC WAR 项目：[BST](https://github.com/hubaolong3632/BST)
