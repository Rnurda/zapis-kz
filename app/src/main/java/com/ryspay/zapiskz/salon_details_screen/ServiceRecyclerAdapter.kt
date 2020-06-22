package com.ryspay.zapiskz.salon_details_screen

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ryspay.zapiskz.R
import com.ryspay.zapiskz.vodetails.Service

const val SERVICE_TAG = "SERVICE_TAG"

class ServiceRecyclerAdapter(private var context: Context, private var click: OnClickService): RecyclerView.Adapter<ServiceRecyclerAdapter.ViewHolder>() {

    private var items: List<Service> = ArrayList()
    var mClick: OnClickService = click
    private val TAG = "ServicesRecyclerAdapter"

    fun setItems(maseters: List<Service>) {
        items = maseters
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_vertical_services, parent, false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, mClick, position, context)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val service_name = view.findViewById(R.id.vertical_service_title) as TextView
        val service_duration = view.findViewById(R.id.vertical_service_time) as TextView
        val service_cost = view.findViewById(R.id.vertical_service_cost) as TextView
        val service_prepayment = view.findViewById(R.id.service_prepayment) as TextView
        val item_service_cv = view.findViewById(R.id.item_service_cv) as ConstraintLayout

        @SuppressLint("SetTextI18n")

        fun bind(service: Service, mClick: OnClickService, position: Int, context: Context) {
            service_name.text = service.name
            service_cost.text = service.priceStr
            service_duration.text = "${service.duration} минут \u25CF"
            if(service.prepaymentAmount!=null){
                service_prepayment.visibility = View.VISIBLE
                service_prepayment.text = "Предоплата ${service.prepaymentAmount} ₸"
            }
            item_service_cv.setOnClickListener { view ->
                mClick.onClickListner(position, service.id, SERVICE_TAG)
            }
        }
    }

    interface OnClickService {
        fun onClickListner(position: Int, id: Int, tag: String)
    }
}