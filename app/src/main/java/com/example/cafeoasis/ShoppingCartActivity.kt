package com.example.cafeoasis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import com.example.cafeoasis.Model.CartList
import com.example.cafeoasis.Model.CustomAdapter_ShoppingCart

var orderTotal = 0.0

class ShoppingCartActivity : AppCompatActivity() {

    private var transferrableName = ""
    private lateinit var itemsInCartList: CartList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)
        transferrableName = intent.getStringExtra("LoggedUser").toString()
        itemsInCartList = intent.getSerializableExtra("cart")  as CartList
        orderTotal = itemsInCartList.getTotalCost()
        //Log.d("TAG", itemsInCart.get(0).toString())
        val cAdapter = CustomAdapter_ShoppingCart(applicationContext, itemsInCartList)
        val displayCart : ListView = findViewById(R.id.ListViewShoppingCart)
        displayCart.adapter = cAdapter

        val orderTotalLabel = findViewById<TextView>(R.id.textView_OrderTotalShoppingCartValue)
        orderTotalLabel.setText("" + String.format("%.2f", itemsInCartList.getTotalCost()).toDouble())
    }

    fun goToPayment(view: View) {
        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra("LoggedUser", transferrableName)
        intent.putExtra("TotalCost", orderTotal)
        intent.putExtra("cart", itemsInCartList)
        startActivity(intent)
    }
}