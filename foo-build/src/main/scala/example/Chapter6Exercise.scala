package example

object Chapter6Exercise extends App {
    // 6.1 
    object Conversions {
        def inchesToCentimeters(inc: Int):Double = {
            inc * 2.54
        }

        def gallonsToLiters(src:Int):Double = {
            src * 3.78541178
        }

        def milesToKilometers(src:Int):Double = {
            src * 1.609344
        }
    }

    println("example 01: " + Conversions.inchesToCentimeters(10))
    println("example 01: " + Conversions.gallonsToLiters(10))
    println("example 01: " + Conversions.milesToKilometers(10))

    // 6.2
    abstract class UnitConversion() {
        def conversions(src:Int): Double;
    }

    // 以下链接讨论的是new和object之间的关系
    // https://stackoverflow.com/questions/16182735/scala-new-vs-object-extends
    object InchesToCentimeters extends UnitConversion {
        override def conversions(src: Int): Double = src * 2.54
    }

    object GallonsToLiters extends UnitConversion {
        override def conversions(src: Int): Double = src * 3.78541178
    }

    object MilesToKilometers extends UnitConversion {
        override def conversions(src: Int): Double = src * 1.609344
    }

    println("example 02: " + InchesToCentimeters.conversions(10))
    println("example 02: " + GallonsToLiters.conversions(10))
    println("example 02: " + MilesToKilometers.conversions(10))

    // 6.3
    import java.awt.Point

    object Origin extends Point {
    }

    println("example 03: " + Origin.toString())

    // 6.4
    object Point {
        def apply(x: Int, y: Int):Point = {
            val res = new Point(x, y)
            res
        }
    }

    println("example 04: " + Point(10, 10).toString())

    // 6.5
    val argc = "scala reverse hello world"
    val argc_split = argc.split(" ")
    val argc_add_split = for (t <- argc_split) yield t + " "
    println("example 05: ")
    argc_add_split.reverse.map(print _)
    println("")

    // 6.6
    object CardSet extends Enumeration {
        type CardSet = Value
        val Clover = Value("C")
        val Dia = Value("D")
        val Heart = Value("H")
        val Spade = Value("S")
    }
    
    for (c <- CardSet.values) println("example 06: " + c.id + c)

    // 6.7
    def CardisRed(color: CardSet.CardSet): Boolean = {
        color match {
            case CardSet.Clover => false
            case CardSet.Spade => false
            case _ => true
        }
    }

    println("example 07: Clover " + CardisRed(CardSet.Clover))
    println("example 07: Heart " + CardisRed(CardSet.Heart)) 

    // 测试Chapter7.scala是否work
    val test = new com.horstmann.impatient.Employee
    println(test.salary)
}