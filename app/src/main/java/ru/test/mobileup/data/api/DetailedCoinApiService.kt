package ru.test.mobileup.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.test.mobileup.data.dto.DetailedCoin

interface DetailedCoinApiService {
    @GET("{id}")
    suspend fun getCoinById(@Path("id") id: String?): Response<DetailedCoin>
}