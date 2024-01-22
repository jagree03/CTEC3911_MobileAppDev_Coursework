package com.example.cafeoasis

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeoasis.Model.Admin
import com.example.cafeoasis.Model.Customer
import com.example.cafeoasis.Model.DataBaseHelper
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Timer
import kotlin.concurrent.schedule

var attemptsValue: Int = 3

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    } // code from https://gist.github.com/dlimpid/68761108b49b7c1ddb84250dfac78495


    fun loginButton(view: View) {
        val attempts = findViewById<TextView>(R.id.textView_Attempts)
        attemptsValue -= 1
        if (attemptsValue == 2) {
            attempts.setText("2 / 3 attempts remaining.")
        } else if (attemptsValue == 1) {
            attempts.setText("1 / 3 attempts remaining.")
        } else if (attemptsValue == 0) {
            attempts.setText("0 / 3 attempts remaining.")
            // Reference: alertDialog code by 'Aman Alam' from https://stackoverflow.com/questions/4850493/how-to-open-a-dialog-when-i-click-a-button
            // converted from Java to Kotlin by Android Studio IDE converter
            val alertDialog: AlertDialog = androidx.appcompat.app.AlertDialog.Builder(this).create()
            alertDialog.setTitle("Out of attempts!")
            alertDialog.setMessage("Wait 5 seconds")
            alertDialog.show()
            Timer().schedule(5000) {
                alertDialog.setMessage("Try again.")
                attemptsValue = 3
                alertDialog.dismiss()
            } // timer code reference: https://stackoverflow.com/questions/71070216/how-to-set-delay-in-kotlin/71070467#71070467
            attempts.setText("3 / 3 attempts remaining")
        }
        val userName = findViewById<EditText>(R.id.editText_Username).text.toString()
        val userPassword = findViewById<EditText>(R.id.editText_Password).text.toString().md5()

        if(userName.isEmpty() || userPassword.isEmpty())
            Toast.makeText(this,"Please insert Username and Password", Toast.LENGTH_LONG).show()
        else {
            val myDataBase = DataBaseHelper(this)
            val result = myDataBase.getCustomer(
                Customer(-1," ", " ",
                    " ", userName, userPassword, 1)
            )
            println("DataBase =  $result")
            if( result == -1) {
                val result_admin =
                    myDataBase.getAdmin(Admin(-1, "", "", "", userName, userPassword, 1))
                if (result_admin == -1) {
                    Toast.makeText(
                        this,
                        "User not found or password incorrect, try again.",
                        Toast.LENGTH_LONG
                    ).show()
                } else if (result_admin == -2) {
                    Toast.makeText(this, "Error Cannot Open or Access Database", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(this, "Welcome Admin", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, AdminPanel_HomeActivity::class.java)
                    intent.putExtra("Admin", userName)
                    startActivity(intent)
                }
            }
            else if( result == -2)
                Toast.makeText(this,"Error Cannot Open or Access Database", Toast.LENGTH_LONG).show()
            else {
                Toast.makeText(this,"Welcome", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainMenuActivity::class.java)
                intent.putExtra("Customer", userName)
                startActivity(intent)
            }
        }
    }

    fun goToRegistration(view: View) {

        val intent = Intent(this, RegisterAccountActivity::class.java)
        startActivity(intent)
    }



}