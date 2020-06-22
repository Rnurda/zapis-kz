package com.ryspay.zapiskz.repo

import com.ryspay.zapiskz.vodetails.BaseDetatil
import com.ryspay.zapiskz.vohome.HomeClass

interface DataSource {
    interface LoadPostsCallback {
        fun onPostsLoaded(posts: HomeClass)
        fun onError(t: Throwable)
    }

    interface LoadPostsDetailsCallback {
        fun onDetailsLoaded(details: BaseDetatil)
        fun onError(t: Throwable)
    }

    fun getPosts(userId: Int, callback: LoadPostsCallback)
    fun getDetails(userId: Int, callback: LoadPostsDetailsCallback)
}