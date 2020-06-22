package com.ryspay.zapiskz.home_screen

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ryspay.zapiskz.R
import com.ryspay.zapiskz.vohome.BaseFirm

class FirmsRecyclerAdapter(private var context: Context, private var click: OnClick): RecyclerView.Adapter<FirmsRecyclerAdapter.ViewHolder>() {

    private var items : List<BaseFirm> = ArrayList()
    var mClick : OnClick = click

    fun setItems(popularFirm: List<BaseFirm>){
        items = popularFirm
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(
                R.layout.item_salons_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int{
        return items.count()

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, mClick, position,context)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val type_text = view.findViewById(R.id.type_text) as TextView
        val item_salon_cv = view.findViewById(R.id.item_salon_cv) as ConstraintLayout
        val title = view.findViewById(R.id.salon_title) as TextView
        val street = view.findViewById(R.id.salon_street) as TextView
        val salon_image = view.findViewById(R.id.salon_image) as ImageView
        val cashback = view.findViewById(R.id.cashback_tv) as TextView
        val people = view.findViewById(R.id.rating_people_image) as ImageView
        val checked_people_count = view.findViewById(R.id.check_rating_count) as TextView
        val rating = view.findViewById(R.id.rating_start_count) as TextView
        @SuppressLint("SetTextI18n")
        fun bind(popularFirm: BaseFirm, mClick: OnClick, position: Int, context: Context){
            type_text.text = popularFirm.type
            title.text = popularFirm.name
            street.text = popularFirm.address.split("<")[0]
            if(popularFirm.prepaymentCashbackAmount!=null){
                cashback.visibility = View.VISIBLE
                cashback.text = "Кэшбек ${popularFirm.prepaymentCashbackAmount}"
            }
            if(popularFirm.checkRating > 0){
                people.visibility = View.VISIBLE
                checked_people_count.visibility = View.VISIBLE
                checked_people_count.text = "${popularFirm.checkRating}"
            }
            if(popularFirm.averageRating!=null){
                rating.text = "${popularFirm.averageRating}"
            }
            Glide.with(context).load(popularFirm.avatarUrl).into(salon_image)
            item_salon_cv.setOnClickListener { view ->
                mClick.onClickListner(position, popularFirm.id, popularFirm.averageRating)
            }
        }
    }

    interface OnClick{
        fun onClickListner(position: Int, id: Int, rating: Double)
    }

}