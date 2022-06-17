package org.example.models.responses

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.example.models.PotatoType

@Tag(name = "API Responses")
@Schema()
data class PotatoTypeResponse(
	@JsonProperty("potatoType")
	@field:JsonProperty("potatoType")
	@JsonInclude(value = JsonInclude.Include.ALWAYS, content = JsonInclude.Include.ALWAYS)
	var potatoType: PotatoType? = null
)