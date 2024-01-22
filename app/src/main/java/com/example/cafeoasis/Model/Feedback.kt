package com.example.cafeoasis.Model

data class Feedback(
    var FeedbackId: Int, var CusId: Int, var ProdId: Int,
    var Comment: String, var StarRating: Float
) {
}