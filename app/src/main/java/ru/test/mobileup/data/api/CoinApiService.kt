package ru.test.mobileup.data.api

import retrofit2.Response
import retrofit2.http.GET
import ru.test.mobileup.data.dto.Coin

interface CoinApiService {
    @GET("markets?vs_currency=usd&per_page=30")
    suspend fun getCoinsVsUsd(): Response<List<Coin>>

    @GET("markets?vs_currency=rub&per_page=30")
    suspend fun getCoinsVsRub(): Response<List<Coin>>
}