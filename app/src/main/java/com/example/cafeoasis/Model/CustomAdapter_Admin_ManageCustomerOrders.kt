package com.example.cafeoasis.Model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.cafeoasis.R

class CustomAdapter_Admin_ManageCustomerOrders(private val appContext: Context, private val myList: OrderList) : BaseAdapter() {

    private val inflater: LayoutInflater
            = appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return myList.getNoOfOrders()
    }

    override fun getItem(i: Int): Any? {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        var view: View? = view
        view = inflater.inflate(R.layout.activity_layout_listview_admin_manageorders, parent, false)

        val id = view.findViewById<TextView>(R.id.textView_layout_listview_orderid)
        val name = view.findViewById<TextView>(R.id.textView_layout_listview_cusfullname)
        val date = view.findViewById<TextView>(R.id.textView_layout_listview_orderdate)
        val status = view.findViewById<TextView>(R.id.textView_layout_listview_orderstatus)
        id.text = myList.getOrder(position).OrderId.toString()
        name.text = myList.getOrder(position).CusFullName
        date.text = myList.getOrder(position).OrderDate
        status.text = myList.getOrder(position).OrderStatus

        return view
    }
}