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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mytestapp.database.domain.model.Investment

@Composable
fun AddOrSellStockScreen(
    uid: Long,
    stockName: String? = null,
    stockPrice: String? = null,
    stockQuantity: String? = null,
    navController: NavController,
    viewModel: ShowInvestmentsViewModel = hiltViewModel()
) {

    var stockName by remember { mutableStateOf(stockName) }
    var stockPrice by remember { mutableStateOf(stockPrice) }
    var stockQuantity by remember { mutableStateOf(stockQuantity) }

    var nameIsValid by remember { mutableStateOf(true) }
    var priceIsValid by remember { mutableStateOf(true) }
    var quantityIsValid by remember { mutableStateOf(true) }

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
                nameIsValid = isAlphaNumeric(stockName) && ((stockName?.length ?: 0) < 15)
            },
            placeholder = {
                Text("Apple")
            },
            colors = TextFieldDefaults.colors(if (nameIsValid) Color.Black else Color.Red),
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

                try {
                    stockPrice?.toDouble()
                    priceIsValid = true
                } catch (e: NumberFormatException) {
                    priceIsValid = false
                }
            },
            colors = TextFieldDefaults.colors(if (priceIsValid) Color.Black else Color.Red),
            label = { Text("Цена акции") },
            placeholder = {
                Text(
                    "100.00",
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // TextField для ввода количества акций
        TextField(
            value = stockQuantity.toString(),
            onValueChange = {
                stockQuantity = it

                try {
                    stockQuantity?.toInt()
                    quantityIsValid = true
                } catch (e: NumberFormatException) {
                    quantityIsValid = false
                }
            },
            label = { Text("Количество акций") },
            placeholder = {
                Text(
                    "10",
                )
            },
            colors = TextFieldDefaults.colors(if (quantityIsValid) Color.Black else Color.Red),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )


        // Кнопка для подтверждения действия
        Button(
            onClick = {
                if (nameIsValid && priceIsValid && quantityIsValid) {
                    insertInvestment(uid, stockName, stockPrice, stockQuantity, viewModel)
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


fun insertInvestment(
    uid: Long,
    stockName: String?,
    stockPrice: String?,
    stockQuantity: String?,
    viewModel: ShowInvestmentsViewModel
) {
    val invest = Investment(
        uid = uid,
        name = stockName.toString(),
        price = stockPrice!!.toDouble(),
        count = stockQuantity!!.toInt(),
    )
    viewModel.insertInvest(invest)
}

fun onTradeDelete(): String {

    return "deleted"
}

fun onTradeCancel(): String {
    return "canceled"
}

fun isAlphaNumeric(input: String?): Boolean {
    return input?.matches("[a-zA-Z0-9]+".toRegex()) == true
}

