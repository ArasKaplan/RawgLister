package com.example.trendyol_intern_project_v2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trendyol_intern_project_v2.common.RAWGResponseGameListResult
import com.example.trendyol_intern_project_v2.common.RAWGResponsePlatformsResult
import com.example.trendyol_intern_project_v2.common.UIModelListing
import com.example.trendyol_intern_project_v2.common.UIPlatforms
import com.example.trendyol_intern_project_v2.data.remote.model.toUIModelListings
import com.example.trendyol_intern_project_v2.data.remote.model.toUIPlatforms
import com.example.trendyol_intern_project_v2.dependencyinjection.DefaultDispatcher
import com.example.trendyol_intern_project_v2.domain.usecase.get_games_by_search.GetGamesBySearchUseCase
import com.example.trendyol_intern_project_v2.domain.usecase.get_platforms.GetPlatformsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

@HiltViewModel
class SplasherScreenViewModel @Inject constructor(
    private val getPlatformsUseCase: GetPlatformsUseCase,
    private val getGamesBySearchUseCase: GetGamesBySearchUseCase
):ViewModel() {
    val platforms=MutableLiveData<ArrayList<UIPlatforms>>()
    val firstList=MutableLiveData<ArrayList<UIModelListing>>()
    val nextPageURL=MutableLiveData<String>()
    val readyForNextPagePlatforms=MutableLiveData<Boolean>(false)
    val readyForNextPageFirstList=MutableLiveData<Boolean>(false)

    private val disposable=CompositeDisposable()

    init {
        firstList.value= arrayListOf()
        platforms.value= arrayListOf()
    }


    fun getPlatforms(){
        disposable.add(
            getPlatformsUseCase()
            .subscribeWith(object :DisposableSingleObserver<RAWGResponsePlatformsResult>(){
                override fun onSuccess(t: RAWGResponsePlatformsResult) {
                    platforms.value=t.toUIPlatforms()
                    readyForNextPagePlatforms.value=true
                }
                override fun onError(e: Throwable) {
                    Log.d("APIERROR",e.localizedMessage ?: e.message ?:"An error occured while getting platforms from API.")
                }
            }))
    }

    fun getFirstList(){
        disposable.add(
            getGamesBySearchUseCase("")
                .subscribeWith(object:DisposableSingleObserver<RAWGResponseGameListResult>(){
                    override fun onSuccess(t: RAWGResponseGameListResult) {
                        t.toUIModelListings().let {
                            firstList.value=it.second!!
                            nextPageURL.value=it.first?:""
                        }
                        readyForNextPageFirstList.value=true
                    }
                    override fun onError(e: Throwable) {
                        Log.d("APIERROR",e.localizedMessage ?: e.message ?:"An error occured while getting game listings from API.")
                    }
                })
        )
    }



}