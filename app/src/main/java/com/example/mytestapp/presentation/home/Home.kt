package com.example.mytestapp.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytestapp.R


//@Preview(showSystemUi = true)
@Composable
fun HomeScreen(
    navController: NavController
) {
    val totalBalance by remember { mutableStateOf(100) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

        // Отступ между изображением пользователя и "Total balance"
        Spacer(modifier = Modifier.height(16.dp))

        // Отображение "Total balance" по середине
        Text(text = "Total balance: $totalBalance", style = MaterialTheme.typography.bodyMedium)

        // Кнопка перехода в раздел инвестиций
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("investments") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Перейти в раздел инвестиций")
        }

        // Отступ между "Total balance" и кнопкой
        Spacer(modifier = Modifier.weight(1f))

        // Кнопка выхода из профиля внизу
        TextButton(
            onClick = {
                // Реализуйте здесь действия при выходе из профиля
            }
        ) {
            Text(text = "Logout", color = MaterialTheme.colorScheme.primary)
        }
    }
}
