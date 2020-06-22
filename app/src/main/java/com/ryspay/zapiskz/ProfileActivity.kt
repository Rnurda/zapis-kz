package com.ryspay.zapiskz

import android.os.Bundle
import com.ryspay.zapiskz.common.BaseActivity

class ProfileActivity : BaseActivity(2) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setupBottomNavigation()
    }
}
