package com.example.cafeoasis

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.TextureView
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.cafeoasis.Model.CartList
import com.example.cafeoasis.Model.Customer
import com.example.cafeoasis.Model.DataBaseHelper
import com.example.cafeoasis.Model.Order
import com.example.cafeoasis.Model.OrderDetails
import com.example.cafeoasis.Model.Payment
import com.example.cafeoasis.Model.Product
import java.time.LocalDate
import java.time.LocalTime

class PaymentActivity : AppCompatActivity() {

    private lateinit var dbHelper: DataBaseHelper
    private var transferrableName = ""
    private var cost: Double = 0.0
    private var paymentType: String = ""
    private lateinit var itemsInCartList: CartList

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        val orderCostLabel =
            findViewById<TextView>(R.id.textView_OrderTotalPaymentScreenLabelAndValue)
        cost = intent.getDoubleExtra("TotalCost", 999.99)
        itemsInCartList = intent.getSerializableExtra("cart") as CartList
        transferrableName = intent.getStringExtra("LoggedUser").toString()
        orderCostLabel.setText("Order Total: ${String.format("%.2f", cost).toDouble()}")
    }

    fun creditCardClicked(view: View) {
        val textview_credit = findViewById<TextView>(R.id.textView_creditcard)
        val textview_debit = findViewById<TextView>(R.id.textView_debitcard)

        textview_credit.setText("Selected")
        textview_debit.setText("Debit Card")
        paymentType = "Credit Card"
    }

    fun debitCardClicked(view: View) {
        val textview_credit = findViewById<TextView>(R.id.textView_creditcard)
        val textview_debit = findViewById<TextView>(R.id.textView_debitcard)

        textview_credit.setText("Credit Card")
        textview_debit.setText("Selected")
        paymentType = "Debit Card"
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun goToOrderEnd(view: View) {

        val cardholder_name = findViewById<EditText>(R.id.editText_CardholderName).text.toString()
        val card_num = findViewById<EditText>(R.id.editText_CardNumber).text.toString()
        val card_cvv = findViewById<EditText>(R.id.editText_CardCVV).text.toString()
        val card_exp = findViewById<EditText>(R.id.editText_CardExpiration).text.toString()

        if (cardholder_name.isEmpty()) {
            Toast.makeText(this, "Enter card holder name", Toast.LENGTH_SHORT).show()
        } else if (card_num.isEmpty()) {
            Toast.makeText(this, "Enter card number", Toast.LENGTH_SHORT).show()
        } else if (card_cvv.isEmpty()) {
            Toast.makeText(this, "Enter card CVV", Toast.LENGTH_SHORT).show()
        } else if (card_exp.isEmpty()) {
            Toast.makeText(this, "Enter card expiration month/year", Toast.LENGTH_SHORT).show()
        } else if (cardholder_name.isEmpty() && card_num.isEmpty() && card_cvv.isEmpty() && card_exp.isEmpty()) {
            Toast.makeText(this, "Please enter the details", Toast.LENGTH_SHORT).show()
        } else {
            dbHelper = DataBaseHelper(this)
            val currentCustomer =
                dbHelper.getCustomerOnlyUser(Customer(-1, "", "", "", transferrableName, "", 1))

            var date = LocalDate.now().toString()
            var time = LocalTime.now().toString()

            dbHelper.addOrder(
                Order(
                    -1,
                    currentCustomer.CusId,
                    currentCustomer.CusFullName,
                    date,
                    time,
                    "Preparing"
                )
            )
            val placedOrder = dbHelper.retrieveOrder(
                Order(
                    -1,
                    currentCustomer.CusId,
                    currentCustomer.CusFullName,
                    date,
                    time,
                    "Preparing"
                )
            )

            dbHelper.addPayment(Payment(-1, placedOrder.OrderId, paymentType, cost, date))

            var i: Int = 0
            while (i < itemsInCartList.getNoOfItems()) {
                dbHelper.addOrderDetails(
                    OrderDetails(
                        -1,
                        placedOrder.OrderId,
                        itemsInCartList.getProduct(i).ProdId
                    )
                )
                i++
            }
            val intent = Intent(this, OrderEndActivity::class.java)
            intent.putExtra("LoggedUser", transferrableName)
            startActivity(intent)
        }
    }
}