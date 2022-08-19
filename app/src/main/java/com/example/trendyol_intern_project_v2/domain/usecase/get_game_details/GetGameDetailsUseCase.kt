package com.example.trendyol_intern_project_v2.domain.usecase.get_game_details

import com.example.trendyol_intern_project_v2.common.RAWGResponseGameDetails
import com.example.trendyol_intern_project_v2.common.UIModelDetails
import com.example.trendyol_intern_project_v2.data.remote.model.toUIModelDetails
import com.example.trendyol_intern_project_v2.domain.repository.IApiRepository
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.coroutineScope
import java.util.function.Consumer
import javax.inject.Inject

class GetGameDetailsUseCase @Inject constructor(
    private val repository: IApiRepository
) {
     operator fun invoke(id:Int):Single<RAWGResponseGameDetails>{
        return repository.getGameDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}