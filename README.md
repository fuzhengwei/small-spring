# Spring手撸专栏

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
    <a href="https://bugstack.cn" style="text-decoration:none"><img src="https://github.com/fuzhengwei/small-spring/blob/main/docs/_media/bugstack-pdf.png?raw=true" width="128px"></a>
</div>
<br/>  

---

本仓库以 Spring 源码学习为目的，通过手写简化版 Spring 框架，了解 Spring 核心原理。

在手写的过程中会简化 Spring 源码，摘取整体框架中的核心逻辑，简化代码实现过程，保留核心功能，例如：IOC、AOP、Bean生命周期、上下文、作用域、资源处理等内容实现。

[`小傅哥`](https://bugstack.cn/)，之所以开始撸Spring源码，主要就是因为在编写[《面经手册》](https://bugstack.cn/itstack/interview.html)时，涉及到的Spring源码都会写很多的文字描述、绘制冗长的流程图稿、做不少的内容铺垫，但对于新人来说想直接学习这部分内容仍是非常困难的，那么现在为了让我以及更多的伙伴能有一个学习的`抓手`，我们来一起研究研究什么是快乐星球！

## 目录

- [x] 第 1 章：开篇介绍，手写Spring能给你带来什么？
- [x] 第 2 章：Spring Bean 容器创建 | [small-spring-step-01](https://github.com/small-spring/small-spring-step-01)
- [x] 第 3 章：Spring Bean 的定义和注册 | [small-spring-step-02](https://github.com/small-spring/small-spring-step-02)
- [x] 第 4 章：Spring Bean 构造函数实例化策略以及Cglib动态生成使用 | [small-spring-step-03](https://github.com/small-spring/small-spring-step-03)
- [x] 第 5 章：给 Bean 对象填充属性信息 | [small-spring-step-04](https://github.com/small-spring/small-spring-step-04)
- [ ] 第 6 章：待归档...

## 参考

在资料整理的过程中，发现了两个非常优秀的手写Spring框架源码，tiny-spring、mini-spring，这两个简化版的Spring框架都实现了一个非常易懂、易学、易上手的源码教程，如果你已经有了一定的基础，那么可以直接阅读源码学习。地址：

- [https://github.com/code4craft/tiny-spring](https://github.com/code4craft/tiny-spring)
- [https://github.com/DerekYRC/mini-spring](https://github.com/DerekYRC/mini-spring)
