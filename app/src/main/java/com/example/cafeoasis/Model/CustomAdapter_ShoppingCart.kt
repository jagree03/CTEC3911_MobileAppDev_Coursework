package com.example.cafeoasis.Model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.cafeoasis.R

class CustomAdapter_ShoppingCart(private val appContext: Context, private val myList: CartList) : BaseAdapter() {

    private val inflater: LayoutInflater
            = appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return myList.getNoOfItems()
    }

    override fun getItem(i: Int): Any? {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        var view: View? = view
        view = inflater.inflate(R.layout.activity_layout_listview_shoppingcart, parent, false)

        val name = view.findViewById<TextView>(R.id.textViewProdName)
        val price = view.findViewById<TextView>(R.id.textViewProdPrice)
        name.text = myList.getProduct(position).ProdName
        price.text = myList.getProduct(position).ProdPrice.toString()

        return view
    }
}