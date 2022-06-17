package org.example.mappers

import org.example.models.PotatoType

/** Given a [io.vertx.mutiny.sqlclient.Row], return a new [PotatoType] that describes it. */
class PotatoTypeMapper {
	/** Given a [io.vertx.mutiny.sqlclient.Row], return a new [PotatoType] that describes it. */
	fun getPotatoType(row: io.vertx.mutiny.sqlclient.Row): PotatoType? {
		val potatoTypeId = row.getString("ID")!!
		val potatoTypeName = row.getString("NAME")!!
		val countryOfOrigin = row.getString("COUNTRY_OF_ORIGIN")
		val yearOfIntroduction = row.getShort("YEAR_OF_INTRODUCTION")

		val potatoType = PotatoType().apply {
			this.id = potatoTypeId
			this.name = potatoTypeName
			this.countryOfOrigin = countryOfOrigin
			this.yearOfIntroduction = yearOfIntroduction
		}

		return potatoType
	}
}
