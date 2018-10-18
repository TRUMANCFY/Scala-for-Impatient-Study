package week2Exercise

object week2Exercise extends App {
    // Curried Function
    def mapReduce(f: Int=>Int, combine: (Int,Int) => Int, zero: Int)(a:Int, b:Int): Int = {
        if (a > b) zero
        else combine(f(a), mapReduce(f, combine, zero)(a+1, b))
    }

    def product(f: Int=>Int)(a: Int, b: Int): Int = mapReduce(f, (x:Int, y:Int)=>x*y, 1)(a, b)
    val res1:Int = product(x=>x*x)(3, 4)
    println("the result of curried function is")
    println(res1)

    // Fixed points
    import math.abs
    val tolerance = 0.00001
    def isCloseEnough(a: Double, b: Double): Boolean = {
        abs((a-b)/a) < tolerance
    }

    def findFixedPoints(f: Double=>Double) (firstGuess:Double):Double = {
        def iterate(guess: Double):Double = {
            val next = f(guess)
            if (isCloseEnough(guess, next)) next
            else iterate(next)
        }
        iterate(firstGuess)
    } 

    val res2:Double = findFixedPoints(x => 1 + x/2)(1)
    println("the result of the fixed point")
    println(res2)

    def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2
    
    def sqrt(x:Double) = findFixedPoints(averageDamp(y => x/y))(x)

    println("the result of sqrt")
    val res3:Double = sqrt(3)
    println(res3)
}
