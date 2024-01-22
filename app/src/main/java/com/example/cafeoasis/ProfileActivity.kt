package com.example.cafeoasis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.cafeoasis.Model.Customer
import com.example.cafeoasis.Model.DataBaseHelper

class ProfileActivity : AppCompatActivity() {

    private lateinit var dbHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        val fullname = findViewById<EditText>(R.id.editText_FullNameProfileSettings)
        val email = findViewById<EditText>(R.id.editText_EmailProfileSettings)
        val phone = findViewById<EditText>(R.id.editText_PhoneNoProfileSettings)
        val user = findViewById<EditText>(R.id.editText_UsernameProfileSettings)
        val pass = findViewById<EditText>(R.id.editText_PasswordProfileSettings)

        dbHelper = DataBaseHelper(this)
        val intent_passed_username: String = intent.getStringExtra("LoggedUser").toString()
        val customerDetails: Customer = dbHelper.getCustomerOnlyUser(Customer(-1, " ", " ", "",
            intent_passed_username, " ", 1))

        fullname.setText(customerDetails.CusFullName)
        email.setText(customerDetails.CusEmail)
        phone.setText(customerDetails.CusPhoneNo)
        user.setText(customerDetails.CusUserName)
        pass.setText(customerDetails.CusPassword)



    }
}