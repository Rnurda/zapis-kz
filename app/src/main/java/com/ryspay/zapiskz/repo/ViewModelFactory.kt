package com.ryspay.zapiskz.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ryspay.zapiskz.salon_details_screen.SalonViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == SalonViewModel::class.java) {
            return SalonViewModel(
                Injection.provideUseCaseHandler(),
                Injection.provideGetPosts(),
                Injection.provideGetDetails()
            ) as T
        }
        throw IllegalArgumentException("unknown model class $modelClass")
    }

    companion object {
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory {
            if (INSTANCE == null) {
                INSTANCE =
                    ViewModelFactory()
            }
            return INSTANCE!!
        }
    }
}

object Injection {
    fun providePostDataRepository(): DataRepository {
        return DataRepository.getInstance()
    }

    fun provideViewModelFactory() = ViewModelFactory.getInstance()

    fun provideGetPosts() = GetPosts(providePostDataRepository())

    fun provideGetDetails() = GetDetails(providePostDataRepository())

    fun provideUseCaseHandler() = UseCaseHandler.getInstance()

}
