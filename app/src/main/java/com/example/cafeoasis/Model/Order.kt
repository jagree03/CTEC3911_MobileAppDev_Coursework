package com.example.cafeoasis.Model

data class Order(
    var OrderId: Int, var CusId: Int, var CusFullName: String,
    var OrderDate: String, var OrderTime: String, var OrderStatus: String) {
}