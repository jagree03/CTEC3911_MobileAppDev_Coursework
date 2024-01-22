package com.example.cafeoasis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import com.example.cafeoasis.Model.CartList
import com.example.cafeoasis.Model.CustomAdapter_Admin_ManageCafeMenu
import com.example.cafeoasis.Model.CustomAdapter_Admin_ManageCustomerOrders
import com.example.cafeoasis.Model.DataBaseHelper
import com.example.cafeoasis.Model.OrderList

class AdminPanel_OrdersActivity : AppCompatActivity() {

    private lateinit var dBHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel_orders)

        dBHelper = DataBaseHelper(this)

        val AllOrderList: OrderList = dBHelper.getAllOrders()
        val cAdapter = CustomAdapter_Admin_ManageCustomerOrders(applicationContext, AllOrderList)
        val displayOrders = findViewById<ListView>(R.id.ListView_ManageCustomerOrders)
        displayOrders.adapter = cAdapter
        displayOrders.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val manageOrdID = findViewById<TextView>(R.id.editText_ManageCusOrderId)
                val manageCusId = findViewById<TextView>(R.id.editText_ManageCusOrderCustomerId)
                val manageFullName = findViewById<TextView>(R.id.editText_ManageCusOrderCustomerFullName)
                val manageDate = findViewById<TextView>(R.id.editText_ManageCusOrderCustomerOrderDate)
                val manageTime = findViewById<TextView>(R.id.editText_ManageCusOrderCustomerOrderTime)
                val manageStatus = findViewById<TextView>(R.id.editText_ManageCusOrderCustomerOrderStatus)

                manageOrdID.setText(AllOrderList.getOrder(position).OrderId.toString())
                manageCusId.setText(AllOrderList.getOrder(position).CusId.toString())
                manageFullName.setText(AllOrderList.getOrder(position).CusFullName)
                manageDate.setText(AllOrderList.getOrder(position).OrderDate)
                manageTime.setText(AllOrderList.getOrder(position).OrderTime)
                manageStatus.setText(AllOrderList.getOrder(position).OrderStatus)
            }
        }
    }

    fun goToUpdateOrderStatus(view: View) {
        val intent = Intent(this, AdminPanel_OrdersUpdateStatusActivity::class.java)
        val manageOrdID: String = findViewById<TextView>(R.id.editText_ManageCusOrderId).text.toString()
        intent.putExtra("OrderId", manageOrdID)
        startActivity(intent)
    }
}