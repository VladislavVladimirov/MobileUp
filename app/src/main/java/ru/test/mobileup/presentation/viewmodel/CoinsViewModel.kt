package ru.test.mobileup.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import ru.test.mobileup.domain.interactor.GetCoinsVsRubUseCase
import ru.test.mobileup.domain.interactor.GetCoinsVsUsdUseCase
import ru.test.mobileup.domain.model.CoinModel
import ru.test.mobileup.domain.model.StateModel
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getCoinsVsRubUseCase: GetCoinsVsRubUseCase,
    private val getCoinsVsUsdUseCase: GetCoinsVsUsdUseCase,
) : ViewModel() {
    private val _coins = MutableLiveData<CoinModel>()
    val coins: LiveData<CoinModel>
        get() = _coins
    private val _state = MutableLiveData<StateModel>()
    val state: LiveData<StateModel>
        get() = _state

    init {
        getCoinsVsUsd()
    }

    fun getCoinsVsUsd() = viewModelScope.launch {
        try {
            _state.value = StateModel(loading = true)
            val list = getCoinsVsUsdUseCase.execute()
            _coins.value = CoinModel(coins = list)
            _state.value = StateModel(loading = false)
        } catch (e: Exception) {
            _state.value = StateModel(error = true)
        }
    }

    fun getCoinsVsRub() = viewModelScope.launch {
        try {
            _state.value = StateModel(loading = true)
            val list = getCoinsVsRubUseCase.execute()
            _coins.value = CoinModel(coins = list)
            _state.value = StateModel(loading = false)
        } catch (e: Exception) {
            _state.value = StateModel(error = true)
        }
    }

    fun clearList() {
        _coins.value = CoinModel()
    }
}