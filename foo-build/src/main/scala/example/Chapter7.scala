package com {
    package horstmann {
        package impatient {
            class Employee {
                var salary = salary_set

                // 这种办法在选择的包中可见
                private[impatient] def description = "salary is " + this.salary

                def this(s:Int) {
                    this()
                    this.salary = s
                }
            }
        }
    }
}

// 7.5 包对象：包可以包含类和对象，但是不能包含函数和变量的定义，这是java虚拟机的局限，包对象就是为了解决这样的局限
package com.horstmann{
    package object impatient{
        // 这种修饰符定义了在horstmann类的下面是可以见的
        private[horstmann] val salary_1 = 10
        val salary_set = 100
    }
}

package com{
    package object horstmann{
        val salary_2 = com.horstmann.impatient.salary_1
    }
}

object Chapter7 extends App {
    val e = new com.horstmann.impatient.Employee
    println(e.salary)
    println(com.horstmann.salary_2)

    import java.awt.Color._
    val c1 = RED
    println(c1)
    val c2 = decode("#ff0000")
    println(c2)

    // 7.8 任何地方都可以声明引入：在scala中，import语句可以出现在任何地方，并不限于文件的顶部，import语句的效果可以一直延伸到该语句的块末尾
    // 7.9 重命名和隐藏方法
    // 选取器
    import java.awt.{Color, Font}

    // 还允许重命名
    import java.util.{HashMap => JavaHashMap} // JavaHashMap: java.util.HashMap
    import scala.collection.mutable._
    
    // 隐藏某个变量，避免重复
    import java.util.{HashMap => _, _} // 这样HashMap无二义地指向了scala.collection.mutable.HashMap
    import scala.collection.mutable._

    // 7.10 隐式引入：每个scala程序都隐式地以下代码开始的:
    import java.lang._
    import scala._
    import Predef._

    // 后面的引入可以覆盖之前的引入 举例来说, scala.StringBuilder会覆盖java.lang.StringBuilder 而不是与之冲突
}