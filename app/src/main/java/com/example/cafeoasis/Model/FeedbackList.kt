package com.example.cafeoasis.Model

import java.io.Serializable
import java.lang.Exception
import java.lang.IllegalArgumentException

class FeedbackList (): Serializable {

    // Create Array List
    private var FeedbackList: ArrayList<Feedback> = ArrayList()
    private var noOfReviews: Int = 0

    fun addFeedbackRecord(fb: Feedback) {
        FeedbackList.add(fb)
        noOfReviews++
    }

    fun getNoOfReviews(): Int = noOfReviews

    fun getFeedback(index: Int) : Feedback {
        if(index !in 0..FeedbackList.size)
        {
            throw ArrayIndexOutOfBoundsException(" Index Out Of Bounds ")
        }
        return FeedbackList.get(index)
    }

    fun deleteAll() {
        FeedbackList.removeAll(FeedbackList)
        noOfReviews = 0
    }

    fun deleteFeedbackRecord(index: Int) {
        if (FeedbackList.size != 0) {
            FeedbackList.removeAt(index)
            noOfReviews = -1
        }
    }
}