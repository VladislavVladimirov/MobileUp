package ru.test.mobileup.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import ru.test.mobileup.domain.interactor.GetCoinsVsRubUseCase
import ru.test.mobileup.domain.interactor.GetCoinsVsUsdUseCase
import ru.test.mobileup.domain.model.CoinModel
import ru.test.mobileup.domain.model.StateModel
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ViewModel @Inject constructor(
    private val getCoinsVsRubUseCase: GetCoinsVsRubUseCase,
    private val getCoinsVsUsdUseCase: GetCoinsVsUsdUseCase
): ViewModel() {
    private val _data = MutableLiveData<CoinModel>()
    val data: LiveData<CoinModel>
        get() = _data
    private val _state = MutableLiveData<StateModel>()
    val state: LiveData<StateModel>
        get() = _state

    fun getCoinsVsUsd() = viewModelScope.launch{
        try {
            _state.value = StateModel(loading = true)
            val list = getCoinsVsUsdUseCase.execute()
            _data.value = CoinModel(coins = list)
        } catch (e:Exception){
            _state.value = StateModel(error = true)
        }
    }
    fun getCoinsVsRub() = viewModelScope.launch{
        try {
            _state.value = StateModel(loading = true)
            val list = getCoinsVsRubUseCase.execute()
            _data.value = CoinModel(coins = list)
        } catch (e:Exception){
            _state.value = StateModel(error = true)
        }
    }

}