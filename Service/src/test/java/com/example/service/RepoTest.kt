package com.example.service

import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoTest {
    @Test
    fun retroFitTest() = runBlocking {
        val retrofit =  Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(GitHubService::class.java)
        val list = service.listRepos("alenz316")
        list.forEach{println(it?.name)}
    }
}