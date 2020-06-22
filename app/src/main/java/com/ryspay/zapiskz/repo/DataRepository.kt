package com.ryspay.zapiskz.repo

import com.ryspay.zapiskz.api.ZapisKZClient
import com.ryspay.zapiskz.vodetails.BaseDetatil
import com.ryspay.zapiskz.vohome.HomeClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepository private constructor(): DataSource {

    companion object {
        private var INSTANCE: DataRepository? = null
        fun getInstance(): DataRepository {
            if (INSTANCE == null) {
                INSTANCE = DataRepository()
            }
            return INSTANCE!!
        }
    }

    override fun getPosts(userId: Int, callback: DataSource.LoadPostsCallback) {
        ZapisKZClient.getClient().getSalonHome().enqueue(object : Callback<HomeClass> {
            override fun onResponse(call: Call<HomeClass>, response: Response<HomeClass>) {
                if (response.isSuccessful){
                    callback.onPostsLoaded(response.body()!!)
                }
            }
            override fun onFailure(call: Call<HomeClass>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

    override fun getDetails(userId: Int, callback: DataSource.LoadPostsDetailsCallback) {
        ZapisKZClient.getClient().getSalonDetatil(userId).enqueue(object : Callback<BaseDetatil> {
            override fun onResponse(call: Call<BaseDetatil>, response: Response<BaseDetatil>) {
                if (response.isSuccessful){
                    callback.onDetailsLoaded(response.body()!!)
                }
            }

            override fun onFailure(call: Call<BaseDetatil>, t: Throwable) {
                callback.onError(t)
            }
        })
    }
}