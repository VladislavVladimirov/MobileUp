package ru.test.mobileup.data.repository

import ru.test.mobileup.data.api.DetailedCoinApiService
import ru.test.mobileup.data.dto.DetailedCoin
import ru.test.mobileup.data.error.ApiError
import ru.test.mobileup.data.error.NetworkError
import ru.test.mobileup.data.error.UnknownError
import ru.test.mobileup.domain.repository.DetailedCoinRepository
import java.io.IOException
import javax.inject.Inject

class DetailedCoinRepositoryImpl @Inject constructor(private val apiService: DetailedCoinApiService) : DetailedCoinRepository {
    override suspend fun getCoinById(id: String?): DetailedCoin {
        try {
            val response = apiService.getCoinById(id)
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
