package com.e.condetestassignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.e.condetestassignment.model.Todo
import com.e.condetestassignment.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application){

    private val mainRepository = MainRepository()

    val successfulLiveData = mainRepository.successLiveData
    val failureLiveData = mainRepository.failureLiveData
    private var mutableList = MutableLiveData<List<Todo>>().apply { value = emptyList() }
    val listData: LiveData<List<Todo>> = mutableList
    fun getTodoList(){
        viewModelScope.launch { mainRepository.getTodoList()}
    }
}