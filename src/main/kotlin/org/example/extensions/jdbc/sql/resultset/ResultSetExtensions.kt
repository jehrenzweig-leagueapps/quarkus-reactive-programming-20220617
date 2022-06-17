package org.example.extensions.jdbc.sql.resultset

import java.sql.SQLException
import javax.validation.constraints.NotNull
import org.apache.commons.lang3.EnumUtils

@Throws(SQLException::class)
fun java.sql.ResultSet.getNullableString(@NotNull columnIndex: Int): String? {
	val nonNullableValue = this.getString(columnIndex)
	val nullableValue = if (this.wasNull()) null else nonNullableValue
	return nullableValue
}

@Throws(SQLException::class)
fun java.sql.ResultSet.getNullableString(@NotNull columnLabel: String): String? {
	val nonNullableValue = this.getString(columnLabel)
	val nullableValue = if (this.wasNull()) null else nonNullableValue
	return nullableValue
}

@Throws(SQLException::class)
fun java.sql.ResultSet.getNullableBoolean(@NotNull columnIndex: Int): Boolean? {
	val nonNullableValue: Boolean = this.getBoolean(columnIndex)
	val nullableValue = if (this.wasNull()) null else nonNullableValue
	return nullableValue
}

@Throws(SQLException::class)
fun java.sql.ResultSet.getNullableBoolean(@NotNull columnLabel: String): Boolean? {
	val nonNullableValue: Boolean = this.getBoolean(columnLabel)
	val nullableValue = if (this.wasNull()) null else nonNullableValue
	return nullableValue
}

@Throws(SQLException::class)
fun java.sql.ResultSet.getNullableByte(@NotNull columnIndex: Int): Byte? {
	val nonNullableValue: Byte = this.getByte(columnIndex)
	val nullableValue = if (this.wasNull()) null else nonNullableValue
	return nullableValue
}

@Throws(SQLException::class)
fun java.sql.ResultSet.getNullableByte(@NotNull columnLabel: String): Byte? {
	val nonNullableValue: Byte = this.getByte(columnLabel)
	val nullableValue = if (this.wasNull()) null else nonNullableValue
	return nullableValue
}

@Throws(SQLException::class)
fun java.sql.ResultSet.getNullableShort(@NotNull columnIndex: Int): Short? {
	val nonNullableValue: Short = this.getShort(columnIndex)
	val nullableValue = if (this.wasNull()) null else nonNullableValue
	return nullableValue
}

@Throws(SQLException::class)
fun java.sql.ResultSet.getNullableShort(@NotNull columnLabel: String): Short? {
	val nonNullableValue: Short = this.getShort(columnLabel)
	val nullableValue = if (this.wasNull()) null else nonNullableValue
	return nullableValue
}

@Throws(SQLException::class)
fun java.sql.ResultSet.getNullableInt(@NotNull columnIndex: Int): Int? {
	val nonNullableValue: Int = this.getInt(columnIndex)
	val nullableValue = if (this.wasNull()) null else nonNullableValue
	return nullableValue
}

@Throws(SQLException::class)
fun java.sql.ResultSet.getNullableInt(@NotNull columnLabel: String): Int? {
	val nonNullableValue: Int = this.getInt(columnLabel)
	val nullableValue = if (this.wasNull()) null else nonNullableValue
	return nullableValue
}


@Throws(SQLException::class)
fun java.sql.ResultSet.getNullableLong(@NotNull columnIndex: Int): Long? {
	val nonNullableValue: Long = this.getLong(columnIndex)
	val nullableValue = if (this.wasNull()) null else nonNullableValue
	return nullableValue
}

@Throws(SQLException::class)
fun java.sql.ResultSet.getNullableLong(@NotNull columnLabel: String): Long? {
	val nonNullableValue: Long = this.getLong(columnLabel)
	val nullableValue = if (this.wasNull()) null else nonNullableValue
	return nullableValue
}

@Throws(SQLException::class)
fun java.sql.ResultSet.getNullableFloat(@NotNull columnIndex: Int): Float? {
	val nonNullableValue: Float = this.getFloat(columnIndex)
	val nullableValue = if (this.wasNull()) null else nonNullableValue
	return nullableValue
}

@Throws(SQLException::class)
fun java.sql.ResultSet.getNullableFloat(@NotNull columnLabel: String): Float? {
	val nonNullableValue: Float = this.getFloat(columnLabel)
	val nullableValue = if (this.wasNull()) null else nonNullableValue
	return nullableValue
}

@Throws(SQLException::class)
fun java.sql.ResultSet.getNullableDouble(@NotNull columnIndex: Int): Double? {
	val nonNullableValue: Double = getDouble(columnIndex)
	val nullableValue = if (this.wasNull()) null else nonNullableValue
	return nullableValue
}

@Throws(SQLException::class)
fun java.sql.ResultSet.getNullableDouble(@NotNull columnLabel: String): Double? {
	val nonNullableValue: Double = this.getDouble(columnLabel)
	val nullableValue = if (this.wasNull()) null else nonNullableValue
	return nullableValue
}

//	@Throws(SQLException::class)
//	inline fun <reified T : Enum<*>> ResultSet.getEnum(@NotNull columnIndex: Int): T? {
//		val enumValue: String = this.getString(columnIndex)
//		return if (enumValue == null) null else java.lang.Enum.valueOf(T::class.java, enumValue)
//	}

@Throws(SQLException::class)
inline fun <reified T> java.sql.ResultSet.getNullableEnum(@NotNull columnIndex: Int): T? where T : Enum<T> {
	val nonNullableValue: String = this.getString(columnIndex)
	val nullableValue = if (this.wasNull()) null else EnumUtils.getEnumIgnoreCase(T::class.java, nonNullableValue)
	return nullableValue
}

@Throws(SQLException::class)
inline fun <reified T> java.sql.ResultSet.getNullableEnum(@NotNull columnLabel: String): T? where T : Enum<T> {
	val nonNullableValue: String = this.getString(columnLabel)
	val nullableValue = if (this.wasNull()) null else EnumUtils.getEnumIgnoreCase(T::class.java, nonNullableValue)
	return nullableValue
}

//	@Throws(SQLException::class)
//	fun java.sql.ResultSet.<E : Enum<E>?> getEnum(@NotNull columnIndex: Int, enumClz: Class<E>?): E? {
//		val enumValue: String = this.getString(columnIndex)
//		return if (enumValue == null) null else java.lang.Enum.valueOf(enumClz, enumValue)
//	}
//
//	@Throws(SQLException::class)
//	fun java.sql.ResultSet.<E : Enum<E>?> getEnum(@NotNull columnLabel: String, enumClz: Class<E>?): E? {
//		val enumValue: String = this.getString(columnLabel)
//		return if (enumValue == null) null else java.lang.Enum.valueOf(enumClz, enumValue)
//	}
