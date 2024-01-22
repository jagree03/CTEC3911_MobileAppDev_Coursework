package com.example.cafeoasis.Model
import java.io.Serializable

data class Product(
    val ProdId: Int, var ProdName: String, var ProdCategory: String,
    var ProdPrice: Double, var ProdImage: ByteArray?, var ProdAvailable: Int) : Serializable {
}