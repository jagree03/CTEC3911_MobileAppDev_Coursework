package com.example.cafeoasis.Model

import java.io.Serializable
import java.lang.Exception
import java.lang.IllegalArgumentException

class CartList (): Serializable {

    // Create Array List
    private var cartList: ArrayList<Product> = ArrayList()
    private var noOfItems: Int = 0

    fun addProduct(p: Product) {
        cartList.add(p)
        noOfItems++
    }

    fun getNoOfItems(): Int = noOfItems

    fun getProduct(index: Int) : Product {
        if(index !in 0..cartList.size)
        {
            throw ArrayIndexOutOfBoundsException(" Index Out Of Bounds ")
        }
        return cartList.get(index)
    }

    fun deleteAll() {
        cartList.removeAll(cartList)
        noOfItems = 0
    }

    fun deleteItem(index: Int) {
        if (cartList.size != 0) {
            cartList.removeAt(index)
            noOfItems = -1
        }
    }

    fun getTotalCost() : Double {
        var cost: Double = 0.0
        for (p: Product in cartList) {
            cost += p.ProdPrice
        }
        return cost
    }

}