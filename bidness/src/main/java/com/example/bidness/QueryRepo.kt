package com.example.bidness

interface QueryRepo {
    suspend fun query(text:String):List<String>
}