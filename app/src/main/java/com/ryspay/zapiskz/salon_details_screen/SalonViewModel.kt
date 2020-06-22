package com.ryspay.zapiskz.salon_details_screen

import androidx.lifecycle.ViewModel
import com.ryspay.zapiskz.repo.*

class SalonViewModel(val useCaseHandler: UseCaseHandler, val getPosts: GetPosts, val getDetails: GetDetails) : ViewModel() {

    fun getAllPosts(userId: Int, callback: DataSource.LoadPostsCallback) {
        val requestValue = GetPosts.RequestValues(userId)
        useCaseHandler.execute(getPosts, requestValue,
            object : UseCase.UseCaseCallback<GetPosts.ResponseValue> {
            override fun onSuccess(response: GetPosts.ResponseValue) {
                callback.onPostsLoaded(response.posts)
            }

            override fun onError(t: Throwable) {
                callback.onError(t)
            }
        })
    }

    fun getAllPostsDetails(userId: Int, callback: DataSource.LoadPostsDetailsCallback) {
        val requestValue = GetDetails.RequestValues(userId)
        useCaseHandler.execute(getDetails, requestValue,
            object : UseCase.UseCaseCallback<GetDetails.ResponseValue> {
                override fun onSuccess(response: GetDetails.ResponseValue) {
                    callback.onDetailsLoaded(response.detatil)
                }
                override fun onError(t: Throwable) {
                    callback.onError(t)
                }
            })
    }
}