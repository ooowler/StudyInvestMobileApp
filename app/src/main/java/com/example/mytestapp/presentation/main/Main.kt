@file:Suppress("UNUSED_EXPRESSION")

package com.example.mytestapp.presentation.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mytestapp.R
import com.example.mytestapp.presentation.main.utils.StockStat


@Composable
//fun MainScreen(
//    navController: NavController
//) {
//    var selectedSortOption by remember { mutableStateOf(SortOption.Total) }
//    val onSortOptionSelected: (SortOption) -> Unit = {
//        selectedSortOption = it
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        ShowPortfolioNameAndImage()
//
//
//        val total = 100.1
//        val invested = 100 + 0.1
//        val profit: Double = total - invested
//        val profitColor = if (profit >= 0) Color.Green else Color.Red
//
//        ShowPortfolioInfo(invested, total, profit, profitColor)
//        Spacer(modifier = Modifier.height(16.dp))
//
//        ShowDropDownSortMenu(selectedSortOption, onSortOptionSelected)
//
////        val sortedRawStockData = viewModel.onEvent(ShowInvestmentsEvent.getSortedRawStockData)
////        val sortedRawStockData = getSortedRawStockData(selectedSortOption)
////        val composeBlocks = sortedRawStockData.map { stockData ->
////            StockBlock(
////                name = stockData.name,
////                price = stockData.price,
////                quantity = stockData.quantity,
////                modifier = Modifier
////                    .padding(bottom = 8.dp)
////                    .height(100.dp)
////                    .fillMaxWidth(),
////                navController = navController
////            )
////        }
////
////        ShowBlocks(composeBlocks)
//    }
//}
fun ShowPortfolioInfo(invested: Double, total: Double, profit: Double, profitColor: Color) {
    profitColor
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
}

@Composable
fun ShowPortfolioNameAndImage() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "portfolio")
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "cat",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .border(
                    BorderStroke(4.dp, Color.Yellow),
                    CircleShape
                )
                .padding(4.dp)
                .clip(CircleShape)
        )
    }
}


@Composable
fun ShowBlocks(sortedStockData: List<Unit>) {
    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        sortedStockData.forEach { block ->
            block
        }
    }
}