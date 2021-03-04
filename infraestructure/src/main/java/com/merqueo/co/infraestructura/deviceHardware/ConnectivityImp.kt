package com.merqueo.co.infraestructura.deviceHardware

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.merqueo.co.usecases.Connectivity
import java.net.InetAddress
import java.net.UnknownHostException

class ConnectivityImp(private val context: Context) : Connectivity {

    override fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.also {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    return if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) true else if (capabilities.hasTransport(
                            NetworkCapabilities.TRANSPORT_WIFI
                        )
                    ) true else capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                }
            } else {
                try {
                    val activeNetworkInfo = connectivityManager.activeNetworkInfo
                    return activeNetworkInfo != null && activeNetworkInfo.isConnected && isInternetAvailable()
                } catch (ex: Exception) {
                    // Log
                }
            }
        }


        return false
    }

    private fun isInternetAvailable(): Boolean {
        try {
            val address = InetAddress.getByName("www.google.com")
            return !address.equals("")
        } catch (e: UnknownHostException) {
            // Log
        }
        return false
    }
}