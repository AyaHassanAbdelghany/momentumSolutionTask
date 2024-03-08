package com.example.momentumsolutiontask.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.LayoutInflater
import com.example.momentumsolutiontask.R
import retrofit2.Response

object Util {

    var alertDialogMessage: AlertDialog? = null
    var alertDialogProgress: AlertDialog? = null
    fun <T> handleResponse(response: Response<T>?): ResponseState<T> {

        return if (response?.isSuccessful == true) {
            val body = response.body()
            if (body != null) {
                ResponseState.Success(body)
            } else {
                ResponseState.Error(response.message())
            }
        } else {
            ResponseState.Error(response?.errorBody().toString())

        }
    }
//
//    fun showDialogMessage(message: String?, context: Context) {
//
//        alertDialogMessage = AlertDialog.Builder(context).create()
//        alertDialogMessage?.setMessage(message)
//        alertDialogMessage?.show()
//        alertDialogMessage?.setCanceledOnTouchOutside(true)
//        alertDialogMessage?.window?.setBackgroundDrawable(context.getDrawable(R.drawable.rounded_shape))
//        alertDialogMessage?.setOnDismissListener {
//            alertDialogMessage = null
//        }
//
//    }

//
//    fun showProgressDialog(context: Context) {
//        val alertDialog = AlertDialog.Builder(context)
//
//        val binding = DialogProgressBinding.inflate(
//            LayoutInflater.from(
//                context
//            ), null, false
//        )
//        alertDialog.setCancelable(false)
//        alertDialog.setView(binding.root)
//
//        alertDialogProgress = alertDialog.create()
//        alertDialogProgress?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        alertDialogProgress?.show()
//    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }
}