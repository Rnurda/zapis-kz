package com.ryspay.zapiskz.home_screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryspay.zapiskz.R
import com.ryspay.zapiskz.common.BaseActivity
import com.ryspay.zapiskz.repo.DataSource
import com.ryspay.zapiskz.repo.Injection
import com.ryspay.zapiskz.salon_details_screen.SalonDetailsActivity
import com.ryspay.zapiskz.salon_details_screen.SalonViewModel
import com.ryspay.zapiskz.vohome.HomeClass
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call

class HomeActivity : BaseActivity(0), FirmsRecyclerAdapter.OnClick, DataSource.LoadPostsCallback {
    private var mPopularAdapter : FirmsRecyclerAdapter =
        FirmsRecyclerAdapter(this, this)
    private var mRecomendedAdapter : FirmsRecyclerAdapter =
        FirmsRecyclerAdapter(this, this)
    private var mMastersAdapter : FirmsRecyclerAdapter =
        FirmsRecyclerAdapter(this, this)
    private var mRecentlyAdapter : FirmsRecyclerAdapter =
        FirmsRecyclerAdapter(this, this)
    private val TAG = "HomeActivity"
    private lateinit var call: Call<HomeClass>

    private lateinit var viewModel: SalonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    fun init(){
        setupBottomNavigation()

        getViewModel()?.getAllPosts(userId = 1, callback = this)

    }

    private fun getViewModel(): SalonViewModel? {
        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory()).
        get(SalonViewModel::class.java)
        return viewModel
    }

    override fun onClickListner(position: Int, id: Int, rating: Double) {
        Log.d(TAG, "onClickListner: $id.")
        val intent = Intent(this, SalonDetailsActivity::class.java).apply {
            putExtra(ID_MESSAGE, "${id}")
            putExtra(RATING_MESSAGE, "$rating")
        }
        startActivity(intent)
    }

    override fun onPostsLoaded(posts: HomeClass) {
        var mCitiesDialog: CitiesDialogActivity
        popular_list_id.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        recomended_list_id.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        masters_list_id.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recently_list_id.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        main_city_text.setOnClickListener {
            mCitiesDialog = CitiesDialogActivity()
            mCitiesDialog.showCitiesDial(this)
        }


        mPopularAdapter.setItems(posts.data.popularFirms)
        popular_list_id.adapter = mPopularAdapter

        mRecomendedAdapter.setItems(posts.data.recommendedFirms)
        recomended_list_id.adapter = mRecomendedAdapter

        mMastersAdapter.setItems(posts.data.masters)
        masters_list_id.adapter = mMastersAdapter

        mRecentlyAdapter.setItems(posts.data.recentlyAddedFirms)
        recently_list_id.adapter = mRecentlyAdapter
    }

    override fun onError(t: Throwable) {
        Toast.makeText(this, "$t", Toast.LENGTH_SHORT).show()
    }

}
