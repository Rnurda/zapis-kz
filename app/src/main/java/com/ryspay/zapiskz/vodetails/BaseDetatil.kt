package com.ryspay.zapiskz.vodetails


data class BaseDetatil(
    val data: Data,
    val message: String
)
data class Category(
    val id: Int,
    val name: String
)

data class Data(
    val categories: List<Category>,
    val firm: Firm,
    val instagram: Any,
    val isOnline: Boolean,
    val location: Location,
    val masters: List<Master>,
    val services: List<Service>
)
data class Firm(
    val address: String,
    val avatarUrl: String,
    val category: String,
    val checkRating: Int,
    val cityName: String,
    val description: String,
    val id: Int,
    val instagramProfile: String,
    val isClientSurnameRequired: Boolean,
    val isExpress: Boolean,
    val isMastersHidden: Boolean,
    val isPromoted: Boolean,
    val name: String,
    val phoneNumbers: List<String>,
    val pictures: List<String>,
    val todayReservationsCount: Any,
    val type: String,
    val urlKey: String,
    val workEndTime: String,
    val workStartTime: String
)

data class Location(
    val centerX: Double,
    val centerY: Double,
    val markerX: Double,
    val markerY: Double,
    val type: String,
    val zoom: Int
)

data class Master(
    val avatarUrl: String,
    val capacity: Any,
    val experience: String,
    val id: Int,
    val isRoom: Boolean,
    val name: String,
    val profession: String,
    val rating: Double,
    val serviceIds: List<Int>,
    val surname: String
)

data class Service(
    val categoryId: Int,
    val description: String,
    val discountedPriceStr: String,
    val duration: Int,
    val express: Int,
    val id: Int,
    val name: String,
    val prepaymentAmount: Any,
    val price: Int,
    val priceMax: Int,
    val priceStr: String
)