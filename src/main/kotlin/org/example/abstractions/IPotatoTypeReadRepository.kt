package org.example.abstractions

import io.smallrye.mutiny.Uni
import org.example.models.PotatoType

interface IPotatoTypeReadRepository {
	fun getPotatoTypeByName(name: String): PotatoType?
	suspend fun getPotatoTypeByNameReactive(name: String): Uni<PotatoType?>
}