package com.example.mytestapp.presentation.main.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mytestapp.navigator.getBuyOrSellRoute
import com.example.mytestapp.utils.round
import kotlin.random.Random

data class StockData(val name: String, val price: Double, val quantity: Int)

@Composable
fun StockStat(label: String, value: Double, color: Color? = null, showSign: Boolean = false) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = "${if (showSign) if (value >= 0) "+" else "" else ""}${round(value, 3)}",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = color ?: MaterialTheme.colorScheme.onSurface
            )
        )
    }
}


@Composable
fun StockBlock(
    name: String = "",
    price: Double = 0.0,
    quantity: Int = 0,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    navController: NavController
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(Color.Gray)
            .shadow(4.dp)
            .clickable {
                val route = getBuyOrSellRoute(name, price, quantity)
                navController.navigate(route)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)

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


fun getSortedRawStockData(selectedSortOption: SortOption): List<StockData> {
    val stockDataList = List(5) { index ->
        StockData(
            name = "Stock $index",
            price = round(3.14 * Random.nextInt(5, 100), 3),
            quantity = Random.nextInt(1, 11)
        )
    }

    val sortedStockData: List<StockData> = when (selectedSortOption) {
        SortOption.Total -> stockDataList.sortedByDescending { it.price * it.quantity }
        SortOption.Quantity -> stockDataList.sortedByDescending { it.quantity }
        SortOption.Price -> stockDataList.sortedByDescending { it.price }
    }

    return sortedStockData
}