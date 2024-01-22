package com.example.cafeoasis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.cafeoasis.Model.Admin
import com.example.cafeoasis.Model.Customer
import com.example.cafeoasis.Model.DataBaseHelper
import java.math.BigInteger
import java.security.MessageDigest

class RegisterAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_account)
        val message = findViewById<TextView>(R.id.textView_Message)
        message.isVisible = false
    }

    fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    } // code from https://gist.github.com/dlimpid/68761108b49b7c1ddb84250dfac78495

    fun saveNewCustomerButton(view: View) {

        val fullname = findViewById<EditText>(R.id.editText_FullName).text.toString()
        val email = findViewById<EditText>(R.id.editText_Email).text.toString()
        val phonenum = findViewById<EditText>(R.id.editText_PhoneNo).text.toString()
        val username  = findViewById<EditText>(R.id.editText_Username2).text.toString()
        val password  = findViewById<EditText>(R.id.editText_Password2).text.toString().md5()
        var active = 1

        val message = findViewById<TextView>(R.id.textView_Message)
        message.isVisible = true

        if(fullname.isEmpty()) { // full name required
            Toast.makeText(this,"First name and last name are required!",Toast.LENGTH_LONG).show()
            message.text = "Full name is required!"
        } else if (email.isEmpty()) { // email required
            Toast.makeText(this, "Email required!", Toast.LENGTH_LONG).show()
            message.text = "Email required!"
        } else if (phonenum.isEmpty()) {
            Toast.makeText(this, "Phone number required!", Toast.LENGTH_LONG).show()
            message.text = "Phone number required!"
        } else if (username.isEmpty()) {
            Toast.makeText(this, "Username required!", Toast.LENGTH_LONG).show()
            message.text = "Username required!"
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Password required!", Toast.LENGTH_LONG).show()
            message.text = "Password required!"
        } else if (fullname.isEmpty() && email.isEmpty() && phonenum.isEmpty() && username.isEmpty() && password.isEmpty()) {
            Toast.makeText(this, "Fill in all the details.", Toast.LENGTH_LONG).show()
            message.text = "Fill in all the details to register account"
        }
        else { // Save data
            // Create object
            val newCustomer = Customer(-1,fullname, email, phonenum, username, password, active)
            val mydatabase = DataBaseHelper(this)
            val result = mydatabase.addCustomer(newCustomer)

            when(result) {

                -1 -> message.text = "Error on creating new account"
                -2 -> message.text = "Error can not open/create database"
                -3 -> message.text = "User name is already exist"
                else ->  {
                    message.text = "Your details has been add to the database successfully "
                }
            }
        }
    }

    fun saveNewAdminButton(view: View) {

        val fullname = findViewById<EditText>(R.id.editText_FullName).text.toString()
        val email = findViewById<EditText>(R.id.editText_Email).text.toString()
        val phonenum = findViewById<EditText>(R.id.editText_PhoneNo).text.toString()
        val username  = findViewById<EditText>(R.id.editText_Username2).text.toString()
        val password  = findViewById<EditText>(R.id.editText_Password2).text.toString().md5()
        var active = 1

        val message = findViewById<TextView>(R.id.textView_Message)
        message.isVisible = true

        if(fullname.isEmpty()) { // full name required
            Toast.makeText(this,"First name and last name are required!",Toast.LENGTH_LONG).show()
            message.text = "Full name is required!"
        } else if (email.isEmpty()) { // email required
            Toast.makeText(this, "Email required!", Toast.LENGTH_LONG).show()
            message.text = "Email required!"
        } else if (phonenum.isEmpty()) {
            Toast.makeText(this, "Phone number required!", Toast.LENGTH_LONG).show()
            message.text = "Phone number required!"
        } else if (username.isEmpty()) {
            Toast.makeText(this, "Username required!", Toast.LENGTH_LONG).show()
            message.text = "Username required!"
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Password required!", Toast.LENGTH_LONG).show()
            message.text = "Password required!"
        } else if (fullname.isEmpty() && email.isEmpty() && phonenum.isEmpty() && username.isEmpty() && password.isEmpty()) {
            Toast.makeText(this, "Fill in all the details.", Toast.LENGTH_LONG).show()
            message.text = "Fill in all the details to register account"
        }
        else { // Save data
            // Create object
            val newAdmin = Admin(-1,fullname, email, phonenum, username, password, active)
            val mydatabase = DataBaseHelper(this)
            val result = mydatabase.addAdmin(newAdmin)

            when(result) {

                -1 -> message.text = "Error on creating new account"
                -2 -> message.text = "Error can not open/create database"
                -3 -> message.text = "User name is already exist"
                else ->  {
                    message.text = "Your details has been add to the database successfully "
                }
            }
        }
    }
}