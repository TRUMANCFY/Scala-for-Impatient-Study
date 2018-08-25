package example

object Hello extends App {
  println("Hello")

  /* * * * * * * * * *
    1. 编写一段代码，将a设置为一个n个随机整数的数组，要求随机数结余0（包含）和n（不包含）之间。
 * * * * * * * * * */
  println("Q1")

  def makeArr(n: Int) = {
    val a = new Array[Int](n)
    for (i <- a) yield scala.util.Random.nextInt(n)
  }
  makeArr(10).foreach(println)
  println("++++++++++++++++++++++++")

/* * * * * * * * * *
2. 编写一个循环，将整数数组中相邻的元素互换。例如，Array(1,2,3,4,5)经过互换后得到Array(2,1,4,3,5)
 * * * * * * * * * */
  println("Q2")

  def exchange(arr: Array[Int]) = {
    for (i <- 0 until (arr.length-1, 2)) {
        val t = arr(i)
        arr(i) = arr(i+1)
        arr(i+1) = t
    }
  }

  val a = Array(1, 2, 3, 4, 5)
  exchange(a)
  a.foreach(println)

  println("+++++++++++++++++++++++++")
/* * * * * * * * * *
3. 重复前一个练习，不过这一次生成一个新的值交换过的数组。用for...yield
(note: 习题3是返回一个新数组；习题2是直接修改原数组）
 * * * * * * * * * */
  println("Q3")

  def exchange_in(arr: Array[Int]) = {
    for (i <- 0 until arr.length) yield {
        if (i < (arr.length - 1) && i%2 == 0) arr(i+1)
        else if (i < (arr.length - 1) && i%2 == 1) arr(i-1)
        else arr(i)
    }
  }

  val a_1 = Array(1,2,3,4,5)
  val b_1 = exchange_in(a_1)
  b_1.foreach(println)
  println("+++++++++++++++++++++++++")

  /* * * * * * * * * *
4. 给定一个整数数组，产出一个新的数组，包含原数组中的所有正值，以原有顺序排列；之后的元素是所有零或负值，按原有顺序排列
 * * * * * * * * * */
  println("Q4")

  def sigNumArr(arr: Array[Int]) = {
    val buf = new scala.collection.mutable.ArrayBuffer[Int]()
    buf ++= (for (i <- arr if i > 0) yield i)
    buf ++= (for (i <- arr if i == 0) yield i)
    buf ++= (for (i <- arr if i < 0) yield i)
    buf.toArray
  }

  val a_2 = Array(1,-2,0,-3,0,4,-2,5,-1)
  val b_2 = sigNumArr(a_2)
  b_2.foreach(println)

  println("++++++++++++++++++++++++++")
  /* * * * * * * * * *
5. 如何计算Array[Double]的平均值
 * * * * * * * * * */
 println("Q5")
 def mean(arr: Array[Double]) = {
    arr.sum / arr.length
 }

 val a_3 = Array(1.0,0,-2,0,-3,0,4,-2,5,1)
 println(mean(a_3))
 println("+++++++++++++++++++++++++++")


/* * * * * * * * * *
6. 如何重新组织Array[Int]的元素将它们以反序排列？对于ArrayBuffer[Int]你又会怎么做呢？
 * * * * * * * * * */
 println("Q6")
 // Array revert
 def reverse_Array(arr: Array[Int]) = {
    for (i <- 0 until (arr.length / 2)) {
        val t = arr(i)
        arr(i) = arr(arr.length - 1 - i)
        arr(arr.length - 1 - i) = t
    }
 }

 val a_4 = Array(1, -2, 0, -3, 0, 4, -2, 5, -1)
 reverse_Array(a_4)
 a_4.foreach(println)
 println("++++++++++++++++++++++++++++")

 // ArrayBuffer revert
 import scala.collection.mutable.ArrayBuffer

 def reverse_Buffer(arr: ArrayBuffer[Int]) = {
    arr.reverse
 }
 val b_4 = ArrayBuffer[Int]()
 b_4 ++= a_4
 reverse_Buffer(b_4)
 b_4.foreach(println)

 println("++++++++++++++++++++++++++++")

 /* * * * * * * * * *
7. 编写一段代码，产出数组中的所有值，去掉重复项。
 * * * * * * * * * */
 println("Q7")
 def removeDuplicate(arr: Array[Int]) = {
    val b = scala.collection.mutable.ArrayBuffer[Int]()
    b ++= arr.distinct
    b.toArray
 }

 val a_5 = Array(1,1,2,2,2,3,4,5,5)
 val b_5 = removeDuplicate(a_5)
 b_5.foreach(println)

}















