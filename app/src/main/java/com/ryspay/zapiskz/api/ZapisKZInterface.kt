package com.ryspay.zapiskz.api

import com.ryspay.zapiskz.vohome.HomeClass
import com.ryspay.zapiskz.vodetails.BaseDetatil
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ZapisKZInterface{
    @GET("v1/firms/{firmId}")
    fun getSalonDetatil(@Path("firmId") id: Int): Call<BaseDetatil>

    @GET("v1/screen/home")
    fun getSalonHome(): Call<HomeClass>
}