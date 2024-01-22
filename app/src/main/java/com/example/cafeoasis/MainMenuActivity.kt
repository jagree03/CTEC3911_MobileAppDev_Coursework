package com.example.cafeoasis

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import com.example.cafeoasis.Model.CartList
import com.example.cafeoasis.Model.DataBaseHelper
import com.example.cafeoasis.Model.Product
import org.w3c.dom.Text
import java.util.*

class MainMenuActivity : AppCompatActivity() {

    private var imageIndex = 0
    private lateinit var dbHelper: DataBaseHelper
    private lateinit var productList: ArrayList<Product>
    private var transferrableName: String = ""
    val MyCartList: CartList = CartList()
    val spinnerItems = arrayOf("Coffee", "Other_Drinks", "Biscuits", "Cakes")

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        setupSpinner()
        val spinner = findViewById<Spinner>(R.id.spinner_categoryDropDown)
        val intent_passed_username: String? = intent.getStringExtra("Customer").toString()
        if (intent_passed_username != null) {
            transferrableName = intent_passed_username
        }
        dbHelper = DataBaseHelper(this)
        //Listener code is from https://www.geeksforgeeks.org/spinner-in-kotlin/
        // explanation: the spinner has an event listener attached, which when the item is changed
        // in the drop down menu, the on item selected listener handles the generated event
        // how it handles the event is by using the database helper class to get all
        // the products, based on the selected item in the drop down list (spinner)
        // each item in the spinner represents a category and a category is a string
        // parameter to the getProducts method, that will use the parameter in its
        // SQL query statement that it runs upon my SQLite database.
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                imageIndex = 0
                productList = dbHelper.getProducts(spinner.selectedItem.toString())
                if (productList.size > 0) {
                    val imageDisplay = findViewById<ImageView>(R.id.imageView_Product)
                    var bmp: Bitmap =
                        BitmapFactory.decodeByteArray(
                            productList.get(imageIndex).ProdImage,
                            0,
                            productList.get(imageIndex).ProdImage!!.size
                        )
                    imageDisplay.setImageBitmap(bmp)
                    val productName = findViewById<TextView>(R.id.textView_ProdName)
                    val productPrice = findViewById<TextView>(R.id.textView_ProdPrice)
                    productName.setText(productList.get(imageIndex).ProdName)
                    productPrice.setText("£: ${productList.get(imageIndex).ProdPrice}")
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                productList = dbHelper.getProducts("Coffee")
                if (productList.size > 0) {
                    val imageDisplay = findViewById<ImageView>(R.id.imageView_Product)
                    var bmp: Bitmap =
                        BitmapFactory.decodeByteArray(
                            productList.get(imageIndex).ProdImage,
                            0,
                            productList.get(imageIndex).ProdImage!!.size
                        )
                    imageDisplay.setImageBitmap(bmp)
                    val productName = findViewById<TextView>(R.id.textView_ProdName)
                    val productPrice = findViewById<TextView>(R.id.textView_ProdPrice)
                    productName.setText(productList.get(imageIndex).ProdName)
                    productPrice.setText("£: ${productList.get(imageIndex).ProdPrice}")
                }
            }
        }
    } // end of onCreate code block

    @SuppressLint("SetTextI18n")
    fun buttonNextPresed(view: View) {
        if(imageIndex < productList.size - 1) {
            imageIndex++
            val imageDisplay = findViewById<ImageView>(R.id.imageView_Product)
            var bmp: Bitmap =
                BitmapFactory.decodeByteArray(productList.get(imageIndex).ProdImage, 0, productList.get(imageIndex).ProdImage!!.size)
            imageDisplay.setImageBitmap(bmp)
            val productName = findViewById<TextView>(R.id.textView_ProdName)
            val productPrice = findViewById<TextView>(R.id.textView_ProdPrice)
            productName.setText(productList.get(imageIndex).ProdName)
            productPrice.setText("£: ${productList.get(imageIndex).ProdPrice}")

        }
    }

    @SuppressLint("SetTextI18n")
    fun buttonPrevPresed(view: View) {
        if(imageIndex != 0) {
            imageIndex--
            val imageDisplay = findViewById<ImageView>(R.id.imageView_Product)
            var bmp: Bitmap =
                BitmapFactory.decodeByteArray(productList.get(imageIndex).ProdImage, 0, productList.get(imageIndex).ProdImage!!.size)
            imageDisplay.setImageBitmap(bmp)
            val productName = findViewById<TextView>(R.id.textView_ProdName)
            val productPrice = findViewById<TextView>(R.id.textView_ProdPrice)
            productName.setText(productList.get(imageIndex).ProdName)
            productPrice.setText("£: ${productList.get(imageIndex).ProdPrice}")
        }
    }

    fun setupSpinner() {
        val spinner = findViewById<Spinner>(R.id.spinner_categoryDropDown)
        val arrayAdapterSpinner = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, spinnerItems)
        spinner.adapter = arrayAdapterSpinner
    } // code reference: https://www.youtube.com/watch?v=lAckLFH7mIE

    fun goToProfileSettings(view: View) {
        val intent_profile = Intent(this, ProfileActivity::class.java)
        intent_profile.putExtra("LoggedUser", transferrableName)
        startActivity(intent_profile)
    }

    fun goToShoppingCart(view: View) {
        val intent = Intent(this, ShoppingCartActivity::class.java)
        intent.putExtra("LoggedUser", transferrableName)
        intent.putExtra("cart", MyCartList)
        startActivity(intent)
    }

    fun goToAddFeedbackScreen(view: View) {
        val intent = Intent(this, InputFeedbackActivity::class.java)
        intent.putExtra("LoggedUser", transferrableName)
        intent.putExtra("ProductIdentifier", productList.get(imageIndex).ProdId)
        startActivity(intent)
    }

    fun goToReadFeedbackScreen(view: View) {
        val intent = Intent(this, ViewReviewsActivity::class.java)
        intent.putExtra("ProductIdentifier", productList.get(imageIndex).ProdId)
        intent.putExtra("ProductName", productList.get(imageIndex).ProdName)
        startActivity(intent)
    }

    fun addToCart(view: View) {
        Toast.makeText(this,"Added to cart", Toast.LENGTH_SHORT).show()
        MyCartList.addProduct(productList.get(imageIndex))
    }

    fun goToMyPurchases(view: View) {
        val intent = Intent(this, MyPurchases::class.java)
        intent.putExtra("LoggedUser", transferrableName)
        startActivity(intent)
    }

    fun logout(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}