package com.example.bidness

//will be coroutines
class FriendlyQueryUseCase(val query:String, val repo : QueryRepo) {

    private val censoredWord = "hitler"
    suspend fun invoke():List<String>{
        //return listOf("Dummy", "Data")
        return repo.query(query).filter { it != censoredWord }
    }


}
