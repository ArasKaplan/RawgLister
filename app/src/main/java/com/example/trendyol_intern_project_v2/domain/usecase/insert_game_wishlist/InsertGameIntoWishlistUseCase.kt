package com.example.trendyol_intern_project_v2.domain.usecase.insert_game_wishlist

import android.content.Context
import com.example.trendyol_intern_project_v2.common.DBModelDetails
import com.example.trendyol_intern_project_v2.common.UIModelDetails
import com.example.trendyol_intern_project_v2.data.local.model.toUIModelDetails
import com.example.trendyol_intern_project_v2.data.local.service.ModelDatabase
import com.example.trendyol_intern_project_v2.dependencyinjection.DefaultDispatcher
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class InsertGameIntoWishlistUseCase @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
){
    suspend operator fun invoke(context: Context, dbModelDetails: DBModelDetails)= withContext(defaultDispatcher){
            val dao= ModelDatabase(context).modelDao()
            dao.insertIntoWishlist(dbModelDetails)
    }
}