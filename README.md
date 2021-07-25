# :seedling: Spring 手撸专栏 - 易学、好写、能懂！

>小傅哥，一线互联网 Java 工程师、架构师，开发过交易、营销类项目，实现过运营、活动类项目，设计过中间件，组织过系统重构，编写过技术专利。不仅从事业务系统的开发工作，也经常做一些字节码插桩类的设计和实现，对架构的设计和落地有丰富的经验。在热衷于Java语言的同时，也喜欢研究中继器、I/O板卡、C#和PHP，是一个技术活跃的折腾者。
>[:pencil2: 虫洞 · 科技栈，博主](https://bugstack.cn)，[:blue_book: 《重学Java设计模式》图书作者](https://item.jd.com/13218336.html)

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

## ⛳ **目录**

- [学习说明 🍁`(学习套路&源码使用)`](https://github.com/fuzhengwei/small-spring#bookmark-%E5%AD%A6%E4%B9%A0%E8%AF%B4%E6%98%8E)
- [章节目录](https://github.com/fuzhengwei/small-spring#pencil-%E7%AB%A0%E8%8A%82%E7%9B%AE%E5%BD%95)
- [我的书籍](https://github.com/fuzhengwei/small-spring#books-%E6%88%91%E7%9A%84%E4%B9%A6%E7%B1%8D)
- [联系作者](https://github.com/fuzhengwei/small-spring#paw_prints-%E8%81%94%E7%B3%BB%E4%BD%9C%E8%80%85)
- [参考资料](https://github.com/fuzhengwei/small-spring#tulip-%E5%8F%82%E8%80%83%E8%B5%84%E6%96%99)

## :bookmark: 学习说明

本仓库以 Spring 源码学习为目的，通过手写简化版 Spring 框架，了解 Spring 核心原理。

在手写的过程中会简化 Spring 源码，摘取整体框架中的核心逻辑，简化代码实现过程，保留核心功能，例如：IOC、AOP、Bean生命周期、上下文、作用域、资源处理等内容实现。

[`小傅哥`](https://bugstack.cn/)，之所以开始撸Spring源码，主要就是因为在编写[《面经手册》](https://bugstack.cn/itstack/interview.html)时，涉及到的Spring源码都会写很多的文字描述、绘制冗长的流程图稿、做不少的内容铺垫，但对于新人来说想直接学习这部分内容仍是非常困难的，那么现在为了让我以及更多的伙伴能有一个学习的`抓手`，我们来一起研究研究什么是快乐星球！

---

1. 此专栏为实战编码类资料，在学习的过程中需要结合文中每个章节里，要解决的**目标**，进行的思路**设计**，带入到编码实操过程。在学习编码的同时也最好理解关于这部分内容为什么这样的实现，它用到了哪样的设计模式，采用了什么手段做了什么样的职责分离。只有通过这样的学习才能更好的理解和掌握 Spring 源码的实现过程，也能帮助你在以后的深入学习和实践应用的过程中打下一个扎实的基础。

2. 另外此专栏内容的学习上结合了[设计模式](https://item.jd.com/13218336.html)，下对应了[SpringBoot 中间件设计和开发](https://juejin.cn/book/6940996508632219689)，所以读者在学习的过程中如果遇到不理解的设计模式可以翻阅相应的资料，在学习完 Spring 后还可以结合中间件的内容进行练习。

3. **源码**：此专栏涉及到的源码已经全部整合到当前工程下，可以与章节中对应的案例源码一一匹配上。大家拿到整套工程可以直接运行，也可以把每个章节对应的源码工程单独打开运行。

4. 如果你在学习的过程中遇到什么问题，包括：不能运行、优化意见、文字错误等任何问题都可以提交issue，也可以联系作者：`小傅哥` 的微信，`fustack`

5. 在专栏的内容编写中，每一个章节都提供了清晰的设计图稿和对应的类图，所以学习过程中一定不要只是在乎代码是怎么编写的，更重要的是理解这些设计的内容是如何来的。包括不限于如下案例：


    |   Spring 容器设计图稿   |
    | ---- |
    |   ![](https://github.com/fuzhengwei/small-spring/blob/main/docs/assets/img/spring-3-01.png)   |
    |   ![](https://github.com/fuzhengwei/small-spring/blob/main/docs/assets/img/spring-3-02.png)   |


## :pencil: 章节目录

- [x] [`第 01 章：开篇介绍，我要带你撸 Spring 啦！`](https://bugstack.cn/spring/2021/05/16/%E7%AC%AC1%E7%AB%A0-%E5%BC%80%E7%AF%87%E4%BB%8B%E7%BB%8D-%E6%89%8B%E5%86%99Spring%E8%83%BD%E7%BB%99%E4%BD%A0%E5%B8%A6%E6%9D%A5%E4%BB%80%E4%B9%88.html)
- [x] [`第 02 章：小试牛刀，实现一个简单的Bean容器`](https://bugstack.cn/spring/2021/05/20/%E7%AC%AC2%E7%AB%A0-%E5%B0%8F%E8%AF%95%E7%89%9B%E5%88%80-%E5%AE%9E%E7%8E%B0%E4%B8%80%E4%B8%AA%E7%AE%80%E5%8D%95%E7%9A%84Bean%E5%AE%B9%E5%99%A8.html)
- [x] [`第 03 章：初显身手，运用设计模式，实现 Bean 的定义、注册、获取`](https://bugstack.cn/spring/2021/05/23/%E7%AC%AC3%E7%AB%A0-%E5%88%9D%E6%98%BE%E8%BA%AB%E6%89%8B-%E8%BF%90%E7%94%A8%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F-%E5%AE%9E%E7%8E%B0-Bean-%E7%9A%84%E5%AE%9A%E4%B9%89-%E6%B3%A8%E5%86%8C-%E8%8E%B7%E5%8F%96.html)
- [x] [`第 04 章：崭露头角，基于Cglib实现含构造函数的类实例化策略`](https://bugstack.cn/spring/2021/05/30/%E7%AC%AC4%E7%AB%A0-%E5%B4%AD%E9%9C%B2%E5%A4%B4%E8%A7%92-%E5%9F%BA%E4%BA%8ECglib%E5%AE%9E%E7%8E%B0%E5%90%AB%E6%9E%84%E9%80%A0%E5%87%BD%E6%95%B0%E7%9A%84%E7%B1%BB%E5%AE%9E%E4%BE%8B%E5%8C%96%E7%AD%96%E7%95%A5.html)
- [x] [`第 05 章：一鸣惊人，为Bean对象注入属性和依赖Bean的功能实现`](https://bugstack.cn/spring/2021/06/02/%E7%AC%AC5%E7%AB%A0-%E4%B8%80%E9%B8%A3%E6%83%8A%E4%BA%BA-%E4%B8%BABean%E5%AF%B9%E8%B1%A1%E6%B3%A8%E5%85%A5%E5%B1%9E%E6%80%A7%E5%92%8C%E4%BE%9D%E8%B5%96Bean%E7%9A%84%E5%8A%9F%E8%83%BD%E5%AE%9E%E7%8E%B0.html)
- [x] [`第 06 章：气吞山河，设计与实现资源加载器，从Spring.xml解析和注册Bean对象`](https://bugstack.cn/spring/2021/06/09/%E7%AC%AC6%E7%AB%A0-%E6%B0%94%E5%90%9E%E5%B1%B1%E6%B2%B3-%E8%AE%BE%E8%AE%A1%E4%B8%8E%E5%AE%9E%E7%8E%B0%E8%B5%84%E6%BA%90%E5%8A%A0%E8%BD%BD%E5%99%A8-%E4%BB%8ESpring.xml%E8%A7%A3%E6%9E%90%E5%92%8C%E6%B3%A8%E5%86%8CBean%E5%AF%B9%E8%B1%A1.html)
- [x] [`第 07 章：所向披靡，实现应用上下文，自动识别、资源加载、扩展机制`](https://bugstack.cn/spring/2021/06/17/%E7%AC%AC7%E7%AB%A0-%E6%89%80%E5%90%91%E6%8A%AB%E9%9D%A1-%E5%AE%9E%E7%8E%B0%E5%BA%94%E7%94%A8%E4%B8%8A%E4%B8%8B%E6%96%87-%E8%87%AA%E5%8A%A8%E8%AF%86%E5%88%AB-%E8%B5%84%E6%BA%90%E5%8A%A0%E8%BD%BD-%E6%89%A9%E5%B1%95%E6%9C%BA%E5%88%B6.html)
- [x] [`第 08 章：龙行有风，向虚拟机注册钩子，实现Bean对象的初始化和销毁方法`](https://bugstack.cn/spring/2021/06/23/%E7%AC%AC8%E7%AB%A0-%E9%BE%99%E8%A1%8C%E6%9C%89%E9%A3%8E-%E5%90%91%E8%99%9A%E6%8B%9F%E6%9C%BA%E6%B3%A8%E5%86%8C%E9%92%A9%E5%AD%90-%E5%AE%9E%E7%8E%B0Bean%E5%AF%B9%E8%B1%A1%E7%9A%84%E5%88%9D%E5%A7%8B%E5%8C%96%E5%92%8C%E9%94%80%E6%AF%81%E6%96%B9%E6%B3%95.html)
- [x] [`第 09 章：虎行有雨，定义标记类型Aware接口，实现感知容器对象`](https://bugstack.cn/spring/2021/06/23/%E7%AC%AC8%E7%AB%A0-%E9%BE%99%E8%A1%8C%E6%9C%89%E9%A3%8E-%E5%90%91%E8%99%9A%E6%8B%9F%E6%9C%BA%E6%B3%A8%E5%86%8C%E9%92%A9%E5%AD%90-%E5%AE%9E%E7%8E%B0Bean%E5%AF%B9%E8%B1%A1%E7%9A%84%E5%88%9D%E5%A7%8B%E5%8C%96%E5%92%8C%E9%94%80%E6%AF%81%E6%96%B9%E6%B3%95.html)
- [x] [`第 10 章：横刀跃马，关于Bean对象作用域以及FactoryBean的实现和使用`](https://bugstack.cn/spring/2021/06/30/%E7%AC%AC10%E7%AB%A0-%E6%A8%AA%E5%88%80%E8%B7%83%E9%A9%AC-%E5%85%B3%E4%BA%8EBean%E5%AF%B9%E8%B1%A1%E4%BD%9C%E7%94%A8%E5%9F%9F%E4%BB%A5%E5%8F%8AFactoryBean%E7%9A%84%E5%AE%9E%E7%8E%B0%E5%92%8C%E4%BD%BF%E7%94%A8.html)
- [x] [`第 11 章：更上层楼，基于观察者实现，容器事件和事件监听器`](https://bugstack.cn/spring/2021/07/07/%E7%AC%AC11%E7%AB%A0-%E6%9B%B4%E4%B8%8A%E5%B1%82%E6%A5%BC-%E5%9F%BA%E4%BA%8E%E8%A7%82%E5%AF%9F%E8%80%85%E5%AE%9E%E7%8E%B0-%E5%AE%B9%E5%99%A8%E4%BA%8B%E4%BB%B6%E5%92%8C%E4%BA%8B%E4%BB%B6%E7%9B%91%E5%90%AC%E5%99%A8.html)
- [x] [`第 12 章：炉火纯青，基于JDK和Cglib动态代理，实现AOP核心功能`](https://bugstack.cn/spring/2021/07/13/%E7%AC%AC12%E7%AB%A0-%E7%82%89%E7%81%AB%E7%BA%AF%E9%9D%92-%E5%9F%BA%E4%BA%8EJDK%E5%92%8CCglib%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86-%E5%AE%9E%E7%8E%B0AOP%E6%A0%B8%E5%BF%83%E5%8A%9F%E8%83%BD.html)
- [x] [`第 13 章：行云流水，把AOP动态代理，融入到Bean的生命周期`](https://bugstack.cn/spring/2021/07/22/%E7%AC%AC13%E7%AB%A0-%E8%A1%8C%E4%BA%91%E6%B5%81%E6%B0%B4-%E6%8A%8AAOP%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86-%E8%9E%8D%E5%85%A5%E5%88%B0Bean%E7%9A%84%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F.html)
- [ ] 第 14 章：待归档...

## :books: 我的书籍

#### - [小傅哥的《重学 Java 设计模式》](https://item.jd.com/13218336.html) ⭐⭐⭐⭐

>本书是作者`小傅哥`，投入50天时间，从互联网实际业务开发中抽离出，交易、营销、秒杀、中间件、源码等22个真实业务场景，编写了18万字271页的实战型Java编程资料。如果书中含有不易理解的内容，一定是作者在编写的过程中缺少必要的描述和严格的校准，感谢把你的意见或者疑问提交给我，也欢迎与我多一些交互，互相进步共同成长。

#### - [小傅哥的《SpringBoot 中间件设计和开发》](https://juejin.cn/book/6940996508632219689) ⭐⭐⭐⭐⭐
>全网唯一一次手把手教你造轮子、写中间件，因为这样的技术离P7最近、离架构师最近、离高薪资最近！小册16个中间件实现，包括测试工程等共计30个代码库，每一章节都会对应有一个中间件的设计和实现，为了便于读者快速有效的学习小册中的技术内容，在小册的每一章节中都涵盖；开篇引导、需求背景、方案设计、技术实现、测试验证和文末总结。

#### - [小傅哥的《Java 面经手册》](https://download.csdn.net/download/Yao__Shun__Yu/14932325) ⭐⭐⭐⭐

>全书共计 5 章 29 节，417页11.5万字，耗时 4 个月完成。涵盖数据结构、算法逻辑、并发编程、JVM以及简历和互联网大厂面试等内容。但此书并不是单纯的面试题，也不是内卷八股文。而是从一个单纯的和程序员有关的数学知识点开始，深入讲解 Java 的核心技术。并且每一章节都配有实践验证的源码，可以对照着一起撸才更有感觉！

#### - [小傅哥的《字节码编程》](http://book.bugstack.cn/#s/51Es_z_Q) ⭐⭐⭐

>让人怪不好意思的，说是出书有点膨胀，毕竟这不是走出版社的流程，选题、组稿、编著、审读、加工到出版发行。但全书共计107页，11万7千字，20个章节涵盖三个字节码框架(ASM、Javassist、Byte-budy)和JavaAgent使用并附带整套案例源码！


## :paw_prints: 联系作者

- **加群交流**

    本群的宗旨是给大家提供一个良好的技术学习交流平台，所以杜绝一切广告！由于微信群人满 100 之后无法加入，请扫描下方二维码先添加作者 “小傅哥” 微信(fustack)，备注：加群。
    
    <img src="https://itedus.cn/_media/fustack.png?x-oss-process=style/may" width="180" height="180"/>

- **公众号(bugstack虫洞栈)**

    沉淀、分享、成长，专注于原创专题案例，以最易学习编程的方式分享知识，让自己和他人都能有所收获。目前已完成的专题有；Netty4.x实战专题案例、用Java实现JVM、基于JavaAgent的全链路监控、手写RPC框架、DDD专题案例、源码分析等。
    
    <img src="https://itedus.cn/_media/qrcode.png?x-oss-process=style/may" width="180" height="180"/>

## :tulip: 参考资料

在资料整理的过程中，发现了两个非常优秀的手写 Spring 框架源码，tiny-spring、mini-spring，这两个简化版的Spring框架都实现了一个非常易懂、易学、易上手的源码教程，如果你已经有了一定的基础，那么可以直接阅读源码学习。地址：

- https://github.com/code4craft/tiny-spring
- https://github.com/DerekYRC/mini-spring
