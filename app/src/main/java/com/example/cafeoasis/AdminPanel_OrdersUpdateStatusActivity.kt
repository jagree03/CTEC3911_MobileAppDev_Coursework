package com.example.cafeoasis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.cafeoasis.Model.DataBaseHelper
import com.example.cafeoasis.Model.Order
import org.w3c.dom.Text

class AdminPanel_OrdersUpdateStatusActivity : AppCompatActivity() {

    private lateinit var passedInOrderID: String
    private lateinit var dbHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel_orders_update_status)
        dbHelper = DataBaseHelper(this)
        passedInOrderID = intent.getStringExtra("OrderId").toString()
        val order = dbHelper.getOrderFromID(Order(passedInOrderID.toInt(), -1, "", "", "", ""))
        val id = findViewById<TextView>(R.id.textView_UpdateOrderStatusID)
        val cusid = findViewById<TextView>(R.id.textView_UpdateOrderStatusCustomerId)
        val cusfullname = findViewById<TextView>(R.id.textView_UpdateOrderStatusCustomerFullName)
        val status = findViewById<TextView>(R.id.textView_UpdateOrderStatusCustomerOrderStatus)
        val radiobutton_preparing = findViewById<RadioButton>(R.id.radioButton_preparing)
        val radioButton_ready = findViewById<RadioButton>(R.id.radioButton_ready)
        val radiobutton_cancel = findViewById<RadioButton>(R.id.radioButton_cancel)

        id.text = "Order id: ${order.OrderId}"
        cusid.text = "Cus id: ${order.CusId}"
        cusfullname.text = "Cus full name: ${order.CusFullName}"
        status.text = "Status: ${order.OrderStatus}"

        if (order.OrderStatus.equals("Preparing")) {
            radioButton_ready.isChecked = false
            radiobutton_cancel.isChecked = false
            radiobutton_preparing.isChecked = true
        } else if (order.OrderStatus.equals("Ready")) {
            radiobutton_preparing.isChecked = false
            radiobutton_cancel.isChecked = false
            radioButton_ready.isChecked = true
        }
    }

    fun updateRecord(view: View) {

        val radiobutton_preparing = findViewById<RadioButton>(R.id.radioButton_preparing)
        val radioButton_ready = findViewById<RadioButton>(R.id.radioButton_ready)
        val radiobutton_cancel = findViewById<RadioButton>(R.id.radioButton_cancel)

        if (radiobutton_preparing.isChecked == true) {
            Toast.makeText(this,"Updated order status to Preparing", Toast.LENGTH_SHORT).show()
            dbHelper.updateOrder(Order(passedInOrderID.toInt(), -1, "", "", "", "Preparing"))
        } else if (radioButton_ready.isChecked == true) {
            Toast.makeText(this,"Updated order status to Ready", Toast.LENGTH_SHORT).show()
            dbHelper.updateOrder(Order(passedInOrderID.toInt(), -1, "", "", "", "Ready"))
        } else {
            Toast.makeText(this,"Updated order status to Cancelled", Toast.LENGTH_SHORT).show()
            dbHelper.updateOrder(Order(passedInOrderID.toInt(), -1, "", "", "", "Cancelled"))
        }
    }
}