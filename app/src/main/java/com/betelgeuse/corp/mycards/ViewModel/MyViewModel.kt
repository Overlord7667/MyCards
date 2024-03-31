package com.betelgeuse.corp.mycards.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betelgeuse.corp.mycards.Model.ApiClient
import com.betelgeuse.corp.mycards.Model.ApiErrorHandler
import com.betelgeuse.corp.mycards.Model.Company
import com.betelgeuse.corp.mycards.Model.GetCompaniesRequest
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class MyViewModel : ViewModel() {
    fun fetchData(context: Context) {
        viewModelScope.launch {
            try {
                val apiService = ApiClient.apiService

                // Получаем коллекцию из Postman API
                val collectionId = "f658293f-e8a0-4f83-94f9-b89c09238834"
                val apiKey = "PMAK-6609a19ba35d6d000100bc4a-XXXX" // Ваш API ключ Postman
                val responseCollection = apiService.getPostmanCollection(collectionId, apiKey)

                if (responseCollection.isSuccessful) {
                    val responseBody = responseCollection.body()
                    Log.e("LogCards", "Postman Collection: $responseBody")
                } else {
                    Log.e("LogCards", "Error getting Postman Collection: ${responseCollection.message()}")
                }

                // Выполняем другие запросы
                val resultCompaniesResponse = apiService.getAllCompanies(GetCompaniesRequest(offset = 0, limit = 5))
                Log.e("LogCards", "Response Companies: ${resultCompaniesResponse.body()}")
                val resultCompanies: List<Company>? = resultCompaniesResponse.body()
                if (!resultCompanies.isNullOrEmpty()) {
                    Log.e("LogCards", "Result Companies: $resultCompanies")
                } else {
                    // Обработка ошибки
                    Log.e("LogCards", "Error: ${resultCompaniesResponse.errorBody()}")
                }

                val resultIdealResponse = apiService.getAllCompaniesIdeal(GetCompaniesRequest(offset = 0, limit = 5))
                val resultIdeal: List<Company>? = resultIdealResponse.body()
                if (!resultIdeal.isNullOrEmpty()) {
                    Log.e("LogCards", "Result Ideal: $resultIdeal")
                } else {
                    Log.e("LogCards", "Result Ideal is null or empty")
                }

                val resultLongResponse = apiService.getAllCompaniesLong(GetCompaniesRequest(offset = 0, limit = 5))
                val resultLong: List<Company>? = resultLongResponse.body()
                if (!resultLong.isNullOrEmpty()) {
                    Log.e("LogCards", "Result Long: $resultLong")
                } else {
                    Log.e("LogCards", "Result Long is null or empty")
                }

                val resultErrorResponse = apiService.getAllCompaniesError(GetCompaniesRequest(offset = 0, limit = 5))
                val resultError: List<Company>? = resultErrorResponse.body()
                if (!resultError.isNullOrEmpty()) {
                    Log.e("LogCards", "Result Error: $resultError")
                } else {
                    Log.e("LogCards", "Result Error is null or empty")
                }
            } catch (e: Exception) {
                when (e) {
                    is IOException -> {
                        Log.e("LogCards", "IOException: ${e.message}")
                    }
                    is HttpException -> {
                        val response: Response<*>? = e.response()
                        if (response != null) {
                            ApiErrorHandler.handleApiError(response as Response<List<Company>>, context)
                        } else {
                            Log.e("LogCards", "HttpException: Response is null")
                        }
                    }
                    else -> {
                        Log.e("LogCards", "Exception: ${e.message}")
                    }
                }
            }
        }
    }
}
