package org.example.mappers

import org.example.extensions.jdbc.sql.resultset.getNullableShort
import org.example.extensions.jdbc.sql.resultset.getNullableString
import org.example.models.PotatoType

/** Given a [java.sql.ResultSet], return a new [PotatoType] that describes it. */
class PotatoTypeJdbcRowMapper : org.springframework.jdbc.core.RowMapper<PotatoType> {
	/** Given a [java.sql.ResultSet], return a new [PotatoType] that describes it. */
	override fun mapRow(resultSet: java.sql.ResultSet, rowNum: Int): PotatoType? {
		val potatoTypeId = resultSet.getString("ID")!!
		val potatoTypeName = resultSet.getString("NAME")!!
		val countryOfOrigin = resultSet.getNullableString("COUNTRY_OF_ORIGIN")
		val yearOfIntroduction = resultSet.getNullableShort("YEAR_OF_INTRODUCTION")

		val potatoTypeEntity = PotatoType().apply {
			this.id = potatoTypeId
			this.name = potatoTypeName
			this.countryOfOrigin = countryOfOrigin
			this.yearOfIntroduction = yearOfIntroduction
		}

		return potatoTypeEntity
	}
}
