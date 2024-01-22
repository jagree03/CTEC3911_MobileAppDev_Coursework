package com.example.cafeoasis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.example.cafeoasis.Model.Customer
import com.example.cafeoasis.Model.DataBaseHelper
import com.example.cafeoasis.Model.Feedback

class InputFeedbackActivity : AppCompatActivity() {

    private lateinit var transferrableName: String
    private lateinit var dBHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_feedback)

        transferrableName = intent.getStringExtra("LoggedUser").toString()
    }

    fun addFeedback(view: View) {
        Toast.makeText(this,"Feedback submitted, Thank you.", Toast.LENGTH_LONG).show()
        dBHelper = DataBaseHelper(this)
        val comment = findViewById<EditText>(R.id.editTextTextMultiLine_CommentInput)
        val rating = findViewById<RatingBar>(R.id.ratingBar_StarRating)
        val customer = dBHelper.getCustomerOnlyUser(Customer(-1, "", "", "", transferrableName, "", 1))
        val passedInProductIdentifier: Int = intent.getIntExtra("ProductIdentifier", 0).toInt()
        dBHelper.addFeedback(Feedback(-1, customer.CusId, passedInProductIdentifier, comment.text.toString(), rating.rating))
    }
}