package com.superapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @Author: XNGEL
 * @Date: 04/10/21
 */
class VmFactoryHero constructor(private val repoHero: RepoHero): ViewModelProvider.Factory {

    override fun<T: ViewModel?> create(modelClass: Class<T>):T{
        return if (modelClass.isAssignableFrom(ViewModelHero::class.java)){
            ViewModelHero(this.repoHero) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}