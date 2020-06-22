package com.ryspay.zapiskz.salon_details_screen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.models.SlideModel
import com.ryspay.zapiskz.*
import com.ryspay.zapiskz.common.BaseActivity
import com.ryspay.zapiskz.common.newInstagramProfileIntent
import com.ryspay.zapiskz.home_screen.HomeActivity
import com.ryspay.zapiskz.repo.DataSource
import com.ryspay.zapiskz.repo.Injection
import com.ryspay.zapiskz.vodetails.BaseDetatil
import kotlinx.android.synthetic.main.activity_salon_details.*
import kotlinx.android.synthetic.main.main_detail_view.*
import kotlinx.android.synthetic.main.main_detail_view.view.*

class SalonDetailsActivity : BaseActivity(10), DataSource.LoadPostsDetailsCallback,  MasterRecyclerAdapter.OnClick, ServiceRecyclerAdapter.OnClickService {
    private var firm_id: Int = 0
    private lateinit var rating: String

    private lateinit var viewModel: SalonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salon_details)
        init()
    }

    fun init(){
        setSupportActionBar(detailsToolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);

        firm_id = intent.getStringExtra(ID_MESSAGE).toInt()
        rating = intent.getStringExtra(RATING_MESSAGE)
        detailsToolbar.setNavigationOnClickListener {
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finish()
        }

        getViewModel()?.getAllPostsDetails(userId = firm_id, callback = this)
    }

    private fun getViewModel(): SalonViewModel? {
        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory()).
        get(SalonViewModel::class.java)
        return viewModel
    }


    override fun onClickListner(position: Int, id: Int, tag: String) {
        when (tag) {
            MASTER_TAG -> { }
            SERVICE_TAG -> { }
        }
    }

    override fun onDetailsLoaded(details: BaseDetatil) {
        masters_detatils_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        services_detatils_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val firm = details.data.firm
        val pictures = firm.pictures[0]
        //response.body()?.data?.masters!!

        val mMasterRecyclerAdapter = MasterRecyclerAdapter(this, this)
        val mServiceRecyclerAdapter = ServiceRecyclerAdapter(this, this)

        mMasterRecyclerAdapter.setItems(details.data.masters)
        masters_detatils_list.adapter = mMasterRecyclerAdapter

        mServiceRecyclerAdapter.setItems(details.data.services)
        services_detatils_list.adapter = mServiceRecyclerAdapter

        val imageList = ArrayList<SlideModel>()

        for (str in firm.pictures)
            imageList.add(SlideModel(str))
        detatil_main_image.setImageList(imageList)

        detail_toolbar.title = firm.name
        main_detatil.main_salon_name_tv.text = firm.name
        main_detatil.main_salon_type_tv.text = firm.type
        main_detatil.detatil_location_tv.text = firm.cityName
        detatils_rating_tv.text = rating

        detatil_insta_icon.setOnClickListener {
            firm.instagramProfile.let { it1 ->
                newInstagramProfileIntent(
                    packageManager,
                    it1
                )
            }
        }
        detatil_phone_icon.setOnClickListener{
            val number = firm.phoneNumbers[0]
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
            startActivity(callIntent)
        }
        main_detatil.details_booking_count_tv.text = firm.checkRating.toString()
        main_detatil.detatil_street_name_tv.text = firm.address.split("<").get(0)
    }

    override fun onError(t: Throwable) {
        Toast.makeText(this, "$t", Toast.LENGTH_SHORT).show()
    }
}
