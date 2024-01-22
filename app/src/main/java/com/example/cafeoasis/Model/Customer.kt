package com.example.cafeoasis.Model

data class Customer(
    var CusId: Int, var CusFullName: String, var CusEmail: String,
    var CusPhoneNo: String, var CusUserName: String, var CusPassword: String, val CusIsActive: Int) {
}