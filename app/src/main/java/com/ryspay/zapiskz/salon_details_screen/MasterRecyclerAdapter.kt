package com.ryspay.zapiskz.salon_details_screen

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ryspay.zapiskz.R
import com.ryspay.zapiskz.vodetails.Master


const val MASTER_TAG = "MASTER_TAG"
class MasterRecyclerAdapter(private var context: Context, private var click: OnClick): RecyclerView.Adapter<MasterRecyclerAdapter.ViewHolder>() {

    private var items : List<Master> = ArrayList()
    var mClick : OnClick = click
    private val TAG = "MasterRecyclerAdapter"

    fun setItems(maseters: List<Master>){
        items = maseters
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_vertical_masters, parent, false))
    }

    override fun getItemCount(): Int{
            return items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        Log.d(TAG, "getItemCount: ${items.count()}")
        holder.bind(item, mClick, position,context)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val master_name_text = view.findViewById(R.id.masters_name_tv) as TextView
        val masters_avatar = view.findViewById(R.id.master_avatar_cv) as ImageView
        val maseter_speciality = view.findViewById(R.id.masters_speciality_tv) as TextView
        val item_master_cv = view.findViewById(R.id.item_master_cv) as ConstraintLayout
        @SuppressLint("SetTextI18n")

        fun bind(masters: Master, mClick: OnClick, position: Int, context: Context){
            master_name_text.text = masters.name+" "+masters.surname
            maseter_speciality.text = masters.profession
            Glide.with(context).load(masters.avatarUrl).into(masters_avatar)
            item_master_cv.setOnClickListener { view ->
                mClick.onClickListner(position, masters.id, MASTER_TAG)
            }
        }
    }
    interface OnClick{
        fun onClickListner(position: Int, id: Int, tag: String)
    }
}