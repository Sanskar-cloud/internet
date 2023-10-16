package com.example.internet

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signUpButton = findViewById<Button>(R.id.btn_signup)
        val login= findViewById<Button>(R.id.btn_login)

        if (isInternetConnected()) {

            signUpButton.setOnClickListener {
                val intent = Intent(this, signup::class.java)
                startActivity(intent)
            }

        } else {

            showNoInternetDialog()
        }
    }

    private fun isInternetConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private fun showNoInternetDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("No Internet Connection")
        alertDialogBuilder.setMessage("Please connect to the internet to use this app.")
        alertDialogBuilder.setPositiveButton("Settings") { _: DialogInterface, _: Int ->
            val settingsIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
            startActivity(settingsIntent)
        }
        alertDialogBuilder.setNegativeButton("Exit") { _: DialogInterface, _: Int ->
            finish()
        }
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.create().show()
    }
}
