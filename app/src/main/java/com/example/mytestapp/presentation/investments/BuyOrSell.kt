@file:Suppress("NAME_SHADOWING")

package com.example.mytestapp.presentation.investments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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

private fun isValidInput(text: String?, price: String?, count: String?): Boolean {
    try {
        count?.toInt()
    } catch (e: NumberFormatException) {
        return false
    }

    return (text != "" && price != "" && count != "") &&
            (price?.toDoubleOrNull()!! > 0) &&
            (count?.toInt()!! > 0)
}

@Composable
fun AddOrSellStockScreen(
    stockName: String? = null,
    stockPrice: String? = null,
    stockQuantity: String? = null,
    navController: NavController
) {

    var stockName by remember { mutableStateOf(stockName) }
    var stockPrice by remember { mutableStateOf(stockPrice) }
    var stockQuantity by remember { mutableStateOf(stockQuantity) }

    var nameError by remember { mutableStateOf(false) }
    var priceError by remember { mutableStateOf(false) }
    var quantityError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TextField для ввода имени акции
        TextField(
            value = stockName ?: "",
            onValueChange = {
                stockName = it
                nameError = stockName.isNullOrBlank()
            },
            placeholder = {
                Text("Имя акции")
            },
            colors = TextFieldDefaults.colors(Color.Red),
            label = { Text("Имя акции") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )


        // TextField для ввода цены акции
        TextField(
            value = stockPrice.toString(),
            onValueChange = { it ->
                stockPrice = it
                priceError = it != ""
            },
            label = { Text("Цена акции") },
            placeholder = {
                Text(
                    "Цена акции",
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(if (priceError) Color.Red else Color.Transparent)
        )

        // TextField для ввода количества акций
        TextField(
            value = stockQuantity.toString(),
            onValueChange = {
                stockQuantity = it
                quantityError = it != ""
            },
            label = { Text("Количество акций") },
            placeholder = {
                Text(
                    "Количество акций",
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(if (quantityError) Color.Red else Color.Transparent)
        )


        // Кнопка для подтверждения действия
        Button(
            onClick = {
                if (isValidInput(stockName, stockPrice, stockQuantity)) {
                    onTradeComplete(stockName, stockPrice, stockQuantity)
                    navController.navigate("investments")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Подтвердить")
        }

        // Кнопка для удаления
        Button(
            onClick = {
                onTradeDelete()
                navController.navigate("investments")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Удалить")
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


fun onTradeComplete(stockName: String?, stockPrice: String?, stockQuantity: String?): String {
    val res = "$stockName продал по цене $stockPrice в количестве $stockQuantity"
    return res

}

fun onTradeDelete(): String {
    return "deleted"
}

fun onTradeCancel(): String {
    return "canceled"
}

