# Spring手撸专栏[目录&源码 🖱下滚动]

> **作者：** 小傅哥，Java Developer，[:pencil2: 虫洞 · 科技栈，博主](https://bugstack.cn)，[:blue_book: 《重学Java设计模式》图书作者](https://u.jd.com/qPnzanP)

<br/>
<div align="center">
    <a href="https://bugstack.cn" style="text-decoration:none"><img src="https://bugstack.cn/assets/images/icon.svg" width="128px"></a>
</div>
<br/>  

<div align="center">
<a href="https://github.com/fuzhengwei/CodeGuide"><img src="https://badgen.net/github/stars/fuzhengwei/CodeGuide?icon=github&color=4ab8a1"></a>
<a href="https://github.com/fuzhengwei/CodeGuide"><img src="https://badgen.net/github/forks/fuzhengwei/CodeGuide?icon=github&color=4ab8a1"></a>
<a href="https://bugstack.cn" target="_blank"><img src="https://bugstack.cn/assets/images/onlinebook.svg"></a>
<a href="https://bugstack.cn/assets/images/qrcode.png?x-oss-process=style/may"><img src="https://itedus.cn/_media/wxbugstack.svg"></a>
</div>

<br/>
<div align="center">
    <a href="https://bugstack.cn" style="text-decoration:none"><img src="https://github.com/fuzhengwei/small-spring/blob/main/docs/_media/pdf.png?raw=true" width="512px"></a>
</div>

---

本仓库以 Spring 源码学习为目的，通过手写简化版 Spring 框架，了解 Spring 核心原理。

在手写的过程中会简化 Spring 源码，摘取整体框架中的核心逻辑，简化代码实现过程，保留核心功能，例如：IOC、AOP、Bean生命周期、上下文、作用域、资源处理等内容实现。

[`小傅哥`](https://bugstack.cn/)，之所以开始撸Spring源码，主要就是因为在编写[《面经手册》](https://bugstack.cn/itstack/interview.html)时，涉及到的Spring源码都会写很多的文字描述、绘制冗长的流程图稿、做不少的内容铺垫，但对于新人来说想直接学习这部分内容仍是非常困难的，那么现在为了让我以及更多的伙伴能有一个学习的`抓手`，我们来一起研究研究什么是快乐星球！

## 目录&源码

- [x] 第 01 章：[开篇介绍，我要带你撸 Spring 啦！](https://bugstack.cn/spring/2021/05/16/%E7%AC%AC1%E7%AB%A0-%E5%BC%80%E7%AF%87%E4%BB%8B%E7%BB%8D-%E6%89%8B%E5%86%99Spring%E8%83%BD%E7%BB%99%E4%BD%A0%E5%B8%A6%E6%9D%A5%E4%BB%80%E4%B9%88.html)
- [x] 第 02 章：[小试牛刀，实现一个简单的Bean容器](https://bugstack.cn/spring/2021/05/20/%E7%AC%AC2%E7%AB%A0-%E5%B0%8F%E8%AF%95%E7%89%9B%E5%88%80-%E5%AE%9E%E7%8E%B0%E4%B8%80%E4%B8%AA%E7%AE%80%E5%8D%95%E7%9A%84Bean%E5%AE%B9%E5%99%A8.html)
- [x] 第 03 章：[初显身手，运用设计模式，实现 Bean 的定义、注册、获取](https://bugstack.cn/spring/2021/05/23/%E7%AC%AC3%E7%AB%A0-%E5%88%9D%E6%98%BE%E8%BA%AB%E6%89%8B-%E8%BF%90%E7%94%A8%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F-%E5%AE%9E%E7%8E%B0-Bean-%E7%9A%84%E5%AE%9A%E4%B9%89-%E6%B3%A8%E5%86%8C-%E8%8E%B7%E5%8F%96.html)
- [x] 第 04 章：[崭露头角，基于Cglib实现含构造函数的类实例化策略](https://bugstack.cn/spring/2021/05/30/%E7%AC%AC4%E7%AB%A0-%E5%B4%AD%E9%9C%B2%E5%A4%B4%E8%A7%92-%E5%9F%BA%E4%BA%8ECglib%E5%AE%9E%E7%8E%B0%E5%90%AB%E6%9E%84%E9%80%A0%E5%87%BD%E6%95%B0%E7%9A%84%E7%B1%BB%E5%AE%9E%E4%BE%8B%E5%8C%96%E7%AD%96%E7%95%A5.html)
- [x] 第 05 章：[一鸣惊人，为Bean对象注入属性和依赖Bean的功能实现](https://bugstack.cn/spring/2021/06/02/%E7%AC%AC5%E7%AB%A0-%E4%B8%80%E9%B8%A3%E6%83%8A%E4%BA%BA-%E4%B8%BABean%E5%AF%B9%E8%B1%A1%E6%B3%A8%E5%85%A5%E5%B1%9E%E6%80%A7%E5%92%8C%E4%BE%9D%E8%B5%96Bean%E7%9A%84%E5%8A%9F%E8%83%BD%E5%AE%9E%E7%8E%B0.html)
- [x] 第 06 章：[气吞山河，设计与实现资源加载器，从Spring.xml解析和注册Bean对象](https://bugstack.cn/spring/2021/06/09/%E7%AC%AC6%E7%AB%A0-%E6%B0%94%E5%90%9E%E5%B1%B1%E6%B2%B3-%E8%AE%BE%E8%AE%A1%E4%B8%8E%E5%AE%9E%E7%8E%B0%E8%B5%84%E6%BA%90%E5%8A%A0%E8%BD%BD%E5%99%A8-%E4%BB%8ESpring.xml%E8%A7%A3%E6%9E%90%E5%92%8C%E6%B3%A8%E5%86%8CBean%E5%AF%B9%E8%B1%A1.html)
- [x] 第 07 章：[所向披靡，实现应用上下文，自动识别、资源加载、扩展机制](https://bugstack.cn/spring/2021/06/17/%E7%AC%AC7%E7%AB%A0-%E6%89%80%E5%90%91%E6%8A%AB%E9%9D%A1-%E5%AE%9E%E7%8E%B0%E5%BA%94%E7%94%A8%E4%B8%8A%E4%B8%8B%E6%96%87-%E8%87%AA%E5%8A%A8%E8%AF%86%E5%88%AB-%E8%B5%84%E6%BA%90%E5%8A%A0%E8%BD%BD-%E6%89%A9%E5%B1%95%E6%9C%BA%E5%88%B6.html)
- [x] 第 08 章：[龙行有风，向虚拟机注册钩子，实现Bean对象的初始化和销毁方法](https://bugstack.cn/spring/2021/06/23/%E7%AC%AC8%E7%AB%A0-%E9%BE%99%E8%A1%8C%E6%9C%89%E9%A3%8E-%E5%90%91%E8%99%9A%E6%8B%9F%E6%9C%BA%E6%B3%A8%E5%86%8C%E9%92%A9%E5%AD%90-%E5%AE%9E%E7%8E%B0Bean%E5%AF%B9%E8%B1%A1%E7%9A%84%E5%88%9D%E5%A7%8B%E5%8C%96%E5%92%8C%E9%94%80%E6%AF%81%E6%96%B9%E6%B3%95.html)
- [x] 第 09 章：[虎行有雨，定义标记类型Aware接口，实现感知容器对象](https://bugstack.cn/spring/2021/06/23/%E7%AC%AC8%E7%AB%A0-%E9%BE%99%E8%A1%8C%E6%9C%89%E9%A3%8E-%E5%90%91%E8%99%9A%E6%8B%9F%E6%9C%BA%E6%B3%A8%E5%86%8C%E9%92%A9%E5%AD%90-%E5%AE%9E%E7%8E%B0Bean%E5%AF%B9%E8%B1%A1%E7%9A%84%E5%88%9D%E5%A7%8B%E5%8C%96%E5%92%8C%E9%94%80%E6%AF%81%E6%96%B9%E6%B3%95.html)
- [x] 第 10 章：[横刀跃马，关于Bean对象作用域以及FactoryBean的实现和使用](https://bugstack.cn/spring/2021/06/30/%E7%AC%AC10%E7%AB%A0-%E6%A8%AA%E5%88%80%E8%B7%83%E9%A9%AC-%E5%85%B3%E4%BA%8EBean%E5%AF%B9%E8%B1%A1%E4%BD%9C%E7%94%A8%E5%9F%9F%E4%BB%A5%E5%8F%8AFactoryBean%E7%9A%84%E5%AE%9E%E7%8E%B0%E5%92%8C%E4%BD%BF%E7%94%A8.html)
- [x] 第 11 章：[更上层楼，基于观察者实现，容器事件和事件监听器](https://bugstack.cn/spring/2021/07/07/%E7%AC%AC11%E7%AB%A0-%E6%9B%B4%E4%B8%8A%E5%B1%82%E6%A5%BC-%E5%9F%BA%E4%BA%8E%E8%A7%82%E5%AF%9F%E8%80%85%E5%AE%9E%E7%8E%B0-%E5%AE%B9%E5%99%A8%E4%BA%8B%E4%BB%B6%E5%92%8C%E4%BA%8B%E4%BB%B6%E7%9B%91%E5%90%AC%E5%99%A8.html)
- [x] 第 12 章：[炉火纯青，基于JDK和Cglib动态代理，实现AOP核心功能](https://bugstack.cn/spring/2021/07/13/%E7%AC%AC12%E7%AB%A0-%E7%82%89%E7%81%AB%E7%BA%AF%E9%9D%92-%E5%9F%BA%E4%BA%8EJDK%E5%92%8CCglib%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86-%E5%AE%9E%E7%8E%B0AOP%E6%A0%B8%E5%BF%83%E5%8A%9F%E8%83%BD.html)
- [ ] 第 13 章：待归档...

| 序号 | 章节                                     | GitHub                                                       | Gitee                                                        |
| :--: | --------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
|  01   | [开篇介绍，我要带你撸 Spring 啦！](https://bugstack.cn/spring/2021/05/16/%E7%AC%AC1%E7%AB%A0-%E5%BC%80%E7%AF%87%E4%BB%8B%E7%BB%8D-%E6%89%8B%E5%86%99Spring%E8%83%BD%E7%BB%99%E4%BD%A0%E5%B8%A6%E6%9D%A5%E4%BB%80%E4%B9%88.html) | [small-spring](https://github.com/fuzhengwei/small-spring) | [small-spring](https://gitee.com/fustack/small-spring) |
|  02   | [小试牛刀，实现一个简单的Bean容器](https://bugstack.cn/spring/2021/05/20/%E7%AC%AC2%E7%AB%A0-%E5%B0%8F%E8%AF%95%E7%89%9B%E5%88%80-%E5%AE%9E%E7%8E%B0%E4%B8%80%E4%B8%AA%E7%AE%80%E5%8D%95%E7%9A%84Bean%E5%AE%B9%E5%99%A8.html)    | [small-spring-step-01](https://github.com/small-spring/small-spring-step-01) | [small-spring-step-01](https://gitee.com/small-spring/small-spring-step-01) |
|  03   | [初显身手，运用设计模式，实现 Bean 的定义、注册、获取](https://bugstack.cn/spring/2021/05/23/%E7%AC%AC3%E7%AB%A0-%E5%88%9D%E6%98%BE%E8%BA%AB%E6%89%8B-%E8%BF%90%E7%94%A8%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F-%E5%AE%9E%E7%8E%B0-Bean-%E7%9A%84%E5%AE%9A%E4%B9%89-%E6%B3%A8%E5%86%8C-%E8%8E%B7%E5%8F%96.html) | [small-spring-step-02](https://github.com/small-spring/small-spring-step-02) | [small-spring-step-02](https://gitee.com/small-spring/small-spring-step-02) |
|  04   | [崭露头角，基于Cglib实现含构造函数的类实例化策略](https://bugstack.cn/spring/2021/05/30/%E7%AC%AC4%E7%AB%A0-%E5%B4%AD%E9%9C%B2%E5%A4%B4%E8%A7%92-%E5%9F%BA%E4%BA%8ECglib%E5%AE%9E%E7%8E%B0%E5%90%AB%E6%9E%84%E9%80%A0%E5%87%BD%E6%95%B0%E7%9A%84%E7%B1%BB%E5%AE%9E%E4%BE%8B%E5%8C%96%E7%AD%96%E7%95%A5.html) | [small-spring-step-03](https://github.com/small-spring/small-spring-step-03) | [small-spring-step-03](https://gitee.com/small-spring/small-spring-step-03) |
|  05   | [为Bean对象注入属性和依赖Bean的功能实现](https://bugstack.cn/spring/2021/06/02/%E7%AC%AC5%E7%AB%A0-%E4%B8%80%E9%B8%A3%E6%83%8A%E4%BA%BA-%E4%B8%BABean%E5%AF%B9%E8%B1%A1%E6%B3%A8%E5%85%A5%E5%B1%9E%E6%80%A7%E5%92%8C%E4%BE%9D%E8%B5%96Bean%E7%9A%84%E5%8A%9F%E8%83%BD%E5%AE%9E%E7%8E%B0.html)          | [small-spring-step-04](https://github.com/small-spring/small-spring-step-04) | [small-spring-step-04](https://gitee.com/small-spring/small-spring-step-04) |
|  06   | [设计与实现资源加载器，从Spring.xml解析和注册Bean对象](https://bugstack.cn/spring/2021/06/09/%E7%AC%AC6%E7%AB%A0-%E6%B0%94%E5%90%9E%E5%B1%B1%E6%B2%B3-%E8%AE%BE%E8%AE%A1%E4%B8%8E%E5%AE%9E%E7%8E%B0%E8%B5%84%E6%BA%90%E5%8A%A0%E8%BD%BD%E5%99%A8-%E4%BB%8ESpring.xml%E8%A7%A3%E6%9E%90%E5%92%8C%E6%B3%A8%E5%86%8CBean%E5%AF%B9%E8%B1%A1.html)          | [small-spring-step-05](https://github.com/small-spring/small-spring-step-05) | [small-spring-step-05](https://gitee.com/small-spring/small-spring-step-05) |
|  07   | [所向披靡，实现应用上下文，自动识别、资源加载、扩展机制](https://bugstack.cn/spring/2021/06/17/%E7%AC%AC7%E7%AB%A0-%E6%89%80%E5%90%91%E6%8A%AB%E9%9D%A1-%E5%AE%9E%E7%8E%B0%E5%BA%94%E7%94%A8%E4%B8%8A%E4%B8%8B%E6%96%87-%E8%87%AA%E5%8A%A8%E8%AF%86%E5%88%AB-%E8%B5%84%E6%BA%90%E5%8A%A0%E8%BD%BD-%E6%89%A9%E5%B1%95%E6%9C%BA%E5%88%B6.html)          | [small-spring-step-06](https://github.com/small-spring/small-spring-step-06) | [small-spring-step-06](https://gitee.com/small-spring/small-spring-step-06) |
|  08   | [龙行有风，向虚拟机注册钩子，实现Bean对象的初始化和销毁方法](https://bugstack.cn/spring/2021/06/23/%E7%AC%AC8%E7%AB%A0-%E9%BE%99%E8%A1%8C%E6%9C%89%E9%A3%8E-%E5%90%91%E8%99%9A%E6%8B%9F%E6%9C%BA%E6%B3%A8%E5%86%8C%E9%92%A9%E5%AD%90-%E5%AE%9E%E7%8E%B0Bean%E5%AF%B9%E8%B1%A1%E7%9A%84%E5%88%9D%E5%A7%8B%E5%8C%96%E5%92%8C%E9%94%80%E6%AF%81%E6%96%B9%E6%B3%95.html)  | [small-spring-step-07](https://github.com/small-spring/small-spring-step-07) | [small-spring-step-07](https://gitee.com/small-spring/small-spring-step-07) |
|  09   | [虎行有雨，定义标记类型Aware接口，实现感知容器对象](https://bugstack.cn/spring/2021/06/23/%E7%AC%AC8%E7%AB%A0-%E9%BE%99%E8%A1%8C%E6%9C%89%E9%A3%8E-%E5%90%91%E8%99%9A%E6%8B%9F%E6%9C%BA%E6%B3%A8%E5%86%8C%E9%92%A9%E5%AD%90-%E5%AE%9E%E7%8E%B0Bean%E5%AF%B9%E8%B1%A1%E7%9A%84%E5%88%9D%E5%A7%8B%E5%8C%96%E5%92%8C%E9%94%80%E6%AF%81%E6%96%B9%E6%B3%95.html) | [small-spring-step-08](https://github.com/small-spring/small-spring-step-08) | [small-spring-step-08](https://gitee.com/small-spring/small-spring-step-08) |
|  10   | [横刀跃马，关于Bean对象作用域以及FactoryBean的实现和使用](https://bugstack.cn/spring/2021/06/30/%E7%AC%AC10%E7%AB%A0-%E6%A8%AA%E5%88%80%E8%B7%83%E9%A9%AC-%E5%85%B3%E4%BA%8EBean%E5%AF%B9%E8%B1%A1%E4%BD%9C%E7%94%A8%E5%9F%9F%E4%BB%A5%E5%8F%8AFactoryBean%E7%9A%84%E5%AE%9E%E7%8E%B0%E5%92%8C%E4%BD%BF%E7%94%A8.html) | [small-spring-step-09](https://github.com/small-spring/small-spring-step-09) | [small-spring-step-09](https://gitee.com/small-spring/small-spring-step-09) |
|  11   | [更上层楼，基于观察者实现，容器事件和事件监听器](https://bugstack.cn/spring/2021/07/07/%E7%AC%AC11%E7%AB%A0-%E6%9B%B4%E4%B8%8A%E5%B1%82%E6%A5%BC-%E5%9F%BA%E4%BA%8E%E8%A7%82%E5%AF%9F%E8%80%85%E5%AE%9E%E7%8E%B0-%E5%AE%B9%E5%99%A8%E4%BA%8B%E4%BB%B6%E5%92%8C%E4%BA%8B%E4%BB%B6%E7%9B%91%E5%90%AC%E5%99%A8.html) | [small-spring-step-10](https://github.com/small-spring/small-spring-step-10) | [small-spring-step-10](https://gitee.com/small-spring/small-spring-step-10) |
|  12   | [炉火纯青，基于JDK和Cglib动态代理，实现AOP核心功能](https://bugstack.cn/spring/2021/07/13/%E7%AC%AC12%E7%AB%A0-%E7%82%89%E7%81%AB%E7%BA%AF%E9%9D%92-%E5%9F%BA%E4%BA%8EJDK%E5%92%8CCglib%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86-%E5%AE%9E%E7%8E%B0AOP%E6%A0%B8%E5%BF%83%E5%8A%9F%E8%83%BD.html) | [small-spring-step-11](https://github.com/small-spring/small-spring-step-11) | [small-spring-step-11](https://gitee.com/small-spring/small-spring-step-11) |
|  13   | 待归档...                     | - | - |

## 参考

在资料整理的过程中，发现了两个非常优秀的手写Spring框架源码，tiny-spring、mini-spring，这两个简化版的Spring框架都实现了一个非常易懂、易学、易上手的源码教程，如果你已经有了一定的基础，那么可以直接阅读源码学习。地址：

- https://github.com/code4craft/tiny-spring
- https://github.com/DerekYRC/mini-spring
