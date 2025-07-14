package org.kotatsu.model.favourite

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys
import kotlin.jvm.internal.Intrinsics

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class FavouritesPackage(
	@SerialName("favourite_categories") val favouriteCategories: List<Category> = listOf(),
	@SerialName("favourites") val favourites: List<Favourite> = listOf(),
	@SerialName("timestamp") val timestamp: Long?,
) {

	fun contentEquals(other: FavouritesPackage): Boolean {
		return Intrinsics.areEqual(favouriteCategories, other.favouriteCategories) &&
			Intrinsics.areEqual(favourites, other.favourites)
	}
}