package io.bloco.template.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://iie-service-dev.workingllama.com/iie-service/"
    const val IMAGE_BASE_URL = "https://iie-service-dev.workingllama.com"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val apiService: ContactService = getRetrofit().create(ContactService::class.java)
}