package com.example.trendyol_intern_project_v2.domain.usecase.delete_game_wishlist

import android.content.Context
import com.example.trendyol_intern_project_v2.common.UIModelDetails
import com.example.trendyol_intern_project_v2.data.local.model.toUIModelDetails
import com.example.trendyol_intern_project_v2.data.local.service.ModelDatabase
import com.example.trendyol_intern_project_v2.dependencyinjection.DefaultDispatcher
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DeleteGameFromWishlistUseCase @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
){
    suspend operator fun invoke(context: Context, uuid:Int)= withContext(defaultDispatcher) {
        val dao = ModelDatabase(context).modelDao()
        dao.deleteFromWishlist(uuid)
    }
}