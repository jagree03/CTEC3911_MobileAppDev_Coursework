package com.example.cafeoasis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class OrderEndActivity : AppCompatActivity() {

    private var transferrableName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_end)
        transferrableName = intent.getStringExtra("LoggedUser").toString()
    }

    fun goToHome(view: View) {
        val intent = Intent(this, MainMenuActivity::class.java)
        intent.putExtra("Customer", transferrableName)
        startActivity(intent)
    }
}