package com.betelgeuse.corp.mycards.Model

import java.net.HttpURLConnection
import java.net.URL

class CollectionApiClient {
    private val apiKey = "PMAK-6609a19ba35d6d000100bc4a-XXXX"

    fun getCollection(collectionId: String): String {
        val url = URL("https://api.getpostman.com/collections/$collectionId")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("X-Api-Key", apiKey)

        val responseCode = connection.responseCode
        println("Response Code: $responseCode")

        val content = connection.inputStream.bufferedReader().readText()
        println("Response Body: $content")

        return content
    }
}

fun main() {
    val apiClient = CollectionApiClient()
    val collectionId = "f658293f-e8a0-4f83-94f9-b89c09238834"
    apiClient.getCollection(collectionId)
}