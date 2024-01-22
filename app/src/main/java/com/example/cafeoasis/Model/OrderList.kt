package com.example.cafeoasis.Model

import java.io.Serializable
import java.lang.Exception
import java.lang.IllegalArgumentException

class OrderList (): Serializable {

    // Create Array List
    private var OrderList: ArrayList<Order> = ArrayList()
    private var noOfOrders: Int = 0

    fun addOrder(o: Order) {
        OrderList.add(o)
        noOfOrders++
    }

    fun getNoOfOrders(): Int = noOfOrders

    fun getOrder(index: Int) : Order {
        if(index !in 0..OrderList.size)
        {
            throw ArrayIndexOutOfBoundsException(" Index Out Of Bounds ")
        }
        return OrderList.get(index)
    }


    fun deleteAll() {
        OrderList.removeAll(OrderList)
        noOfOrders = 0
    }

    fun deleteItem(index: Int) {
        if (OrderList.size != 0) {
            OrderList.removeAt(index)
            noOfOrders = -1
        }
    }
}