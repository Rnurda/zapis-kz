package com.ryspay.zapiskz.home_screen

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.ryspay.zapiskz.R
import com.ryspay.zapiskz.api.ZapisKZClient
import com.ryspay.zapiskz.vohome.City
import com.ryspay.zapiskz.vohome.HomeClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CitiesDialogActivity {

    fun showCitiesDial(parent: HomeActivity){
        val alertDialogBuilder = AlertDialog.Builder(parent,
            R.style.AlertDialogTheme
        );
        val layoutInflater = LayoutInflater.from(parent)
        val cities_dialog_contatiner = parent.findViewById<ConstraintLayout>(R.id.dialog_cities_container)
        val view: View = layoutInflater.inflate(R.layout.layout_cities_dialog, cities_dialog_contatiner, false)
        alertDialogBuilder.setView(view)

        val alertDialog = alertDialogBuilder.create()
        val cencelBtn = view.findViewById(R.id.dialog_cencel_btn) as TextView
        val listOfCities = view.findViewById<ListView>(R.id.dialog_cities_list_id)

        val apiServiceCity = ZapisKZClient.getClient().getSalonHome()

        apiServiceCity.enqueue(object : Callback<HomeClass> {
            override fun onResponse(call: Call<HomeClass>, response: Response<HomeClass>) {
                if(!response.isSuccessful){}
                val data = response.body()
                var cities : List<City> = ArrayList()
                //Log.d(TAG, "onResponse: ${data?.data?.cities!!}")
                cities = data!!.data.cities
                //Log.d(TAG, "Array Of cities: ${cities[0].name}")

                val adapter = ArrayAdapter(parent,
                    R.layout.custom_center_text_layout,
                    R.id.textItem, returnArray(cities))
                listOfCities.adapter = adapter
            }
            override fun onFailure(call: Call<HomeClass>, t: Throwable) {
                Toast.makeText(parent, "$t", Toast.LENGTH_SHORT).show()
                //Log.e(TAG, "onFailure: $t" )
            }
        })
        alertDialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
        cencelBtn.setOnClickListener {
            alertDialog.dismiss()
        }
    }

    fun returnArray(cities: List<City>): Array<String?> {
        val cityItems = arrayOfNulls<String>(cities.count())
        for (i in 0 until cities.count()) {
            cityItems[i] = cities[i].name
            //Log.d(TAG, "aaaaa: ${cityItems[i]}")
        }
        return cityItems
    }
}