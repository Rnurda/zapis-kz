package com.ryspay.zapiskz

import android.os.Bundle
import com.ryspay.zapiskz.common.BaseActivity

class SearchActivity : BaseActivity(1) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setupBottomNavigation()
    }
}
