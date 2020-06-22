package com.ryspay.zapiskz.repo

import com.ryspay.zapiskz.vodetails.BaseDetatil
import com.ryspay.zapiskz.vohome.HomeClass

class GetPosts(private val mDataSource: DataSource) :
    UseCase<GetPosts.RequestValues, GetPosts.ResponseValue>() {

    override fun executeUseCase(requestValues: GetPosts.RequestValues?) {
        mDataSource.getPosts(requestValues?.userId ?: -1,
            object : DataSource.LoadPostsCallback {
            override fun onPostsLoaded(posts: HomeClass) {
                val responseValue = ResponseValue(posts)
                useCaseCallback?.onSuccess(responseValue)
            }
            override fun onError(t: Throwable) {
                // Never use generic exceptions. Create proper exceptions. Since
                // our use case is different we will go with generic throwable
                useCaseCallback?.onError(Throwable("Data not found"))
            }
        })
    }
    class RequestValues(val userId: Int) : UseCase.RequestValues
    class ResponseValue(val posts: HomeClass) : UseCase.ResponseValue
    class ResponseValuess(val detatil: BaseDetatil) : UseCase.ResponseValue
}