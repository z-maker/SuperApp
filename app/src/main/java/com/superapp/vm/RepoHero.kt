package com.superapp.vm

import com.superapp.api.network.HeroService

/**
 * @Author: XNGEL
 * @Date: 04/10/21
 */
class RepoHero constructor(private val heroService: HeroService ) {

    suspend fun getAll() = heroService.getAll()

    suspend fun search(search: String) = heroService.search(search)

    suspend fun getById(id: String) = heroService.getById(id)

}