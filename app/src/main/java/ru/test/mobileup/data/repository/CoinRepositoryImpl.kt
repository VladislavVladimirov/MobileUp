package ru.test.mobileup.data.repository

import ru.test.mobileup.data.api.CoinApiService
import ru.test.mobileup.data.dto.Coin
import ru.test.mobileup.data.error.ApiError
import ru.test.mobileup.data.error.NetworkError
import ru.test.mobileup.data.error.UnknownError
import ru.test.mobileup.domain.repository.CoinRepository
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val apiService: CoinApiService
) : CoinRepository {

    override suspend fun getCoinsVsUsd(): List<Coin> {
        try {
            val response = apiService.getCoinsVsUsd()
            if (!response.isSuccessful) throw ApiError(response.code(), response.message())
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e: IOException) {
            e.printStackTrace()
            throw NetworkError
        } catch (e: Exception) {
            e.printStackTrace()
            throw UnknownError
        }
    }

    override suspend fun getCoinsVsRub(): List<Coin> {
        try {
            val response = apiService.getCoinsVsRub()
            if (!response.isSuccessful) throw ApiError(response.code(), response.message())
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e: IOException) {
            e.printStackTrace()
            throw NetworkError
        } catch (e: Exception) {
            e.printStackTrace()
            throw UnknownError
        }
    }
}