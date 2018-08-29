package Chapter8Exercise

// 1. 扩展如下的BankAccount类，新类CheckingAccount对每次存款和取款都收取1美元的手续费
class BankAccount(initialBalance:Double){
    private var balance = initialBalance
    def deposit(amount:Double) = { balance += amount; balance}
    def withdraw(amount:Double) = {balance -= amount; balance}
}

class CheckingAccount(initialBalance:Double) extends BankAccount(initialBalance) {
    override def deposit(amount:Double):Double = super.deposit(amount - 1)
    override def withdraw(amount:Double):Double = super.deposit(amount + 1)
}

// 2. 扩展前一个练习的BankAccount类，新类的SavingsAccount每个月都约利息产生（earnMonthlyInterest方法被调用），并且每月有三次免手续费的存款或取款。在earnMonthlyInterest方法中重置交易计数
class SavingsAccount(initialBalance:Double) extends BankAccount(initialBalance) {
    private var cnt = 3
    
    def earningMonthlyInterest() {
        cnt = 3
        super.deposit(1)
    }

    override def deposit(amount:Double):Double = {
        cnt -= 1
        if (cnt >= 0) super.deposit(amount) else super.deposit(amount - 1)
    }

    override def withdraw(amount:Double):Double = {
        cnt -= 1
        if (cnt >= 0) super.deposit(amount) else super.deposit(amount + 1)
    }
}

// 3 翻开你喜欢的Java或C++教科书，一定会找到用来讲解继承层级的实例，可能是员工，宠物，图形或类似的东西。用Scala来实现这个示例

// Written in Java
// class Art{
//     Art(){System.out.println("Art constructor");}
// }
 
// class Drawing extends Art{
//     Drawing() {System.out.println("Drawing constructor");}
// }
 
// public class Cartoon extends Drawing{
//     public Cartoon() { System.out.println("Cartoon constructor");}

// Written in Scala
class Art {
    println("Art Constructor")
}

class Drawing extends Art {
    println("Drawing Constructor")
}

class Cantoon extends Art {
    println("Cartoon Constructor")
}

// 4. 定义一个抽象类Item，加入方法price和description。SimpleItem是一个在构造器上给出价格和描述的物件。利用val重写def这个事实。Bundle是一个可以包含其他物件的物件。其价格是打包中所有物件的价格之和
// 同时提供一个将物件添加到打包当中的机制，以及一个合适的description方法
abstract class Item {
    def price():Double
    def description():String
}

class SimpleItem(val price:Double, val description:String) extends Item {}

class Bundle extends Item {
    val items = new collection.mutable.ArrayBuffer[Item]()

    def add_item(in:Item) {
        items += in
    }

    def price:Double = {
        var total = 0.0
        this.items.foreach(x => total+=x.price)
        total
    }

    def description:String = {
        this.items.mkString(" ")
    }
}

// 5. 设计一个Point类，其x和y坐标可以通过构造器提供。提供一个子类LabeledPoint，其构造器接受一个标签值和x，y坐标 比如 new LabeledPoint("Black Thursday", 1929, 230.07)
class Point(a:Int, b:Int) {
    println("Point x: " + a.toString + " Point y: " + b.toString)
}

class LabeledPoint(var label:String, var x:Int, var y:Int) extends Point(x, y) {
    println("LabeledPoint x: " + x.toString + " LabeledPoint y: " + y.toString)
    def description = "x: " + x + " y: " + y
}

// 6 定义一个抽象类Shape，一个抽象方法centerPoint，以及该抽象类的子类Rectangle和Circle。为子类提供合适的构造器，并重写centerPoint方法

// 7 提供一个Square类，扩展自java.awt.Rectangle并且是三个构造器：一个以给定的端点和宽度构造正方形，一个以(0,0)为端点和给定的宽度构造正方形，一个以(0,0)为端点,0为宽度构造正方形
import java.awt.{Point=>Point1, Rectangle}

class Square(point:Point1, width:Int) extends Rectangle(point.x, point.y, width, width) {
    def this() {
        this(new Point1(0,0), 0)
    }

    def this(width:Int) {
        this(new Point1(0,0), width)
    }
}

// 9 在8.10节的Creature类中，将val range替换成一个def。如果你在Ant子类中也用def的话会有什么效果？如果在子类中使用val又会有什么效果？为什么？
// Answer: def可以，但是val编译不过，因为val只能override没有参数的def，而在creature中参数是range

object Chapter8Exercise extends App {
    // 3
    val a1 = new Drawing
    val a2 = new Cantoon

    // 4
    val b1 = new Bundle
    val t1 = new SimpleItem(10, "1")
    val t2 = new SimpleItem(10, "2")
    println(b1.description)
    b1.add_item(t1)
    println(b1.description)
    b1.add_item(t2)
    println(b1.description)

    var p1 = new LabeledPoint("China", 1, 2)
    p1.x = 4
    println(p1.x + p1.y)
    println(p1.description)
}
