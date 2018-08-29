package Chapter9

object Chapter9 extends App {
    // 9.1 读取行
    println("===================9.1=======================")
    import java.nio.file.Paths
    println("Current Directory")
    println(Paths.get(".").toAbsolutePath)

    import scala.io.Source
    val source1 = Source.fromFile("/Users/caifengyu/Desktop/scala/foo-build/src/main/scala/example/my_file.txt", "UTF-8")
    val lineIterator = source1.getLines
    for (l <- lineIterator) println(l)
    println(Source.fromFile("/Users/caifengyu/Desktop/scala/foo-build/src/main/scala/example/my_file.txt", "UTF-8").mkString)
    source1.close() // 记得close

    // 9.2 读取字符
    println("===================9.2=======================")
    val source2 = Source.fromFile("/Users/caifengyu/Desktop/scala/foo-build/src/main/scala/example/my_file.txt", "UTF-8")
    // 读到o为止 另scala中没有break
    val iter = source2.buffered
    import scala.util.control.Breaks._
    breakable {
        while (iter.hasNext) {
            if (iter.head == 'o') {
                println(iter.next())
                break
            }
            println(iter.next())
        }
    }
    source2.close()

    // 9.3 读取词法单元和数字
    println("===================9.3=======================")
    val source3 = Source.fromFile("/Users/caifengyu/Desktop/scala/foo-build/src/main/scala/example/number.txt", "UTF-8")
    val tokens = source3.mkString.split(" ")
    println(tokens.length)
    tokens.foreach(println(_))
    val number1 = for (w <- tokens) yield w.toDouble
    println(number1.mkString)

    println("how old are u")
    val age = readInt() // readDouble readLong
    source3.close()

    // 9.4 从URL或其他源读取
    println("===================9.4=======================")
    // val source = Source.fromURL("http://horstmann.com", "UTF-8)
    val source4 = Source.fromString("Hello World")
    val source4_ = Source.stdin //从标准读入数据

    println(source4.mkString)
    source4.close()

    // 9.5 读取二进制文件
    // Scala没有提供读取二进制文件的方法 需要Java类的库
    // val file = new File(filename)
    // val in = new FileInputStream(file)
    // val byte = new Array[Byte](file.length.toInt)
    // in.read(byte)
    // in.close

    // 9.6 写入文本文件
    // Scala 没有内建的对写入文件的支持，要写入文本文件
    println("===================9.6=======================")
    import java.io.PrintWriter
    val out = new PrintWriter("/Users/caifengyu/Desktop/scala/foo-build/src/main/scala/example/numbers.txt")
    for (i <- 1 to 100) out.println(i)
    out.close()

    // 9.7 访问目录
    // Scala 没有正式的用来访问某个目录中的所有文件，或者递归遍历
    println("===================9.7=======================")
    import java.io.File
    def subdirs(dir:File): Iterator[File] = {
        val children = dir.listFiles.filter(_.isDirectory)
        children.toIterator ++ children.toIterator.flatMap(subdirs _)
    }

    val file1 = new File("/Users/caifengyu/Desktop/scala/foo-build/src/main/scala/")
    for (d <- subdirs(file1)) println(d)

    // 9.8 序列化与反序列化

    // 9.9 进程控制
    println("===================9.9=======================")
    import sys.process._
    "ls -al .." ! // 执行的是从字符串到ProcessBuilder的隐形转化
    // (!!输出会以字符串的形式返回)
    val result = "ls -al .." !!

    import sys.process._
    "ls -al .." #| "grep Mind" !

    // 把输出重定向到文件使用 #> 操作符
    // "ls -al .." #> new File("output.txt") !
    // 要追加到文件末尾而不是从头覆盖的话使用 #>>
    // 要把文件内容作为输入使用 #<
    // "grep sec" #< new File("output.txt")
    // 还可以从URL重定向输入
    // "grep Scala" #< new URL("www.horstmann.com/index.html")

    // 建造ProcessBuilder: val p = Process(cmd, new File(dirName), ("LANG", "en_US"))

    // 9.10 正则表达式
    // scala.util.matching.Regex类让这件事情变得简单 (.r构造)
    println("===================9.10=======================")
    val numPattern = "[0-9]+".r
    // 如果正则表达式包含反斜杠或者引号的话，最好使用“原始”字符串语法"""..."""
    // 例如
    val wsnumwsPattern = """\s+[0-9]+\s+""".r

    //  findAllIn: 查找所有匹配项
    for (matchString <- numPattern.findAllIn("99 bottles, 98 bottles")) {
        println(matchString)
    }

    val matches = numPattern.findAllIn("99 bottles, 98 bottles").toArray
    println(matches.mkString(" "))

    // findFirstIn: 查找首个匹配项
    val m1 = wsnumwsPattern.findFirstIn("99 bottles, 98 bottles")
    println(m1)

    // findPrefixOf：检查某个字段开始的时候是否能匹配上
    val m2 = numPattern.findPrefixOf("99 bottles, 98 bottles")
    val m3 = wsnumwsPattern.findPrefixOf("99 bottles, 98 bottles")
    
    println(m2)
    println(m3)

    // replaceFirstIn, replaceAllIn
    val m4 = numPattern.replaceFirstIn("99 bottle, 98 bottle", "XX")
    val m5 = numPattern.replaceAllIn("99 bottles, 98 bottles", "XX")

    println(m4)
    println(m5)

    println("===================9.11=======================")
    // 9.11 正则表达式组
    val numitemPattern = "([0-9]+) ([a-z]+)".r
    val numitemPattern(num, item) = "99 bottles"
    println(num)
    println(item)

    for (numitemPattern(num, item) <- numitemPattern.findAllIn("99 bottles, 98 bottles")) {
        println(num)
        println(item)
    }
}