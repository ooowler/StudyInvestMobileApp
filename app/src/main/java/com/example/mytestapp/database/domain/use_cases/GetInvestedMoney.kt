package com.example.mytestapp.database.domain.use_cases

fun getInvestedMoney(): Double {
    return (1..100).random().toDouble()
}
