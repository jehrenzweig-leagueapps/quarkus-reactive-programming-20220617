package org.example.controllers

import io.smallrye.mutiny.Uni
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.example.abstractions.IPotatoTypeReadRepository
import org.example.models.responses.PotatoTypeResponse
import org.jboss.logging.Logger
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Tag(name = "HelloWorldController")
@Path("")
class HelloWorldController {
	// Fields
	private val logger = Logger.getLogger(HelloWorldController::class.java.name)

	// Dependencies
	private val potatoTypeReadRepository: IPotatoTypeReadRepository

	// Constructors
	@Inject
	constructor(potatoTypeReadRepository: IPotatoTypeReadRepository) {
		this.potatoTypeReadRepository = potatoTypeReadRepository
	}

	// Public methods
	@Operation(summary = "A GET request is submitted & an HTTP 200 response is returned; response body is a \"Hello World!\" string.")
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	fun index(): Uni<String> {
		return Uni.createFrom().item("Hello World!")
	}

	@Operation(
		summary = "Given a potato type's name, return the matching potato type's info; query uses an imperative (i.e. non-reactive) datasource.",
		description = "Perform a case-insensitive search by potato type name (e.g. \"Adirondack Blue\", \"Yukon Gold\"). If an exact match is found, the potato type's info is returned; otherwise, returns null."
	)
	@Parameter(
		name = "name", description = "Unique name of the potato type.", `in` = ParameterIn.PATH,
		examples = [
			ExampleObject(name = "Returns an object", description = "Returns an object describing the potato type.", value = "Adirondack Blue"),
			ExampleObject(name = "Returns a null", description = "Returns a null value.", value = "Wombat")
		]
	)
	@GET
	@Path("/potato-type/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	fun getPotatoTypeByName(@PathParam("name") potatoTypeName: String): Uni<PotatoTypeResponse> {
		this.logger.info("[HelloWorldController.getPotatoByName()] potatoTypeName = $potatoTypeName")

		val potatoTypeEntity = this.potatoTypeReadRepository.getPotatoTypeByName(potatoTypeName)
		val okResponseBody = PotatoTypeResponse(potatoTypeEntity)
		return Uni.createFrom().item(okResponseBody)
	}

	@Operation(
		summary = "Given a potato type's name, return the matching potato type's info; query uses an reactive datasource.",
		description = "Perform a case-insensitive search by potato type name (e.g. \"Adirondack Blue\", \"Yukon Gold\"). If an exact match is found, the potato type's info is returned; otherwise, returns null."
	)
	@Parameter(
		name = "name", description = "Unique name of the potato type.", `in` = ParameterIn.PATH,
		examples = [
			ExampleObject(name = "Returns an object", description = "Returns an object describing the potato type.", value = "Adirondack Blue"),
			ExampleObject(name = "Returns a null", description = "Returns a null value.", value = "Wombat")
		]
	)
	@GET
	@Path("/potato-type/name/{name}/reactive")
	@Produces(MediaType.APPLICATION_JSON)
	suspend fun getPotatoTypeByNameReactive(@PathParam("name") potatoTypeName: String): Uni<PotatoTypeResponse> {
		this.logger.info("[getPotatoByName] potatoTypeName = $potatoTypeName")

		val uniPotatoTypeEntity = this.potatoTypeReadRepository.getPotatoTypeByNameReactive(potatoTypeName)
		return uniPotatoTypeEntity.onItem().transform { potatoTypeEntity -> PotatoTypeResponse(potatoTypeEntity) }
	}
}