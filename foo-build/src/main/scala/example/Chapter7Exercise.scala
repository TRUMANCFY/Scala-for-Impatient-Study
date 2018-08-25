// 7.1 编写示例程序，展示为什么
// package com.horstmann.impatient
// 不同于
// package com
// package horstmann
// package impatient

package com {
    class T1() {}
    package horstmann {
        class T2() {}
        package impatient {
            class T3() {}
        }
    }
}

// Error occurs: 说明不能在上一级在scope之外啊
// package com.horstmann.impatient {
//     class T4(t1: T1, t2: T2) {}
// }

// 7.3 编写一个包random，加入函数nextInt():Int, nextDouble():Double, setSeed(seed:Int):Unit
// 生成随机数的算法采用线性同余生成器

package object random {
    import scala.math._
    var seed: Int= _
    val a = BigDecimal(1664525)
    val b = BigDecimal(1013904223)
    val n = 32

    def nextInt(): Int = {
        val temp = (seed * a + b) % BigDecimal(2).pow(n)
        seed = temp.toInt
        seed
    }

    def nextDouble(): Double = {
        val temp = (seed * a + b) % BigDecimal(2).pow(n)
        seed = temp.toInt
        temp.toDouble
    }
}

// 7.4 在你看来scala的设计者为什么要提供package object语法而不是简单的让你将函数和变量添加到包里呢？
// Answer：因为JVM不支持包中

// 7.5 private[com] def giveRaise(rate: Double)的含义是什么？有什么用么？
// Answer: 除了com可以访问giveRaise，其他包不能访问

// 7.7 将7.6的练习中的所有引入语句缩小到最小的域里

// 7.8 以下代码的作用是什么?这是个好主意么？
// import java._
// import javax._
// 作用是倒入java和javax下面所有的类, 但是java和javax下面都没有类，所以代码无效

object Chapter7Exercise extends App {
    // 7.3
    (1 to 10).foreach(x => println(x))
    (1 to 10).foreach(x => println(random.nextInt()))
    (1 to 10).foreach(x => println(random.nextDouble()))

    // 7.6 编写一段程序，将Java哈希映射中的所有元素拷贝到Scala哈希映射。用引入语句重新命名两个类
    import java.util.{HashMap => JavaHashMap}
    import collection.mutable.{HashMap => ScalaHashMap}

    val javaMap = new JavaHashMap[Int, String]

    javaMap.put(1, "One")
    javaMap.put(2, "Two")
    javaMap.put(3, "Three")
    javaMap.put(4, "Four")

    val scalaMap = new ScalaHashMap[Int, String]
    for (key <- javaMap.keySet().toArray) {
        scalaMap += (key.asInstanceOf[Int] -> javaMap.get(key))
    }
    println(scalaMap.mkString(" "))

    // 7.9 编写一段程序，引入java.lang.System类，从user.name系统属性读取用户名，从Console对象读取一个密码,如果密码不是”secret”
    // 则在标准错误流中打印一个消息；如果密码是”secret”，则在标准输出流中打印一个问候消息。不要使用任何其他引入，也不要使用任何限定词(带句点的那种)
    var password = Console.readLine()
    if (password == "secret") println("Hello world!")

    // 7.10 处理StringBuilder还有那些java.lang的成员被scala包覆盖着
}