package com.example.trendyol_intern_project_v2.domain.usecase.get_platforms

import android.util.Log
import android.widget.Toast
import com.example.trendyol_intern_project_v2.common.RAWGResponsePlatformsResult
import com.example.trendyol_intern_project_v2.common.UIPlatforms
import com.example.trendyol_intern_project_v2.data.remote.model.toUIPlatforms
import com.example.trendyol_intern_project_v2.domain.repository.IApiRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPlatformsUseCase @Inject constructor(
    private val repository:IApiRepository
){
    operator fun invoke(): Single<RAWGResponsePlatformsResult>{
        return repository.getPlatforms()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}