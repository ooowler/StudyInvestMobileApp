@file:Suppress("NAME_SHADOWING")

package com.example.mytestapp.presentation.investments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//Icon(
//imageVector = Icons.Default.Close,
//contentDescription = "Delete",
//tint = Color.Red,
//modifier = Modifier
//.size(48.dp)
//.clickable {
//    deleteBlock()
//}
//.padding(8.dp)
//)

//fun deleteBlock() {
//    println("удалили блок")
//}
private fun isValidInput(textValue: String, numericValue: Double, intValue: Int): Boolean {
    return textValue.isNotEmpty() && numericValue != 0.0 && intValue != 0
}

@Composable
fun AddOrSellStockScreen(
    stockName: String = "Input action name",
    stockPrice: Double = 0.0,
    stockQuantity: Int = 0,
    navController: NavController
) {

    var stockName by remember { mutableStateOf(stockName) }
    var stockPrice by remember { mutableStateOf(stockPrice) }
    var stockQuantity by remember { mutableStateOf(stockQuantity) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TextField для ввода имени акции
        TextField(
            value = stockName,
            onValueChange = { stockName = it },
            placeholder = { Text("Имя акции", color = Color.Green) },
            label = { Text("Имя акции") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // TextField для ввода цены акции
        TextField(
            value = stockPrice.toString(),
            onValueChange = { stockPrice = it.toDoubleOrNull() ?: stockPrice },
            label = { Text("Цена акции") },
            placeholder = { Text("Цена акции", color = Color.Gray) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // TextField для ввода количества акций
        TextField(
            value = stockQuantity.toString(),
            onValueChange = { stockQuantity = it.toIntOrNull() ?: 0 },
            label = { Text("Количество акций") },
            placeholder = { Text("Количество акций", color = Color.Gray) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        isValidInput(stockName, stockPrice, stockQuantity)

        // Кнопка для подтверждения действия
        Button(
            onClick = {
                onTradeComplete(stockName, stockPrice, stockQuantity)
                navController.navigate("investments")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Подтвердить")
        }

        // Кнопка для отмены действия
        Button(
            onClick = {
                onTradeCancel()
                navController.navigate("investments")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Отмена")
        }
    }
}

fun onTradeComplete(stockName: String, stockPrice: Double, stockQuantity: Int): String {
    val res = "$stockName продал по цене $stockPrice в количестве $stockQuantity"
    println(res)
    return res

}

fun onTradeCancel(): String {
    return "canceled"
}