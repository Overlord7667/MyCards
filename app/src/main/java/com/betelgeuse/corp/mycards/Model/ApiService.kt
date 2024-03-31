package com.betelgeuse.corp.mycards.Model

import android.app.AlertDialog
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import android.content.Context
import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @POST("getAllCompanies")
    suspend fun getAllCompanies(@Body request: GetCompaniesRequest): Response<List<Company>>

    @POST("getAllCompaniesIdeal")
    suspend fun getAllCompaniesIdeal(@Body request: GetCompaniesRequest): Response<List<Company>>

    @POST("getAllCompaniesLong")
    suspend fun getAllCompaniesLong(@Body request: GetCompaniesRequest): Response<List<Company>>

    @POST("getAllCompaniesError")
    suspend fun getAllCompaniesError(@Body request: GetCompaniesRequest): Response<List<Company>>

    @POST("collections/{collectionId}")
    suspend fun getPostmanCollection(
        @Path("collectionId") collectionId: String,
        @Header("X-Api-Key") apiKey: String
    ): Response<ResponseBody>
}

object ApiClient {
    private const val BASE_URL = "https://api.getpostman.com/"

    private val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("TOKEN", "123")
            .build()
        chain.proceed(request)
    }.build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

object ApiErrorHandler {
    fun handleApiError(response: Response<*>?, context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)

        if (response != null) {
            when (response.code()) {
                401 -> {
                    alertDialogBuilder.setTitle("Ошибка авторизации")
                        .setMessage("Вы не авторизованы для выполнения этого действия")
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                    Log.e("LogCards", "Ошибка авторизации: Вы не авторизованы для выполнения этого действия")

                }
                400 -> {
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    alertDialogBuilder.setTitle("Ошибка 400")
                        .setMessage(errorMessage)
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                    Log.e("LogCards", "Ошибка 400: $errorMessage")

                }
                500 -> {
                    alertDialogBuilder.setTitle("Ошибка сервера")
                        .setMessage("Произошла внутренняя ошибка сервера")
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                    Log.e("LogCards", "Ошибка сервера: Произошла внутренняя ошибка сервера")
                }
            }
        }
    }
}

