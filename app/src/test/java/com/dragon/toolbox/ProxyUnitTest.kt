package com.dragon.toolbox

import org.junit.Test
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ProxyUnitTest {

    @Test
    fun proxy(){

    }

}


interface Person{
    fun drink()
    fun say()
}

class Student:Person{
    override fun drink() {
        println("student drink")
    }

    override fun say() {
        println("student say")
    }

}

class Deletegate(){

}