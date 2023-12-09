package com.example.mytestapp.utils

fun round(num: Double, digits: Int): Double {
    return "%.${digits}f".format(num).toDouble()
}