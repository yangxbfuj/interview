# interview

###### 1. activity 生命周期

###### 2. service 的基本使用

###### 3. handler

###### 4. Scroller 的使用方法

1. 在 View.computeScroll() 中判断 Scroller 的状态。
mScroller.computeScrollOffset() 是否滚动完毕。
2.this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY()); 进行滚动

###### 5. 帧动画

###### 6. 看下activity启动

###### 7. aidl

1. 定义好aidl接口后，编译器生成一个 AIDL.Stub 的抽象类，实现该类通过 Service 绑定即可。
2. 跨 app 的匿名 service 绑定，在 Intent 中传入 action 时，需要设置 packagename

###### 8. 自定义 binder

1. 其实就是 AIDL 的自我实现而已。设置一个继承 Binder 的类，在绑定 Service 时返回 Binder 对象实例，然后转换为客户端的本地对象。