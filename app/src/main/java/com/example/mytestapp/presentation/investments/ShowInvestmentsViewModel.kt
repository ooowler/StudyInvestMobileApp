package com.example.mytestapp.presentation.investments

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.database.domain.model.Investment
import com.example.mytestapp.database.domain.use_cases.InvestmentUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ShowInvestmentsViewModel @Inject constructor(
    private val investmentUseCases: InvestmentUseCases
): ViewModel() {
    private val _counterState = mutableStateOf(0)
    val counterState: State<Int> = _counterState

    private val _listState = mutableStateOf(listOf<Investment>())
    val listState: State<List<Investment>> = _listState

    val scope = viewModelScope

    init {
        scope.launch {
            _listState.value = investmentUseCases.getAllInvestment()
        }
    }

    fun onEvent(event: ShowInvestmentsEvent) {
        when (event) {
            ShowInvestmentsEvent.UpdateCounterToLatestInvestment -> {
                scope.launch {
                    investmentUseCases.insertInvestment(
                        Investment(
                        price = 100L,
                        count = 300L,
                        name = "name: ${Random.nextInt()}",
                        uid = null
                    )
                    )
                    Log.d("my tag", "my message")
                    _listState.value = investmentUseCases.getAllInvestment()
                }
            }
        }
    }
}

sealed class ShowInvestmentsEvent {
    object UpdateCounterToLatestInvestment : ShowInvestmentsEvent()
}