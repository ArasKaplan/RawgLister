package com.example.trendyol_intern_project_v2.domain.repository

import com.example.trendyol_intern_project_v2.common.*
import io.reactivex.Single


interface IApiRepository {

    fun getGameDetails(id:Int): Single<RAWGResponseGameDetails>

    fun getPlatforms():Single<RAWGResponsePlatformsResult>

    fun getGamesBySearch(searchString: String):Single<RAWGResponseGameListResult>

    fun getGamesBySearchPlatform(searchString: String,platformId:Int):Single<RAWGResponseGameListResult>

    fun getGamesByNextURL(urlString: String):Single<RAWGResponseGameListResult>
}