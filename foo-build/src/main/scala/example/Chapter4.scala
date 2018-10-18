package example

object Chapter4 extends App {
  println("Chapter4")
/*
1. 设置一个映射，其中包含你想要的一些装备，以及它们的价格。然后构建另外一个映射，采用同一组键，但在价格上打九折。
* */
  println("Q1")
  val map = Map("cup" -> 19, "book" -> 30, "Macbook Pro" -> 9980, "iPad" -> 3200)
  val newMap = for ((k, v) <- map) yield (k, v * 0.9)
  Console.println(newMap)

  println("+++++++++++++++++++++++++")
/*
2. 编写一段程序，从文件中读取单词。用一个可变映射来清点每个单词出现的频率。读取这些单词的操作可以使用java.util.Scanner:
val in = new java.util.Scanner(new java.io.File("myfile.txt"))
while(in.hasNext()) 处理 in.next() 或者翻到第9章看看更Scala的做法。
最后，打印出所有单词和它们出现的次数。
* */
  println("Q2")
  val source = scala.io.Source.fromFile("src/main/scala/example/my_file.txt").mkString
  // println(source)
  val tokens = source.split("\\s+")
  var map_2 = Map[String, Int]()

  for (key <- tokens) {
    map_2 += (key -> (map_2.getOrElse(key, 0) + 1))
  }

  println(map_2.mkString(","))
  println("++++++++++++++++++++++++")

  /*
3. 重复前一个练习，这次用不可变的映射
 * */
  println("Q3")
  import scala.io.Source
  import scala.collection.mutable.HashMap

  val map_3 = new HashMap[String, Int]

  for (key <- tokens) {
    map_3(key) = map_3.getOrElse(key, 0) + 1
  }

  println(map_3.mkString(","))
  println("++++++++++++++++++++++++")

  /*
4. 重复前一个练习，这次使用已排序的映射，以便单词可以按顺序打印出来
 * */
  println("Q4")
  import scala.io.Source
  import scala.collection.SortedMap

  var map_4 = SortedMap[String, Int]()

  for (key <- tokens) {
    map_4 += (key -> (map_4.getOrElse(key, 0) + 1))
  }

  println(map_4.mkString(","))
  println("++++++++++++++++++++++++")

  /*
5. 重复前一个练习，这次使用java.util.TreeMap并使之适用于Scala API
 * */
  println("Q5")

  import scala.io.Source
  import scala.collection.mutable.Map
  import scala.collection.JavaConversions.mapAsScalaMap
  import java.util.TreeMap

  val map_5: Map[String, Int] = new TreeMap[String, Int]

  for (key <- tokens) {
    map_5(key) = map_5.getOrElse(key, 0) + 1
  }
  println(map_5.mkString(","))
  println("++++++++++++++++++++++++")

  /*
6. 定义一个链式哈希映射,将"Monday"映射到 java.util.Calendar.MONDAY,依次类推加入其他日期。展示元素是以插入的顺序被访问的
 * */
  println("Q6")
  import scala.collection.mutable.LinkedHashMap
  import java.util.Calendar

  val map_6 = new LinkedHashMap[String, Int]
  map_6 += ("Monday" -> Calendar.MONDAY)
  map_6 += ("Tuesday" -> Calendar.TUESDAY)
  map_6 += ("Wednesday" -> Calendar.WEDNESDAY)
  map_6 += ("Thursday" -> Calendar.THURSDAY)
  map_6 += ("Friday" -> Calendar.FRIDAY)
  map_6 += ("Saturday" -> Calendar.SATURDAY)
  map_6 += ("Sunday" -> Calendar.SUNDAY)

  println(map_6.mkString(","))
  println("++++++++++++++++++++++++")

  /*
8. 编写一个函数minmax(values:Array[Int]),返回数组中最小值和最大值的对偶
 * */
  println("Q8")
  def minmax(values: Array[Int]) = {
    (values.min, values.max)
  }

  val a = Array(1, 2, 3, 4, 5)
  println(minmax(a))
  println("++++++++++++++++++++++++")

  /*
9. 编写一个函数Iteqgt(values:Array[int],v:Int),返回数组中小于v,等于v和大于v的数量，要求三个值一起返回
 * */

  println("Q9")
  def Iteqgt(values: Array[Int], v: Int) = {
    (values.count(_ < v), values.count(_ == v), values.count(_ > v))
  }

  val values = Array(-5, -3, -1, 0, 0, 1, 2, 3, 4, 5)
  val v = 0
  println(Iteqgt(values, v))
  println("++++++++++++++++++++++++")

  /*
10. 当你将两个字符串拉链在一起，比如"Hello".zip("World")，会是什么结果？想出一个讲得通的用例
 * */
  println("Q10")
  val worldSet:Seq[(Char, Char)] = "Hello".zip("World")
  println(worldSet)
  
}
