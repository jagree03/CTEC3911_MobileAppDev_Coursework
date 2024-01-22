package com.example.cafeoasis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.View
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.cafeoasis.Model.CartList
import com.example.cafeoasis.Model.CustomAdapter_Admin_ManageCafeMenu
import com.example.cafeoasis.Model.CustomAdapter_ShoppingCart
import com.example.cafeoasis.Model.DataBaseHelper
import com.example.cafeoasis.Model.Product
import java.sql.DatabaseMetaData
import java.util.ArrayList

class AdminPanel_CafeMenuActivity : AppCompatActivity() {

    private lateinit var dBHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel_cafe_menu)
        dBHelper = DataBaseHelper(this)

        val list = findViewById<ListView>(R.id.ListView_ManageCafeMenuItems)

        val AllProductList: CartList = dBHelper.getAllProducts()
        val cAdapter = CustomAdapter_Admin_ManageCafeMenu(applicationContext, AllProductList)
        val displayProducts : ListView = findViewById(R.id.ListView_ManageCafeMenuItems)
        displayProducts.adapter = cAdapter
        displayProducts.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val manageID = findViewById<TextView>(R.id.editText_ManageCafeMenuItemsProdId)
                val manageName = findViewById<TextView>(R.id.editText_AddNewItemProdName)
                val manageCategory = findViewById<EditText>(R.id.editText_cafemenucategory)
                val managePrice = findViewById<TextView>(R.id.editText_AddNewItemProdPrice)
                val checkboxAvailable =
                    findViewById<CheckBox>(R.id.checkBox_ManageCafeMenuItemsProdAvailable)


                manageID.setText(AllProductList.getProduct(position).ProdId.toString())
                manageName.setText(AllProductList.getProduct(position).ProdName)
                managePrice.setText(AllProductList.getProduct(position).ProdPrice.toString())
                manageCategory.setText(AllProductList.getProduct(position).ProdCategory)
                checkboxAvailable.isChecked = AllProductList.getProduct(position).ProdAvailable == 1
            }
        }
    }


    fun goToAddItem(view: View) {
        val intent = Intent(this, AdminPanel_AddItemActivity::class.java)
        startActivity(intent)
    }

    fun remove(view: View) {
        val manageID = findViewById<TextView>(R.id.editText_ManageCafeMenuItemsProdId).text.toString().toInt()
        dBHelper.removeProduct(manageID)
        Toast.makeText(this,"Product removed", Toast.LENGTH_SHORT).show()
    }

    fun update(view: View) {
        val manageID = findViewById<TextView>(R.id.editText_ManageCafeMenuItemsProdId).text.toString().toInt()
        val manageName = findViewById<TextView>(R.id.editText_AddNewItemProdName).text.toString()
        val manageCategory = findViewById<EditText>(R.id.editText_cafemenucategory).text.toString()
        val managePrice = findViewById<TextView>(R.id.editText_AddNewItemProdPrice).text.toString().toDouble()
        val checkboxAvailable = findViewById<CheckBox>(R.id.checkBox_ManageCafeMenuItemsProdAvailable)
        var availabilityValue = 0

        if (checkboxAvailable.isChecked == true) {
            availabilityValue = 1
        } else {
            availabilityValue = 0
        }
        dBHelper.updateProduct(Product(manageID, manageName, manageCategory, managePrice, null, availabilityValue))
        Toast.makeText(this,"Product updated", Toast.LENGTH_SHORT).show()
    }

}