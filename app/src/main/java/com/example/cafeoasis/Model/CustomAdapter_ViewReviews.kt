package com.example.cafeoasis.Model

import android.content.Context
import android.media.Rating
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RatingBar
import android.widget.TextView
import com.example.cafeoasis.R

class CustomAdapter_ViewReviews(private val appContext: Context, private val myList: FeedbackList) : BaseAdapter() {

    private val inflater: LayoutInflater
            = appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return myList.getNoOfReviews()
    }

    override fun getItem(i: Int): Any? {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        var view: View? = view
        view = inflater.inflate(R.layout.activity_layout_listview_view_feedback_customer, parent, false)

        //val name = view.findViewById<TextView>(R.id.textView_NameOfReviewer)
        val rating = view.findViewById<RatingBar>(R.id.view_ratingBar)
        val comment = view.findViewById<TextView>(R.id.textView_CommentReview)
        rating.rating = myList.getFeedback(position).StarRating
        comment.text = myList.getFeedback(position).Comment

        return view
    }
}