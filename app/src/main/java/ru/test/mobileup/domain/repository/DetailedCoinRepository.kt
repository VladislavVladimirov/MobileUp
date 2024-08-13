package ru.test.mobileup.domain.repository

import ru.test.mobileup.data.dto.DetailedCoin

interface DetailedCoinRepository {
    suspend fun getCoinById(id: String?): DetailedCoin
}