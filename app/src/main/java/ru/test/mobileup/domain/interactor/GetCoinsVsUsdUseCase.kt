package ru.test.mobileup.domain.interactor

import ru.test.mobileup.data.dto.Coin
import ru.test.mobileup.domain.repository.CoinRepository
import javax.inject.Inject


class GetCoinsVsUsdUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    suspend fun execute(): List<Coin> {
        return coinRepository.getCoinsVsUsd()
    }
}