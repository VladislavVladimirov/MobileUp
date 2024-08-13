package ru.test.mobileup.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import ru.test.mobileup.domain.interactor.GetCoinByIdUseCase
import ru.test.mobileup.domain.model.DetailedCoinModel
import ru.test.mobileup.domain.model.StateModel
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class DetailedCoinsViewModel @Inject constructor(
    private val getCoinByIdUseCase: GetCoinByIdUseCase
) : ViewModel() {
    private val _detailedCoin = MutableLiveData<DetailedCoinModel>()
    val detailedCoin: LiveData<DetailedCoinModel>
        get() = _detailedCoin
    private val _state = MutableLiveData<StateModel>()
    val state: LiveData<StateModel>
        get() = _state

    fun getCoinById(id: String?) = viewModelScope.launch {
        try {
            _state.value = StateModel(loading = true)
            val input = getCoinByIdUseCase.execute(id)
            _detailedCoin.value = DetailedCoinModel(coin = input)
            _state.value = StateModel(loading = false)
        } catch (e: Exception) {
            _state.value = StateModel(error = true)
        }
    }
}