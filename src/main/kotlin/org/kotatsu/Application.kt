package org.kotatsu

import io.ktor.server.application.*
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry
import org.kotatsu.plugins.*
import org.ktorm.database.Database

lateinit var database: Database
lateinit var appMicrometerRegistry: PrometheusMeterRegistry

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
	configureSerialization()
	configureDatabase()
	configureAuthentication()
	configureLogging()
	configureCompression()
	configureStatusPages()
	configureMetrics()
	configureRouting()
}