package com.ryspay.zapiskz.repository

enum class Status {
    RUNNIN,
    SUCCESS,
    FAILED
}

class  NetworkState(val status: Status, val msg: String){
    companion object{
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROE: NetworkState

        init {
            LOADED = NetworkState(Status.SUCCESS, "success")
            LOADING = NetworkState(Status.RUNNIN, "running")
            ERROE = NetworkState(Status.FAILED, "something went wrong!")
        }
    }
}