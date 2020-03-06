package com.example.lab6.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bidness.QueryRepo
import com.example.bidness.QueryUseCase
import com.example.service.GitHubBussinessRepo
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    //outside read inside read write
    val data : LiveData<List<String>> = MutableLiveData(emptyList<String>())

    fun query(){
        viewModelScope.launch{
            val queryUseCase = QueryUseCase("alenz316", GitHubBussinessRepo())

            val list = queryUseCase.invoke()
            (data as MutableLiveData).value = list
        }
    }
}
