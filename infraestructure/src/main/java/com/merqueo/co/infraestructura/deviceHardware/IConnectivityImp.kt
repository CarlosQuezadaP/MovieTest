package com.merqueo.co.infraestructura.deviceHardware

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.merqueo.co.CORE.IConnectivity
import java.net.InetAddress
import java.net.UnknownHostException

class IConnectivityImp(private val context: Context) : IConnectivity {

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
                    return activeNetworkInfo != null && activeNetworkInfo.isConnected
                } catch (ex: Exception) {
                   val Aasd = ex
                }
            }
        }


        return false
    }


}