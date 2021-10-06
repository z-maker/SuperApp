package com.superapp.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.superapp.api.model.HeroModel
import kotlinx.coroutines.*

/**
 * @Author: XNGEL
 * @Date: 04/10/21
 */
class ViewModelHero constructor(private val repoHero: RepoHero) : ViewModel() {

    val heroTempList = ArrayList<HeroModel?>()

    val isLoading = MutableLiveData<Boolean>()

    val heroList = MutableLiveData<List<HeroModel?>>()

    val heroModel = MutableLiveData<HeroModel>()

    var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getNextBundle(start: Int, end: Int) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            isLoading.postValue(true)
            for (i in start..end) {
                val response = repoHero.getById("${i}")
                withContext(Dispatchers.Main){
                    if (response.isSuccessful){
                        heroTempList.add(response.body())
                        heroList.value = heroTempList
                        heroList.postValue(heroTempList)
                    }else{
                        onError("Error: ${response.message()}")
                    }

                }
            }
            isLoading.postValue(false)
        }
    }

    fun getAll() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repoHero.getAll()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    heroList.postValue(response.body())
                } else {
                    onError("Error: ${response.message()}")
                }

            }
        }
    }

    fun search(search: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repoHero.search(search)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    heroList.postValue(response.body()!!.results)
                } else {
                    onError("Error: ${response.message()}")
                }

            }
        }
    }

    fun getById(id: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repoHero.getById(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    heroModel.postValue(response.body())
                } else {
                    onError("Error: ${response.message()}")
                }

            }
        }
    }

    private fun onError(message: String) {
        Log.d("KK", message)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}