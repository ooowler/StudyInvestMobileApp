package com.example.mytestapp.presentation.investments

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@SuppressLint("UnrememberedMutableState")
@Composable
fun ShowInvestmentsScreen(
    navController: NavController,
    viewModel: ShowInvestmentsViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    )

    Column {
        val count = viewModel.counterState.value
        val invList = viewModel.listState.value
        Text(text = "Counter ${count}")
        Button(
            onClick = { viewModel.onEvent(ShowInvestmentsEvent.UpdateCounterToLatestInvestment) }
        ) {

        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(invList) {
                Text(text = it.name)
            }
        }
    }
}
