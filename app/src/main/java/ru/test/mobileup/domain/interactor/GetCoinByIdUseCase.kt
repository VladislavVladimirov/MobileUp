package ru.test.mobileup.domain.interactor

import ru.test.mobileup.data.dto.DetailedCoin
import ru.test.mobileup.domain.repository.DetailedCoinRepository
import javax.inject.Inject

class GetCoinByIdUseCase @Inject constructor(
    private val detailedCoinRepository: DetailedCoinRepository
) {
    suspend fun execute(id: String?): DetailedCoin {
        return detailedCoinRepository.getCoinById(id)
    }
}

