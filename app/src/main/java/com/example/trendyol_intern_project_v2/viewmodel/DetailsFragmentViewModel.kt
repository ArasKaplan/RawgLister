package com.example.trendyol_intern_project_v2.viewmodel

import android.app.AlertDialog
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendyol_intern_project_v2.R
import com.example.trendyol_intern_project_v2.common.DBModelDetails
import com.example.trendyol_intern_project_v2.common.RAWGResponseGameDetails
import com.example.trendyol_intern_project_v2.common.UIModelDetails
import com.example.trendyol_intern_project_v2.data.local.model.toDBModelDetails
import com.example.trendyol_intern_project_v2.data.local.model.toUIModelDetails
import com.example.trendyol_intern_project_v2.data.local.service.ModelDAO
import com.example.trendyol_intern_project_v2.data.local.service.ModelDatabase
import com.example.trendyol_intern_project_v2.data.remote.model.toUIModelDetails
import com.example.trendyol_intern_project_v2.data.remote.service.RawgAPIProvider
import com.example.trendyol_intern_project_v2.domain.usecase.alert_dialog.AlertDialogUseCase
import com.example.trendyol_intern_project_v2.domain.usecase.delete_game_wishlist.DeleteGameFromWishlistUseCase
import com.example.trendyol_intern_project_v2.domain.usecase.get_game_details.GetGameDetailsUseCase
import com.example.trendyol_intern_project_v2.domain.usecase.get_games_room.GetGameFromRoomUseCase
import com.example.trendyol_intern_project_v2.domain.usecase.insert_game_wishlist.InsertGameIntoWishlistUseCase
import com.example.trendyol_intern_project_v2.domain.usecase.popup_webview.PopupWebViewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(
    private val getGameDetailsUseCase: GetGameDetailsUseCase,
    private val popupWebViewUseCase: PopupWebViewUseCase,
    private val getGameDetailsFromRoomUseCase: GetGameFromRoomUseCase,
    private val deleteGameFromWishlistUseCase: DeleteGameFromWishlistUseCase,
    private val insertGameIntoWishlistUseCase: InsertGameIntoWishlistUseCase,
    private val alertDialogUseCase: AlertDialogUseCase
):ViewModel() {
    val gameDetails=MutableLiveData<UIModelDetails>()

    val gameID=MutableLiveData<Int>()

    val isSavedOnDB=MutableLiveData<Boolean>()//True -->SQL, False-->API

    private val disposable=CompositeDisposable()

     fun getGameDetails(id:Int, context: Context){
        gameID.value=id
        checkWishlist(context)
    }

    private fun getGameDetailsFromAPI(){
        disposable.add(
            getGameDetailsUseCase(gameID.value!!)
                .subscribeWith(object :DisposableSingleObserver<RAWGResponseGameDetails>(){
                    override fun onSuccess(t: RAWGResponseGameDetails) {
                        gameDetails.value=t.toUIModelDetails()
                    }
                    override fun onError(e: Throwable) {
                        Log.d("APIERROR","An error occured while getting game details from API.")
                    }
                })

        )
    }

    fun insertIntoWishlist(context: Context){
        viewModelScope.launch{
            insertGameIntoWishlistUseCase(context,gameDetails.value!!.toDBModelDetails())
            isSavedOnDB.value=true
        }
    }

    fun deleteFromWishlist(context: Context){
        viewModelScope.launch{
            deleteGameFromWishlistUseCase(context,gameID.value!!)
            isSavedOnDB.value=false
        }
    }

    private fun checkWishlist(context: Context){
        viewModelScope.launch {
            getGameFromDB(context)?.let {
                isSavedOnDB.value=true
                gameDetails.value=it
            }?: getGameDetailsFromAPI()
        }
    }

    suspend fun getGameFromDB(context: Context):UIModelDetails?{
        return getGameDetailsFromRoomUseCase(context,gameID.value!!)
    }

    fun showWebsiteWebView(context: Context,url:String):Dialog{
        return popupWebViewUseCase(context,R.layout.popup_webview,url)
    }

    fun showAlertDialog(
        context: Context,
        titleString: String,
        messageString: String,
        positiveString: String,
        negativeString: String,
        positiveOnclick:()->Unit,
        negativeOnClick:(()->Unit)?
    ):AlertDialog{

        negativeOnClick?.let {
            return alertDialogUseCase(context,titleString,messageString,positiveString,negativeString,positiveOnclick,negativeOnClick)
        }?: return alertDialogUseCase(context,titleString,messageString,positiveString,negativeString,positiveOnclick)

    }
}