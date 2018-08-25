package example

import scala.beans.BeanProperty
import scala.collection.mutable.ArrayBuffer

class Counter {
    private var value = 0
    
    def increment() {
        value += 1
    }

    def current() = value

    def isLess(other: Counter) = value < other.value
}


class Person {
    private var privateAge = 0

    def age = privateAge
    def age_=(newValue: Int) {
        if (newValue > privateAge) privateAge = newValue;
    }
}

class Person_5 {
    // 生成了四个方法: 1. name: String 2. name_=(newValue:String):Unit, getName():String, setName(newValue):Unit
    @BeanProperty var name: String = _
}

//构造函数
class Person_6 {
    private var name = ""
    private var age = 0

    def age_ = age
    def name_ = name

    def this(name: String) {
        this()
        this.name = name
    }

    def this(name: String, age: Int) {
        this(name)
        this.age = age
    }

}

class Person_7(val name:String = "", val age: Int = 0) {
    println("Just construct another person")
    def description = name + " is " + age + " years old"
}

class Network {
    class Member(val name: String) {
        val contacts = new ArrayBuffer[Network#Member]
    }

    private val members = new ArrayBuffer[Member]

    def join(name: String) = {
        val m = new Member(name)
        members += m
        m
    }

    def members_ = (this.members)
}



object Chapter5 extends App {
    val myCounter = new Counter
    myCounter.increment()
    println(myCounter.current())
    val yourCounter = new Counter
    println(myCounter isLess yourCounter)

    val fred = new Person
    fred.age = 30
    fred.age = 21
    println(fred.age)

    val newPerson = new Person_5
    newPerson.name = "Truman"
    println(newPerson.name)
    println(newPerson.getName())

    val p1 = new Person_6
    val p2 = new Person_6("Fred")
    val p3 = new Person_6("Fred, 42")

    printf("Name is %s; Age is %d \n", p1.name_, p1.age_)
    printf("Name is %s; Age is %d \n", p2.name_, p2.age_)
    printf("Name is %s; Age is %d \n", p3.name_, p3.age_)

    val p4 = new Person_7("Truman", 32)
    println(p4.description)

    val chatter = new Network
    val myFace = new Network

    val fred1 = chatter.join("Fred")
    val wilma = chatter.join("Wilma")
    fred1.contacts += wilma
    val barney = myFace.join("Barney")
    fred1.contacts += barney
    println(chatter.members_)
}









