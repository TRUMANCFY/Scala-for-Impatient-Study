package NLPeditDistance
import scala.collection.mutable.ArrayBuffer

class Matrix(val str1:String, val str2:String, val cutoffDistance:Int=1) {
    def isMatch():Boolean={
        val m:Int = this.str1.length();
        val n:Int = this.str2.length();
        var start:Int = 0;
        var end:Int = 0;

        var matrix = ArrayBuffer.fill(m+1, n+1)(0);
        matrix(0) = (0 to n).to[ArrayBuffer]

        for (i <- 1 to m) {
            matrix(i)(0) = i;
        }
        
        for (col <- 1 to n) {
            this.computeLastCol(this.str1, this.str2, matrix, col, m, n);
            start = math.min(m, math.max(1, col - this.cutoffDistance));
            end = math.max(1, math.min(m, col + this.cutoffDistance));
            var col_list = ArrayBuffer[Int]();
            for (k <- 1 to m) {
                col_list :+= matrix(k)(col);
            }
            println(col_list.mkString(" "))
            if (col_list.min > this.cutoffDistance) {
                return false;
            }
        }
        true;
    }
    
    def computeLastCol(str1:String, str2:String, matrix: => ArrayBuffer[ArrayBuffer[Int]], col:Int, m:Int, n:Int):Unit = {
        for (i <- 1 to m) {
            if (str1(i-1) == str2(col-1)) {
                matrix(i)(col) = matrix(i-1)(col-1);
            }

            else if (i >= 2 && col >= 2 && str1(i-1) == str2(col-2) && str1(i-2) == str2(col-1)) {
                matrix(i)(col) = 1 + math.min(matrix(i-2)(col-2), math.min(matrix(i-1)(col), matrix(i)(col-1)));
            }

            else {
                matrix(i)(col) = 1 + math.min(matrix(i-1)(col-1), math.min(matrix(i-1)(col), matrix(i)(col-1)));
            }
        }
    }
}

object EditDistance extends App {
    val s1 = "abba";
    val s2 = "cfd";

    val m = new Matrix(s1, s2);
    println(m.isMatch)
}