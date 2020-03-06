package com.example.bidness

//will be coroutines
class QueryUseCase(val query:String, val repo : QueryRepo) {

    suspend fun invoke():List<String>{
        //return listOf("Dummy", "Data")
        return repo.query(query)
    }


}
