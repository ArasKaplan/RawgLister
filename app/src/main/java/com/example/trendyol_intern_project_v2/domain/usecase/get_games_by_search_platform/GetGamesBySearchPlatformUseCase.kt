package com.example.trendyol_intern_project_v2.domain.usecase.get_games_by_search_platform

import com.example.trendyol_intern_project_v2.common.RAWGResponseGameListResult
import com.example.trendyol_intern_project_v2.common.UIModelListing
import com.example.trendyol_intern_project_v2.data.remote.model.toUIModelListings
import com.example.trendyol_intern_project_v2.domain.repository.IApiRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetGamesBySearchPlatformUseCase @Inject constructor(
    private val repository: IApiRepository
) {
    operator fun invoke(searchString: String,platformID:Int):Single<RAWGResponseGameListResult>{
        return repository.getGamesBySearchPlatform(searchString,platformID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}