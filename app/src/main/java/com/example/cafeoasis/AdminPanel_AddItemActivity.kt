package com.example.cafeoasis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.cafeoasis.Model.DataBaseHelper
import com.example.cafeoasis.Model.Product

class AdminPanel_AddItemActivity : AppCompatActivity() {

    private lateinit var dbHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel_add_item)
        dbHelper = DataBaseHelper(this)


    }

    fun add(view: View) {
        val name = findViewById<EditText>(R.id.editText_AddNewItemProdName).text.toString()
        val category = findViewById<EditText>(R.id.editText_additem_cat).text.toString()
        val price = findViewById<EditText>(R.id.editText_AddNewItemProdPrice).text.toString().toDouble()
        val availability = findViewById<CheckBox>(R.id.checkBox_AddNewItemProdAvailable)
        var availabilityValue = 0

        if (availability.isChecked == true) {
            availabilityValue = 1
        } else {
            availabilityValue = 0
        }
        dbHelper.addProduct(Product(-1, name, category, price, null, availabilityValue))
        Toast.makeText(this,"Added new product", Toast.LENGTH_SHORT).show()
    }
}