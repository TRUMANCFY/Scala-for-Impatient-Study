package example

// 6.2 静态方法的伴生对象; 类和他的伴生对象可以相互访问私有特性，他们必须村雨同一个源文件中
object Account {
    private var lastNumber = 0
    def newUniqueNumber() = { lastNumber += 1; lastNumber }
}

class Account {
    val id = Account.newUniqueNumber()
    private var balance = 0.0
    def deposit(amount:Double) { balance += amount }
}

// 6.3 扩展类或特质的对象
abstract class UndoableAction(val desciption: String) {
  def undo(): Unit
  def redo(): Unit
}

object DoNothingAction extends UndoableAction("Do nothing") {
  override def undo() {}
  override def redo() {}
}

// 6.4 apply 方法
class Account_4 (val id: Int, val initialBalance: Double) {
  private var balance = initialBalance
}

// 伴生对象
object Account_4 {
  def apply(initialBalance: Double) = new Account_4(1, initialBalance)
}

// 6.6 枚举
object TrafficLightColor extends Enumeration {
  type TrafficLightColor = Value
  // val Red, Yellow, Green = Value
  val Red = Value(0, "Stop")
  val Yellow = Value(10) // 名称为Yellow
  val Green = Value("GO") // ID为在前一个的基础上加1
}

// 伴生对象例子
import scala.collection.mutable.Map

class ChecksumAccumulator {
  private var sum = 0
  
  def add(b: Byte) {
    sum += b
  }

  def checksum(): Int = ~(sum & 0xFF) + 1
}

object ChecksumAccumulator {
  private val cache = Map[String, Int]()
  
  def calculate(s: String): Int = {
    if (cache.contains(s)) cache(s)
    else {
      val acc = new ChecksumAccumulator
      for (c <- s) acc.add(c.toByte)
      val cs = acc.checksum()
      cache += (s -> cs)
      println("s: " + s + "cs :" + cs)
      cs
    }
  }
}


object Chapter6 extends App {
  val actions = Map("open" -> DoNothingAction, "save" -> DoNothingAction)
  val acct = Account_4(1000.0)
  println(acct.initialBalance)

  import TrafficLightColor._
  println(Red)
  println(TrafficLightColor.Yellow)

  import TrafficLightColor._
  def doWhat(color:TrafficLightColor) = {
    if (color == Red) "stop"
    else if (color == Yellow) "hurry up"
    else "go"
  }
  for (c <- TrafficLightColor.values) println(c.id + ": " + c)

  println(TrafficLightColor(0))
  // println(TrafficLightColor.withName("Red"))
}
