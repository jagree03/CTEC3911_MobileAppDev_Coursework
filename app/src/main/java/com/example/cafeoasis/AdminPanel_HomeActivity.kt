package com.example.cafeoasis

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class AdminPanel_HomeActivity : AppCompatActivity() {

    private lateinit var admin_name: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel_home)
        admin_name = intent.getStringExtra("Admin").toString()
        val nameLabel = findViewById<TextView>(R.id.textView_AdminPanelUserName)
        nameLabel.text = "Admin Account: $admin_name"
    }

    fun goToManageCafeItems(view: View) {
        val intent = Intent(this, AdminPanel_CafeMenuActivity::class.java)
        startActivity(intent)
    }

    fun goToManageCustomerOrders(view: View) {
        val intent = Intent(this, AdminPanel_OrdersActivity::class.java)
        startActivity(intent)
    }

    fun goToManageFeedback(view: View) {
        val intent = Intent(this, AdminPanel_FeedbackActivity::class.java)
        startActivity(intent)
    }

    fun goToManagePaymentData(view: View) {
        val intent = Intent(this, AdminPanel_CustomerPaymentDataActivity::class.java)
        startActivity(intent)
    }

    fun goToManageAccounts(view: View) {
        val intent = Intent(this, AdminPanel_AccountsActivity::class.java)
        startActivity(intent)
    }

    fun logout(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}