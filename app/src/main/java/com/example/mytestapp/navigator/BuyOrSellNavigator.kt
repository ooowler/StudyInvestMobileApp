package com.example.mytestapp.navigator

fun getBuyOrSellRoute(name: String, price: Double, quantity: Int): String {
    return "buy_or_sell?name=$name&price=$price&quantity=$quantity"
}