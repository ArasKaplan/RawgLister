package com.example.trendyol_intern_project_v2.data.repository

import android.util.Log
import com.example.trendyol_intern_project_v2.common.*
import com.example.trendyol_intern_project_v2.data.remote.model.toUIModelDetails
import com.example.trendyol_intern_project_v2.data.remote.model.toUIModelListings
import com.example.trendyol_intern_project_v2.data.remote.model.toUIPlatforms
import com.example.trendyol_intern_project_v2.data.remote.service.IRawgAPI
import com.example.trendyol_intern_project_v2.domain.repository.IApiRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


//Bu sınıf fonksiyonları IAPIRepository'den inherit eder. Fonksiyonlar zaten yazılmıştır.
// Yeni request olduğu zaman fonksiyon burada implement edilir.
// API değişimi olacağı zaman constructor içerisindeki api değiştirilir.


class ApiRepositoryImpl @Inject constructor(
    private val api: IRawgAPI
): IApiRepository {
    override fun getGameDetails(id: Int): Single<RAWGResponseGameDetails> {
        return api.getGameDetails(id)
    }

    override fun getPlatforms(): Single<RAWGResponsePlatformsResult> {
        return api.getPlatforms()
    }

    override fun getGamesBySearch(searchString: String): Single<RAWGResponseGameListResult> {
        return api.getGameListBySearch(search = searchString)
    }

    override fun getGamesBySearchPlatform(
        searchString: String,
        platformId: Int
    ): Single<RAWGResponseGameListResult> {
        return api.getGameListBySearchPlatform(search = searchString, platformID = platformId)
    }

    override fun getGamesByNextURL(urlString: String): Single<RAWGResponseGameListResult> {
        return api.getGamesByNextURL(urlString)
    }

}