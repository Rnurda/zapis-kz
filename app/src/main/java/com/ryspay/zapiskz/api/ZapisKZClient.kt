package com.ryspay.zapiskz.api

import com.ryspay.zapiskz.common.BaseActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ZapisKZClient {

    fun getClient(): ZapisKZInterface{
        return Retrofit.Builder()
            .baseUrl(BaseActivity.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<ZapisKZInterface>(ZapisKZInterface::class.java)
    }
}