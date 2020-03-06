package com.example.service

import com.example.bidness.QueryRepo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubBussinessRepo : QueryRepo {

    private val service = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GitHubService::class.java)

    override suspend fun query(text: String): List<String> {
        return service.listRepos(text).map { it?.name ?: "error name" }

    }
}