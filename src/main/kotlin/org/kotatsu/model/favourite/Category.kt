package org.kotatsu.model.favourite

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class Category(
	@SerialName("category_id") val id: Int,
	@SerialName("created_at") var createdAt: Long,
	@SerialName("sort_key") var sortKey: Int,
	@SerialName("track") var track: Int,
	@SerialName("title") var title: String,
	@SerialName("order") var order: String,
	@SerialName("deleted_at") var deletedAt: Long,
	@SerialName("show_in_lib") var showInLib: Int,
)