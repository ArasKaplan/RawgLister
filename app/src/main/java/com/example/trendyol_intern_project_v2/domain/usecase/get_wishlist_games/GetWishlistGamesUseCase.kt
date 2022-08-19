package com.example.trendyol_intern_project_v2.domain.usecase.get_wishlist_games

import android.content.Context
import com.example.trendyol_intern_project_v2.common.UIModelListing
import com.example.trendyol_intern_project_v2.data.local.model.toUIModelListing
import com.example.trendyol_intern_project_v2.data.local.service.ModelDatabase
import com.example.trendyol_intern_project_v2.dependencyinjection.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetWishlistGamesUseCase @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(context: Context):ArrayList<UIModelListing> =withContext(defaultDispatcher){
        val dao=ModelDatabase(context).modelDao()
        ArrayList(dao.getAllFromWishlist().map { it.toUIModelListing() })
    }
}