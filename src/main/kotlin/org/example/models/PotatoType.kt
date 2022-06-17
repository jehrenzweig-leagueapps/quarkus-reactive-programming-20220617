package org.example.models

data class PotatoType(
	/** Unique 36-character GUID of the potato type (e.g. "1a0f40de-24e6-40da-9d33-c1d48b6317f7"). */
	var id: String = ""
) {
	/** Name of the potato type (e.g. "King Edward"). */
	var name: String = ""

	/** ISO-3166 alpha-3 code describing the potato type's country of origin (e.g "GBR"). */
	var countryOfOrigin: String? = null

	/** Calendar year in which the potato type was first introduced (e.g. 1902). */
	var yearOfIntroduction: Short? = null
}