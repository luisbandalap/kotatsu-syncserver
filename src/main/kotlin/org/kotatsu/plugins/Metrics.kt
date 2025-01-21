package org.kotatsu.plugins

import io.ktor.server.application.*
import io.ktor.server.metrics.micrometer.*
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics
import io.micrometer.core.instrument.binder.jvm.JvmInfoMetrics
import io.micrometer.core.instrument.binder.jvm.JvmHeapPressureMetrics
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics
import io.micrometer.core.instrument.binder.jvm.JvmCompilationMetrics
import io.micrometer.core.instrument.binder.system.DiskSpaceMetrics
import io.micrometer.core.instrument.binder.system.FileDescriptorMetrics
import io.micrometer.core.instrument.binder.system.UptimeMetrics
import io.micrometer.core.instrument.binder.system.ProcessorMetrics
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig
import io.micrometer.prometheusmetrics.PrometheusConfig
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry
import org.kotatsu.appMicrometerRegistry
import java.io.File
import java.time.Duration


fun Application.configureMetrics() {
	appMicrometerRegistry = PrometheusMeterRegistry(PrometheusConfig.DEFAULT)

	install(MicrometerMetrics) {
		registry = appMicrometerRegistry

		distributionStatisticConfig = DistributionStatisticConfig.Builder()
			.percentilesHistogram(true)
			.maximumExpectedValue(Duration.ofSeconds(20).toNanos().toDouble())
			.serviceLevelObjectives(
				Duration.ofMillis(100).toNanos().toDouble(),
				Duration.ofMillis(500).toNanos().toDouble()
			)
			.build()

		meterBinders = listOf(
			JvmMemoryMetrics(),
			JvmGcMetrics(),
			ProcessorMetrics(),
			UptimeMetrics(),
			ClassLoaderMetrics(),
			JvmInfoMetrics(),
			JvmHeapPressureMetrics(),
			JvmThreadMetrics(),
			JvmCompilationMetrics(),
			FileDescriptorMetrics(),
			DiskSpaceMetrics(File("/"))
		)
	}
}
