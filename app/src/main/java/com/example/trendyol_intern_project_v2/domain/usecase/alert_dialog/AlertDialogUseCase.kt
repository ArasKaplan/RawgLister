package com.example.trendyol_intern_project_v2.domain.usecase.alert_dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.trendyol_intern_project_v2.R
import org.w3c.dom.Text
import javax.inject.Inject


class AlertDialogUseCase @Inject constructor()  {
    private lateinit var dialog:AlertDialog
    operator fun invoke(
        context: Context,
        titleString: String,
        messageString: String,
        positiveString:String,
        negativeString: String,
        positiveOnClick:()->Unit,
        negativeOnClick:()->Unit=this::negativeOnClick
    ):AlertDialog{
        val alertDialogBuilder=AlertDialog.Builder(context)
            .setTitle(titleString)
            .setMessage(messageString)
            .setPositiveButton(positiveString,object :DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    positiveOnClick()
                }})
            .setNegativeButton(negativeString,object :DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    negativeOnClick()
                }
            })
        dialog=alertDialogBuilder.create()

        return dialog
    }
    private fun negativeOnClick(){
        dialog.dismiss()
    }

}