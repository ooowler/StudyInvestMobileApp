@file:Suppress("UNUSED_EXPRESSION")

package com.example.mytestapp.presentation.main

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mytestapp.R
import kotlin.random.Random

@SuppressLint("UnrememberedMutableState")
@Composable
fun StockBlock(
    navController: NavController
) {
    val sortOptions = listOf("Total", "Quantity", "Price")
    var sort by remember { mutableStateOf("Total") }
    val invested by remember { mutableStateOf(100) }
    val total by remember { mutableStateOf(20) }
    val profit by remember { derivedStateOf { total - invested } }
    val profitColor by remember { derivedStateOf { if (profit >= 0) Color.Green else Color.Red } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 1 строка
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "portfolio")
            val borderWidth = 4.dp
            Image(
                painter = painterResource(R.drawable.img),
                contentDescription = "cat",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .border(
                        BorderStroke(borderWidth, Color.Yellow),
                        CircleShape
                    )
                    .padding(borderWidth)
                    .clip(CircleShape)
            )
        }

        // 2 строка
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StockStat("Invested", invested)
                StockStat("Total", total)
                StockStat("Profit", profit, profitColor, showSign = true)
            }
        }

        // 3 строка
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            var expanded by remember { mutableStateOf(false) }
            Box {
                Text(
                    text = "sort: $sort",
                    modifier = Modifier
                        .clickable { expanded = true }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    sortOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                sort = option
                                expanded = false
                            }
                        )
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    modifier = Modifier.clickable { navController.navigate("investments") })
            }
        }

        val stockDataList = List(5) { index ->
            StockData(
                name = "Stock $index",
                price = round(3.14 * Random.nextInt(5, 100), 3),
                quantity = Random.nextInt(1, 11)
            )
        }

        if (sort == "Total") {
            stockDataList.sortedByDescending { it.price * it.quantity }
        } else if (sort == "Quantity") {
            stockDataList.sortedBy { it.quantity }
        } else if (sort == "Price") {
            stockDataList.sortedByDescending { it.price }
        } else {
            stockDataList
        }

        val sortedStockDataList = stockDataList.sortedByDescending { it.price * it.quantity }

        val sortedBlocks = sortedStockDataList.map { stockData ->
            GreenStockBlock(
                name = stockData.name,
                price = stockData.price,
                quantity = stockData.quantity,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .height(100.dp)
                    .fillMaxWidth(),
                navController = navController
            )
        }


        // 4 и последующие строки
        Spacer(modifier = Modifier.height(16.dp))
        // Вместо LazyRow используем LazyColumn
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            sortedBlocks.forEach { block ->
                block
            }


        }
    }
}


@Composable
fun StockStat(label: String, value: Int, color: Color? = null, showSign: Boolean = false) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = "${if (showSign) if (value >= 0) "+" else "" else ""}$value",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = color ?: MaterialTheme.colorScheme.onSurface
            )
        )
    }
}


@SuppressLint("ModifierParameter")
@Composable
fun GreenStockBlock(
    name: String = "",
    price: Double = 0.0,
    quantity: Int = 0,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Delete",
        tint = Color.Red,
        modifier = Modifier
            .size(48.dp)
            .clickable {
                onDeleteClick()
            }
            .padding(8.dp)
    )
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(Color.Gray)
            .shadow(4.dp)
            .clickable {
                navController.navigate("buy_or_sell?name=$name&price=$price&quantity=$quantity")
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            // Иконка крестика
            // Название акции
            Text(text = name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)

            // Цена и количество
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Price: ${round(price, 3)}", color = Color.White, fontSize = 16.sp)
                Text(text = "Quantity: $quantity", color = Color.White, fontSize = 16.sp)
            }

            // Итоговая сумма
            val totalPrice = price * quantity
            Text(
                text = "Total: ${round(totalPrice, 3)}",
                color = Color.White,
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp
            )

        }
    }

}

fun round(num: Double, digits: Int): Double {
    return "%.${digits}f".format(num).toDouble()
}

fun onDeleteClick() {
    println("удалили блок")
}

data class StockData(val name: String, val price: Double, val quantity: Int)
