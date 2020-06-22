package com.ryspay.zapiskz.repo

import com.ryspay.zapiskz.vodetails.BaseDetatil

class GetDetails(private val mDataSource: DataSource) :

    UseCase<GetDetails.RequestValues, GetDetails.ResponseValue>() {
    override fun executeUseCase(requestValues: RequestValues?) {
        mDataSource.getDetails(requestValues?.userId ?: -1,
            object : DataSource.LoadPostsDetailsCallback {
            override fun onDetailsLoaded(details: BaseDetatil) {
                val responseValue = ResponseValue(details)
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
    class ResponseValue(val detatil: BaseDetatil) : UseCase.ResponseValue
}