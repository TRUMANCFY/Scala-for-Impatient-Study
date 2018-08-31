package Chapter10

object Chapter10 extends App {
    // 10.1 为什么没有多重继承：菱形继承问题（C++的解决办法是虚拟积类）https://www.cnblogs.com/youxin/archive/2013/03/15/2961219.html
    // Java 的解决办法是 创造了接口，接口只能包含抽象方法，不能包含抽象字段
    // Scala提供了“特质”而不是接口，特质同时可以拥有抽象方法和具体方法

    // 10.2 当作接口使用的特质
    trait Logger {
        def log(msg: String) // 抽象的方法
    }

    class ConsoleLogger extends Logger {
        def log(msg: String) { println(msg) }
    }

    // 重写特质的对象方法时不需要加override关键字段
    // 如果需要的特质不止一个，可以with关键字来添加额外的特质：
    // class ConsoleLogger extends Logger with Cloneable with Serializable
    // Logger with Cloneable with Serializable

    // 10.3 带有具体实现的特质
    trait ConsoleLogger3 {
        def log(msg: String) { println(msg) }
    }

    class Account {
        var balance:Int = 100
    }

    class SavingsAccount extends Account with ConsoleLogger3 {
        def withdraw(amount: Int) {
            if (amount > balance) log("Insufficient funds")
            else balance -= amount
        }
    }

    // 10.4 带有特质的对象
    trait Logger4 {
        def log(msg: String) // 抽象的方法
    }

    trait ConsoleLogger4 extends Logger4 {
        override def log(msg: String) { println(msg) }
    }

    val acct = new SavingsAccount with Logger4

    // 10.5 叠加在一起的特质
    trait TimestampLogger extends ConsoleLogger3 {
        override def log(msg:String) {
            super.log(new java.util.Date() + " " + msg)
        }
    }
    
    // 非常有趣的是 特质往往从最后一个开始处理 也可以通过super[..]的方法来指定特质
    val acct1 = new SavingsAccount with TimestampLogger
    println(acct1.balance)
    println(acct1.withdraw(1110))
    println(acct1.balance)

    // 10.6 在特质中重写抽象的方法
    trait Logger6 {
        def log(msg:String) // 抽象的方法
    }

    trait TimestampLogger6 extends Logger6 {
        abstract override def log(msg:String) {
            super.log(new java.util.Date() + " " + msg)
        }
    }

    // 10.7 当作富接口使用的特质
    // 一些特质可以被经常调用
    // trait Logger7 {
    //    def log(msg:String) {
    //        def log(msg:String)
    //        def info(msg:String) { log("INFO: " + msg) }
    //        def warn(msg:String) { log("WARN: " + msg) }
    //        def severe(msg: String) { log("SEVERE" + msg) }
    //    }
    // }

    // 10.8 特质中的具体字段
    // 特质中的字段可以是具体的 也可以是抽象的
    // 但是 该字段只是简简单单的放入在类中 下面举例
    // class A{}
    // trait a {var i}
    // class B extends A with a {var j}
    // i并不在超类A，而是直接在B上,和j排在一起

    // 10.9 特质中的抽象字段
    // 特质中未被初始化的字段在具体的子类中必须要被重写

    // 10.10 特质构造顺序
    // 举例
    // class SavingsAccout extends Aount with FileLogger with Shortlogger
    // 1. Account(超类) 2. Logger(一个特质的父特质) 3. FileLogger(第一个特质) 4. Shortlogger(第二个特质，他的父特质logger已经构造)5. SavingsAccount(类)

    // 10.11 初始化特质中的字段
    // 特质不能有构造器参数 每个特质有一个无参数构造器

    trait FileLogger extends Logger {
        val filename: String
        abstract override def log(msg:String) { println(msg); }
    }

    // 这个不行，问题出在构造顺序上FileLogger构造器先于子类的构造器执行
    // val acct2 = new SavingsAccount with FileLogger {
    //     val filename = "myapp.log"
    // }
 }