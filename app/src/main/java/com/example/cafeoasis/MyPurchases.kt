package com.example.cafeoasis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.cafeoasis.Model.CustomAdapter_MyPurchases
import com.example.cafeoasis.Model.CustomAdapter_ShoppingCart
import com.example.cafeoasis.Model.Customer
import com.example.cafeoasis.Model.DataBaseHelper
import com.example.cafeoasis.Model.OrderList

class MyPurchases : AppCompatActivity() {

    private lateinit var transferrableName: String
    private lateinit var dBHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_purchases)
        transferrableName = intent.getStringExtra("LoggedUser").toString()
        dBHelper = DataBaseHelper(this)
        val customer = dBHelper.getCustomerOnlyUser(Customer(-1, "", "", "", transferrableName, "", 1))
        val CustomerOrdersList: OrderList = dBHelper.getAllOrdersForSpecificCustomer(customer.CusId)
        val cAdapter = CustomAdapter_MyPurchases(applicationContext, CustomerOrdersList)
        val displayOrders : ListView = findViewById(R.id.ListView_MyPurchases)
        displayOrders.adapter = cAdapter

    }
}