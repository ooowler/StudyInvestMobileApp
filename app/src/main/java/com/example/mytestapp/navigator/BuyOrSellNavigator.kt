package com.example.mytestapp.navigator

fun getBuyOrSellRoute(uid: Long, name: String, price: Double, quantity: Int): String {
    return "buy_or_sell?uid=$uid&name=$name&price=$price&quantity=$quantity"
}

fun getBuyOrSellRoute(): String {
    return "buy_or_sell?uid=&name=&price=&quantity="
}