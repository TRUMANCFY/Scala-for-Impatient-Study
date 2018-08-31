package example

class Counter_1 {
    private var value = 0

    def increment() {
        if(value < Int.MaxValue) value += 1 else value
    }

    def current() = value

    def setValue(v:Int) {
        this.value = v
    }
    def isLess(other: Counter_1) = value < other.value

}

class BankAccount(var balance:Int = 0) {
    def deposit(v:Int){
        this.balance += v
        printf("Balance is %d \n", this.balance)
    }

    def withdraw(v:Int){
        if (v <= this.balance) balance -= v else println("Not enough balance")
        printf("Balance is %d \n", this.balance)
    }
}

class Time(var hours:Int = 0, var minutes:Int = 0) {
    def before(other: Time):Boolean = {
        if (this.hours > other.hours) false
        else if (this.hours < other.hours) true
        else {
            if (this.minutes > other.minutes) false
            else true
        }
    }
}

class Time_4(var hours:Int=0, var minutes:Int=0) {
    def before(other:Time_4):Boolean = {
        this.hours < other.hours || (this.hours == other.hours && this.minutes < other.minutes)
    }

    def show():String = {
        (hours * 60 + minutes).toString
    }
}

class PersonNew(var age:Int = 0) {
    age = if (age < 0) 0 else age
}

class PersonSplit(var name: String) {
    val firstName:String = {val tokens = name.split("\\s+"); tokens.head}
    val lastName:String = {val tokens = name.split("\\s+"); tokens.last}

    def show() {
        printf("firstName is %s \n", firstName)
        printf("lastName is %s \n", lastName)
    }
}

class Car(val mk:String, val typeName:String, val year:Int = -1, var carLic:String = "") {
    def this(mk:String, tname:String) {this(mk, tname, -1, "")}
    def this(mk:String, tname:String, y:Int) {this(mk, tname, y, "")}
    def this(mk:String, tname:String, cl:String) {this(mk, tname, -1, cl)}
}

object Chapter5Exercise extends App {
    /*
    1. 改进5.1节的Counter类,让它不要在Int.MaxValue时变成负数
    * */
    println("Q1")
    val c1 =  new Counter_1
    c1.setValue(Int.MaxValue-1)
    println(c1.current)
    c1.increment()
    println(c1.current)
    c1.increment()
    println(c1.current)

/*
2. 编写一个BankAccount类，加入deposit和withdraw方法，和一个只读的balance属性
* */
    println("++++++++++++++++++++")
    println("Q2")
    val bank = new BankAccount(100)
    bank.deposit(100)
    bank.withdraw(100)
    bank.withdraw(200)

    /*
3. 编写一个Time类，加入只读属性hours和minutes，和一个检查某一时刻是否早于另一时刻的方法 before(other:Time):Boolean。Time对象应该以new Time(hrs,min)方式构建。其中hrs以军用时间格式呈现(介于0和23之间)
* */
    println("++++++++++++++++++++")
    println("Q3")
    val time1 = new Time(1, 1)
    val time2 = new Time(2, 0)
    val time3 = new Time(1, 3)
    println(time1.before(time2))
    println(time1.before(time3))
    println(time2.before(time3))

/*
4. 重新实现前一个类中的Time类，将内部呈现改成午夜起的分钟数(介于0到24*60-1之间)。不要改变公有接口。也就是说，客户端代码不应因你的修改而受影响
* */
    println("++++++++++++++++++++")
    println("Q4")
    val time4 = new Time_4(23, 49)
    println(time4.show)

/*
6. 在5.2节的Person类中提供一个主构造器,将负年龄转换为0
* */
    println("++++++++++++++++++++")
    println("Q6")
    val person_new = new PersonNew(-10)
    println(person_new.age)

/*
7. 编写一个Person类，其主构造器接受一个字符串，该字符串包含名字，空格和姓，如new Person("Fred Smith")。提供只读属性firstName和lastName。主构造器参数应该是var,val还是普通参数？为什么？
* */
    println("++++++++++++++++++++")
    println("Q7")
    val person_split = new PersonSplit("Truman Cai")
    person_split.show()

/* * * * * * * * * *
8. 创建一个Car类，以只读属性对应制造商，型号名称，型号年份以及一个可读写的属性用于车牌。提供四组构造器。每个构造器fc都要求制造商和型号为必填。型号年份和车牌可选，如果未填，则型号年份为-1，车牌为空串。你会选
择哪一个作为你的主构造器？为什么？
 * * * * * * * * * */
    println("++++++++++++++++++++")
    println("Q8")

/* * * * * * * * * *
10. 考虑如下的类
class Employ(val name:String, var salary:Double){
    def this(){this("John Q. Public",0.0)}
}
重写该类,使用显示的字段定义，和一个缺省主构造器。你更倾向于使用哪种形式？为什么？
 * * * * * * * * * */
    println("++++++++++++++++++++")
    println("Q9")
    class Employ() {
        val name:String = "John Q. Public"
        val salary:Double = 0.0
    }
    //然而第一种更为scala的风格
}
