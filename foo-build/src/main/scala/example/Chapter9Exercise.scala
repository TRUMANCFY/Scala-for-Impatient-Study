package Chapter9Exercise

object Chapter9Exercise extends App {
    // 1 编写一小段Scala代码
    println("============== 1 ==============")
    import scala.io._
    import java.io.PrintWriter
    import scala.collection.mutable.ArrayBuffer
    import scala.math._

    val fileName:String = "/Users/caifengyu/Desktop/scala/foo-build/src/main/scala/example/my_file.txt"
    val newFileName:String = "/Users/caifengyu/Desktop/scala/foo-build/src/main/scala/example/my_file_new.txt"
    val numberFile:String = "/Users/caifengyu/Desktop/scala/foo-build/src/main/scala/example/numbers.txt"
    val newNumberFile:String = "/Users/caifengyu/Desktop/scala/foo-build/src/main/scala/example/numbers_new.txt"
    def revertFile(fileString:String, newFileString:String) {
        val source1 = Source.fromFile(fileString)
        val textArray = source1.getLines.toArray
        val source2 = new PrintWriter(newFileString)
        for (d <- textArray.reverse) source2.println(d)
        // for (d <- source1) println(d)
        source1.close()
        source2.close()
    }
    revertFile(fileName, newFileName)

    // 2 编写一段Scala程序，从一个带有制表符的文件读取内容，将每个指标服饰替换成一组空格，使得制表符隔开的n列仍然保持纵向对其，并将结果写入同一个文件里
    println("============== 2 ==============")
    def exercise2(srcFile:String) {
        var source:BufferedSource = null
        
        try {
            var lines = Source.fromFile(srcFile).mkString
            lines = lines.replaceAll("\t", " "*4)
            var writein = new PrintWriter(srcFile)
            writein.print(lines)
            writein.close()
        }
        catch {
            case _: Exception => println("Error occured")
        }
        finally {
            if (source != null) {
                println(source)
                source.close()
            }
        }
    }
    
    // 3 编写一小段Scala代码，从一个文件读取内容并把所有字符数大于12的单词打印到控制台
    println("============== 3 ==============")
    def exercise3(fileName:String):Unit = Source.fromFile(fileName).mkString.split("[ \n]").foreach(x => if (x.length > 4) println(x))
    exercise3(fileName)

    // 4 编写Scala程序，从包含浮点数的文本文件中读取内通打印出文本中所有浮点数之和，平均值，最大值和最小值
    println("============== 4 ==============")
    def exercise4(fileName:String):Unit = {
        var container:ArrayBuffer[Int] = new ArrayBuffer[Int]()

        val source = Source.fromFile(fileName)
        val textArray = source.mkString.split("[\n ]").toArray
        for (i <- textArray) container += i.toInt
        println("Sum: " + container.sum)
        println("Average: " + container.sum/container.length.toFloat) // 注意此处int/int仍然还是int
        println("Max: " + container.max)
        println("Min: " + container.min)
    }
    exercise4(numberFile)

    // 5 编写scala程序，向文件中写入2的n次方及其倒数，指数n从0到20
    def exercise5(fileName:String):Unit = {
        val out = new PrintWriter(fileName)
        for (i <- 0 to 20) {
            println(pow(2, i) + "\t" + pow(2, 0-i))
        }
    }
    exercise5(newNumberFile)
}