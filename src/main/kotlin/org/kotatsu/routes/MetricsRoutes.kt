package org.kotatsu.routes

import io.ktor.server.response.respond
import io.ktor.server.routing.*
import org.kotatsu.appMicrometerRegistry


fun Route.metricsRoutes() {
	get("/metrics") {
		call.respond(appMicrometerRegistry.scrape())
	}
}