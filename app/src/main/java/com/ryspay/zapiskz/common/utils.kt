package com.ryspay.zapiskz.common

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast


fun Context.showToast(text: String?, duration: Int = Toast.LENGTH_SHORT){
    text?.let{ Toast.makeText(this, it, duration).show()}
}

/*private fun initCollapsingToolbar(appBarLayout: AppBarLayout,toolbar: CollapsingToolbarLayout,title: String) {
    appBarLayout.setExpanded(true)
    appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
        var isShow = false
        var scrollRange = -1
        override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
            if (scrollRange == -1) {
                scrollRange = appBarLayout.totalScrollRange
            }
            if (scrollRange + verticalOffset == 0) {
                toolbar.title = "tool"
                isShow = true
            } else if (isShow) {
                toolbar.title = " "
                isShow = false
            }
        }
    })
}*/

fun newInstagramProfileIntent(pm: PackageManager, url: String): Intent? {
    var url = url
    val intent = Intent(Intent.ACTION_VIEW)
    try {
        if (pm.getPackageInfo("com.instagram.android", 0) != null) {
            if (url.endsWith("/")) {
                url = url.substring(0, url.length - 1)
            }
            intent.data = Uri.parse("http://instagram.com/_u/$url")
            intent.setPackage("com.instagram.android")
            return intent
        }
    } catch (ignored: Exception) {
    }
    intent.data = Uri.parse(url)
    return intent
}








