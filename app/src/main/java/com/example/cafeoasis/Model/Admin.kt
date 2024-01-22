package com.example.cafeoasis.Model

data class Admin(val AdminId: Int, var AdminFullName: String, var AdminEmail: String,
                    var AdminPhoneNo: String, var AdminUserName: String, val AdminPassword: String, val AdminIsActive: Int) {
}