package com.ryspay.zapiskz.salon_details_screen

import com.ryspay.zapiskz.api.ZapisKZClient
import com.ryspay.zapiskz.vodetails.BaseDetatil
import retrofit2.Call

fun detatilScreenDatas(activity: SalonDetailsActivity, firm_id: Int): Call<BaseDetatil> {
    val call: Call<BaseDetatil>

    call = ZapisKZClient.getClient().getSalonDetatil(firm_id)
    return call
}
