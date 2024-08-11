package ru.test.mobileup.domain.repository

import ru.test.mobileup.data.dto.Coin

interface CoinRepository {
    suspend fun getCoinsVsUsd(): List<Coin>
    suspend fun getCoinsVsRub(): List<Coin>
}