package com.e.condetestassignment.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.e.condetestassignment.model.Todo
import com.e.condetestassignment.network.RetrofitService
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MainRepository {
    private val retrofitService = RetrofitService.create()
    val successLiveData = MutableLiveData<List<Todo>>()
    val failureLiveData = MutableLiveData<Boolean>()

    companion object {
        val TAG = MainRepository::class.java.simpleName
    }

    suspend fun getTodoList() {
        try {
            val response = retrofitService?.getTodo()

            if (response!!.isSuccessful) {
                Log.d(TAG, "SUCCESS")
                Log.d(TAG, "${response.body()}")
                successLiveData.postValue(response.body())
            } else {
                Log.d(TAG, "FAILURE")
                Log.d(TAG, "${response.body()}")
                failureLiveData.postValue(true)
            }
        } catch (e: UnknownHostException) {
            //this exception occurs when there is no internet connection or host is not available
            //so inform user that something went wrong
            failureLiveData.postValue(true)
        } catch (e: SocketTimeoutException) {
            //this exception occurs when time out will happen
            //so inform user that something went wrong
            failureLiveData.postValue(true)
        } catch (exception: Exception) {
            //this is generic exception handling
            //so inform user that something went wrong
            failureLiveData.postValue(true)
        }
    }
}