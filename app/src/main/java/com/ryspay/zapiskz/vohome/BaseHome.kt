package com.ryspay.zapiskz.vohome
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


class HomeClass(
    val data: Data,
    val message: String
)

data class Data(
    val cities: List<City>,
    val isCategoriesHidden: Boolean,
    val masters: List<Master>,
    val popularFirms: List<PopularFirm>,
    val recentlyAddedFirms: List<RecentlyAddedFirm>,
    val recommendedFirms: List<RecommendedFirm>,
    val updates: List<Any>,
    val wrongServicePricePhones: List<String>,
    val baseFirm: List<BaseFirm>
)

@Parcelize
data class City(
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val urlName: String
) : Parcelable


abstract class BaseFirm{
    abstract val address: String
    abstract val avatarUrl: String
    abstract val averageRating: Double
    abstract val checkRating: Int
    abstract val id: Int
    abstract val isFavorite: Boolean
    abstract val isIndividualMaster: Boolean
    abstract val isPromoted: Boolean
    abstract val name: String
    abstract val pictureUrl: String
    abstract val prepaymentCashbackAmount: String
    abstract val todayReservationsCount: String
    abstract val type: String
    abstract val urlKey: String
    abstract val workSchedule: String
}

data class Master(
    override var address: String,
    override var avatarUrl: String,
    override var averageRating: Double,
    override var checkRating: Int,
    override var id: Int,
    override var isFavorite: Boolean,
    override var isIndividualMaster: Boolean,
    override var isPromoted: Boolean,
    override var name: String,
    override var pictureUrl: String,
    override var prepaymentCashbackAmount: String,
    override var todayReservationsCount: String,
    override var type: String,
    override var urlKey: String,
    override var workSchedule: String
): BaseFirm()

data class PopularFirm(
    override var address: String,
    override var avatarUrl: String,
    override var averageRating: Double,
    override var checkRating: Int,
    override var id: Int,
    override var isFavorite: Boolean,
    override var isIndividualMaster: Boolean,
    override var isPromoted: Boolean,
    override var name: String,
    override var pictureUrl: String,
    override var prepaymentCashbackAmount: String,
    override var todayReservationsCount: String,
    override var type: String,
    override var urlKey: String,
    override var workSchedule: String
): BaseFirm()


data class RecentlyAddedFirm(
   override var address: String,
   override var avatarUrl: String,
   override var averageRating: Double,
   override var checkRating: Int,
   override var id: Int,
   override var isFavorite: Boolean,
   override var isIndividualMaster: Boolean,
   override var isPromoted: Boolean,
   override var name: String,
   override var pictureUrl: String,
   override var prepaymentCashbackAmount: String,
   override var todayReservationsCount: String,
   override var type: String,
   override var urlKey: String,
   override var workSchedule: String
): BaseFirm()

data class RecommendedFirm(
    override var address: String,
    override var avatarUrl: String,
    override var averageRating: Double,
    override var checkRating: Int,
    override var id: Int,
    override var isFavorite: Boolean,
    override var isIndividualMaster: Boolean,
    override var isPromoted: Boolean,
    override var name: String,
    override var pictureUrl: String,
    override var prepaymentCashbackAmount: String,
    override var todayReservationsCount: String,
    override var type: String,
    override var urlKey: String,
    override var workSchedule: String
): BaseFirm()