package com.example.trendyol_intern_project_v2.domain.usecase.get_games_room

import android.content.Context
import com.example.trendyol_intern_project_v2.common.UIModelDetails
import com.example.trendyol_intern_project_v2.data.local.model.toUIModelDetails
import com.example.trendyol_intern_project_v2.data.local.service.ModelDatabase
import com.example.trendyol_intern_project_v2.dependencyinjection.DefaultDispatcher
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GetGameFromRoomUseCase @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
){
//Dispatcher,Statehandle,Navigation-Navbar,suspend

    suspend operator fun invoke(context: Context,uuid:Int):UIModelDetails?= withContext(defaultDispatcher){
        val dao=ModelDatabase(context).modelDao()
        dao.checkIfWishlist(uuid)?.toUIModelDetails()
    }
}