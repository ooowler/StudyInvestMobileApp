@file:Suppress("UNUSED_EXPRESSION")

package com.example.mytestapp.presentation.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mytestapp.R
import com.example.mytestapp.navigator.getBuyOrSellRoute
import com.example.mytestapp.presentation.investments.ShowInvestmentsViewModel
import com.example.mytestapp.presentation.main.utils.ShowDropDownSortMenu
import com.example.mytestapp.presentation.main.utils.SortOption
import com.example.mytestapp.presentation.main.utils.StockBlock
import com.example.mytestapp.presentation.main.utils.StockStat


@Composable
fun MainScreen(
    navController: NavController,
    viewModel: ShowInvestmentsViewModel = hiltViewModel()
) {
    var selectedSortOption by remember { mutableStateOf(SortOption.Total) }
    val onSortOptionSelected: (SortOption) -> Unit = {
        selectedSortOption = it
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ShowPortfolioNameAndImage(navController)


        val total = viewModel.totalBalance.value
        val invested = 100.0
        val profit = total - invested
        val profitColor = if (profit >= 0) Color.Green else Color.Red

        ShowPortfolioInfo(invested, total, profit, profitColor)
        Spacer(modifier = Modifier.height(50.dp))


        ShowDropDownSortMenu(selectedSortOption, onSortOptionSelected)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Investments",
                modifier = Modifier.clickable { viewModel.deleteAllInvestments() }
            )

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Investments",
                modifier = Modifier.clickable {
                    val route = getBuyOrSellRoute()
                    navController.navigate(route)
                }
            )
        }

        val rawStockData = viewModel.listState.value
        val sortedRawStockData = when (selectedSortOption) {
            SortOption.Total -> rawStockData.sortedByDescending { it.price * it.count }
            SortOption.Quantity -> rawStockData.sortedByDescending { it.count }
            SortOption.Price -> rawStockData.sortedByDescending { it.price }
        }

        val composeBlocks = sortedRawStockData.map { stockData ->
            StockBlock(
                uid = stockData.uid,
                name = stockData.name,
                price = stockData.price,
                quantity = stockData.count,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .height(100.dp)
                    .fillMaxWidth(),
                navController = navController
            )
        }

        ShowBlocks(composeBlocks)
    }
}


@Composable
fun ShowPortfolioNameAndImage(navController: NavController) {
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
                .clickable { navController.navigate("home") }
        )
    }
}

@Composable
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