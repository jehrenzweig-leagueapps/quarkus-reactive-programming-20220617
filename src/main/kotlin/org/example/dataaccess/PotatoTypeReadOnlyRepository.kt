package org.example.dataaccess

import io.agroal.api.AgroalDataSource
import io.quarkus.agroal.DataSource
import io.quarkus.reactive.datasource.ReactiveDataSource
import io.smallrye.mutiny.Uni
import io.vertx.mutiny.mysqlclient.MySQLPool
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import org.apache.commons.lang3.StringUtils
import org.example.abstractions.IPotatoTypeReadRepository
import org.example.mappers.PotatoTypeJdbcRowMapper
import org.example.mappers.PotatoTypeMapper
import org.example.models.PotatoType
import org.jboss.logging.Logger

@ApplicationScoped
class PotatoTypeReadOnlyRepository : IPotatoTypeReadRepository {
	// Fields
	private val logger = Logger.getLogger(PotatoTypeReadOnlyRepository::class.java.name)

	// Dependencies
	private val testDataSource: AgroalDataSource
	private val testReactiveDataSource: MySQLPool

	// Constructors
	@Inject
	constructor(
		@DataSource("NON_REACTIVE_DB") testDataSource: AgroalDataSource,
		@ReactiveDataSource("REACTIVE_DB") testReactiveDataSource: MySQLPool
	) {
		this.testDataSource = testDataSource
		this.testReactiveDataSource = testReactiveDataSource
	}

	// Public methods
	/** Given a name, return a single potato type that exactly (but case-insensitively) matches that name.
	 *
	 * A non-reactive datasource is used to perform the database query.
	 */
	override fun getPotatoTypeByName(name: String): PotatoType? {
		if (StringUtils.isBlank(name)) return null
		this.logger.infov("[PotatoTypeReadOnlyRepository.getPotatoTypeByName()] name = {name}", name)

		// Return a potato type that matches the potato type name.
		var potatoTypeEntity: PotatoType?

		this.testDataSource.connection.use { dbConnection ->
			val sqlQuery = "select * from PotatoTypes where NAME = ? order by NAME limit 2"

			dbConnection.prepareStatement(
				sqlQuery, java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY
			).use { preparedStatement ->
				preparedStatement.setString(1, name)

				preparedStatement.executeQuery().use { resultSet ->
					// Verify there is -EXACTLY- one row in the result set. Otherwise, return null.
					resultSet.last()
					val rowCount: Int = resultSet.row
					if (rowCount != 1) return null

					// Return the single row from the rowset.
					resultSet.first()
					potatoTypeEntity = PotatoTypeJdbcRowMapper().mapRow(resultSet, resultSet.row)
					if (potatoTypeEntity == null) throw Exception("Unable to map SQL results to object.")
				}
			}
		}

		return potatoTypeEntity
	}

	/** Given a name, return a single potato type that exactly (but case-insensitively) matches that name.
	 *
	 * A reactive datasource is used to perform the database query.
	 */
	override suspend fun getPotatoTypeByNameReactive(name: String): Uni<PotatoType?> {
		if (StringUtils.isBlank(name)) return Uni.createFrom().nullItem()

		// Return a potato type that matches the potato type name.
		//var potatoType: PotatoType?

		val uniPotatoType = this.testReactiveDataSource
			.preparedQuery("select * from PotatoTypes where NAME = ? order by NAME")
			.execute(io.vertx.mutiny.sqlclient.Tuple.of(name))
			.onItem().transform(io.vertx.mutiny.sqlclient.RowSet<io.vertx.mutiny.sqlclient.Row>::iterator)
			.onItem().transform { iterator ->
				if (iterator.hasNext()) {
					PotatoTypeMapper().getPotatoType(iterator.next())
				} else {
					null
				}
			}

		//return potatoType
		return uniPotatoType
	}
}