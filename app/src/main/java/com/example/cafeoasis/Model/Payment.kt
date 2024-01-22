package com.example.cafeoasis.Model

data class Payment(val PaymentId: Int, var OrderId: Int, var PaymentType: String,
                    var Amount: Double, var PaymentDate: String) {
}