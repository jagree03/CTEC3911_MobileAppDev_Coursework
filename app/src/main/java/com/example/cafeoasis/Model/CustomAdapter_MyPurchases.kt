package com.example.cafeoasis.Model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.cafeoasis.R

class CustomAdapter_MyPurchases(private val appContext: Context, private val myList: OrderList) : BaseAdapter() {

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
        view = inflater.inflate(R.layout.activity_layout_listview_mypurchases, parent, false)

        val name = view.findViewById<TextView>(R.id.textView_layout_listview_mypurchases_cusfullname)
        val date = view.findViewById<TextView>(R.id.textView_layout_listview_mypurchases_Orderdate)
        val time = view.findViewById<TextView>(R.id.textView_layout_listview_mypurchases_ordertime)
        val status = view.findViewById<TextView>(R.id.textView_layout_listview_mypurchases_orderstatus)
        name.text = myList.getOrder(position).CusFullName
        date.text = myList.getOrder(position).OrderDate
        time.text = myList.getOrder(position).OrderTime
        status.text = myList.getOrder(position).OrderStatus

        return view
    }
}