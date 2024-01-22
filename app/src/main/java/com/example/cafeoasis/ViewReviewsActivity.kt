package com.example.cafeoasis

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.example.cafeoasis.Model.CustomAdapter_ShoppingCart
import com.example.cafeoasis.Model.CustomAdapter_ViewReviews
import com.example.cafeoasis.Model.DataBaseHelper
import com.example.cafeoasis.Model.FeedbackList

class ViewReviewsActivity : AppCompatActivity() {

    private lateinit var dBHelper: DataBaseHelper

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_reviews)
        val ProductID = intent.getIntExtra("ProductIdentifier", 0)
        val ProductName = intent.getStringExtra("ProductName").toString()
        val title = findViewById<TextView>(R.id.textView_ViewFeedbackFor)
        title.setText("View Feedback for $ProductName")

        dBHelper = DataBaseHelper(this)
        var fbList: FeedbackList = dBHelper.getAllFeedbacks(ProductID)

        val cAdapter = CustomAdapter_ViewReviews(applicationContext, fbList)
        val displayReviews : ListView = findViewById(R.id.ListView_ViewFeedback)
        displayReviews.adapter = cAdapter
    }
}