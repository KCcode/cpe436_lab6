package com.example.bidness

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class QueryUseCaseTest{

    @Test
    fun testSample() = runBlocking{
        val queryUseCase =  QueryUseCase("", object: QueryRepo{
            override suspend fun query(text: String): List<String> {
                return when(text){
                    "fun" -> listOf("yo yo", "pokemon")
                    else -> listOf("hitler")
                }
            }
        })
        assert(queryUseCase.invoke().containsAll(listOf("hitler"))){}
    }

    @Test
    fun `Test friendly filter`() = runBlocking{
        val queryUseCase =  FriendlyQueryUseCase("", object: QueryRepo{
            override suspend fun query(text: String): List<String> {
                return when(text){
                    "fun" -> listOf("yo yo", "pokemon")
                    else -> listOf("hitler")
                }
            }
        })
        assert(queryUseCase.invoke().size == 0){ "Friendly censorship" }
    }
}