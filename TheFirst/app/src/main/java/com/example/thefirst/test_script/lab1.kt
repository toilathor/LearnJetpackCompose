package com.example.thefirst.test_script

import com.example.thefirst.core.models.MessageModel
import com.example.thefirst.core.models.messageData
import kotlinx.serialization.json.Json

fun main1() {
    println("Hello Kotlin")

    val a = 1
    val b = 2

    val message: String = "Tổng 2 số a = $a và b = $b: ${sum(a, b)}"
    println(message)

    /// Arrays
    val array = arrayOf(1, 2, 3, 4, 5)
    println("Các phần tử trong mảng: ${array.forEach { e -> print("$e ") }}")
}

fun sum(a: Int, b: Int) : Int {
    return  a + b
}

fun main() {
    val obj = Json.decodeFromString<Array<MessageModel>>(messageData)
    println(obj.size)
}