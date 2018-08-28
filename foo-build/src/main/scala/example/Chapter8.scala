package Chapter8
// 8.1 扩展类: 与java模式相似使用关键字extends
class Person {
    val name:String = "Truman"
    // 和java一样 可以声明final。这样的话该类就不能被扩展，final的字段是不可变的，类似scala中的val
    def show() = "Name is " + this.name
}

class Employee extends Person {
    // 8.2 重写方法
    // 改动可以override 但是需要follow一下的原则
    // 1. 子类的方法要覆盖父类的方法，必须写override
    // 2. 子类中的属性要覆盖父类中的val, 必须写override
    // 3. 父类中的var不可被override

    override val name:String = "New Truman"
    var salary:Double = 0.0
    // 在scala中使用关键字super调用父类的函数
    override def show() = super.show() + " and salary is " + this.salary.toString
}

// 8.5
class Person1(var name:String = "Truman", var age:Int = 10) {}

class Employee1(name:String, age:Int, val salary:Double = 0.0) extends Person1(name, age) {}

// 8.6 重写字段：可以用另一个同名的val字段重写一个val（或者不带参数的def）
// def只能重写另一个def；
// val只能重写另一个val或不带参数的def；
// var只能重写另一个抽象的var

// 8.8 抽象类: 可以用abstract关键字来标记不能被实例化的类，通常是因为它的某个或者某几个方法没有被完整定义
abstract class Person2(val name: String) {
    def id:Int
}

class Employee2(name: String) extends Person2(name) {
    def id = name.hashCode // 不需要override关键字
}

// 8.9 抽象字段：类还可以拥有抽象字段（抽象字段就是一个没有初始值的字段）
abstract class Person3 {
    val id: Int // 没有初始化-这是一个带有抽象的getter方法的抽象字段
    var name: String // 另一个抽象字段，带有抽象的getter和setter方法
}

class Employee3(val id:Int) extends Person3 {
    var name = "" // 和具体的name属性
}

// 8.10 构造顺序和提前定义
class Creature {
    val range:Int = 10
    val env: Array[Int] = new Array[Int](range)
}

class Ant extends Creature {
    override val range = 2
}

// 这样会有问题，env的range会产生歧义，有以下这样几种解决办法
// 1. 将val声明为final 很安全但是不够灵活
// 2. 在超类中将val声明为lazy，很安全但是不够高效
// 3. 在子类中进行提前定义
class Ant1 extends {
    override val range = 2
} with Creature


object Chapter8 extends App {
    var a = new Person
    var b = new Employee
    println(a.name)
    println(b.salary)
    println(a.show)
    println(b.show)

    // 8.3 类型检查和转换
    // p.isInstanceOf[Person]检查，如果p是Person或者Person的子类返回true 否则返回false
    // p.asInstanceOf[Person]检查，如果p是Perosn或者Person的子类返回Person，否则返回null
    // p.getClass == classOd[Employee] 可以检查p是否是Employee 而不是他的子类 # classOf在scala.Predef里面已经定义了 不需要在override
    var p1 = new Person
    var p2 = new Employee
    println("Person is Person: " + p1.isInstanceOf[Person] + " 返回内容：" + p1.asInstanceOf[Person].toString)
    println("Person is Employee: " + p1.isInstanceOf[Employee] + " 返回内容：" + "NULL")
    println("Employee is Person: " + p2.isInstanceOf[Person] + " 返回内容：" + p2.asInstanceOf[Person].toString)
    println("Employee is Employee: " + p2.isInstanceOf[Employee] + " 返回内容：" + p2.asInstanceOf[Employee].toString)

    // 8.4 受保护的字段和方法
    // protected: 可以被子类访问，但是不能在其他位置被看到
    // protected的成员对于类所属的包而言是不可见的，需要protect[this],类似于之前的private[this]

    // 8.5 超类的构造
    // 类有一个主构造器和任意数量的辅助构造器 每个辅助构造器都必须以之前定义的辅助构造器或者主构造器开始， 所以这就意味着辅助构造器永远不可能直接调用超类的构造器
    // 所以 子类的辅助构造器只能调用主构造器，只有主构造器才能调用超类的构造器
    // Example: class Employee(name: String, age: Int, val salary: Double) extends Person(name, age)
    val p3 = new Person1()
    println(p3.name + " " + p3.age)

    val p4 = new Employee1("Fengyu", 22)
    println(p4.name + " " + p4.age)

    // 8.7 匿名子类：和java一样 可以通过包含带有定义或重写的代码块的方式创建一个匿名的子类
    val alien = new Person1("Fred") {
        def greeting = "Greetings"
    }

    def meeting(p: Person1{def greeting: String}) {
        println(p.name + " says: " + p.greeting)
    }

    meeting(alien)

    // 8.8
    val p5 = new Employee2("Truman New")
    println(p5.id)

    // 8.9 同时也可以用匿名类型来定制抽象字段
    val fred1 = new Person3 {
        val id = 1792
        var name = "Fred"
    }

    println("name: " + fred1.name + " id: " + fred1.id)

    // 8.10
    val ant1 = new Ant
    println(ant1.env)

    val ant2 = new Ant1
    println(ant2.env)

    // 8.11 Unit和Any的概念完全不一样，将Unit转化为()
    def printAny(x: Any) { println(x) }
    def printUnit(x: Unit) { println(x) }

    printAny("Hello")
    printUnit("Hello")

    // 8.12 对象的相等性, 根据不同类的情况可以重新定义equals
    // final override def equals(other: Any) = {
    //  val that = other.asInstatnceOf[Item]
    //  if (that == null) false
    //  else description == that.description && price == that.price
    // }
}