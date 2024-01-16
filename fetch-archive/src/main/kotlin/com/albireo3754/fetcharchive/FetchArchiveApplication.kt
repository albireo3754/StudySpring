package com.albireo3754.fetcharchive

import org.springframework.beans.factory.getBean
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cglib.core.ReflectUtils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.UncheckedIOException


object SpringCglibUtils {
	fun initGeneratedClassHandler(targetPath: String?) {
		val homeDir = System.getProperty("user.home")
		val filePath = "$homeDir/${targetPath}/"
		val dir = File(filePath)
		dir.mkdirs()
		ReflectUtils.setGeneratedClassHandler { className: String, classContent: ByteArray? ->
			try {
				if (classContent != null) {
					classContent.forEach { print(it.toInt().toChar()) }
				}
				FileOutputStream(File(dir, "$className.class")).use { out -> out.write(classContent) }
			} catch (e: IOException) {
				throw UncheckedIOException("Error while storing $className", e)
			}
		}
	}
}
@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties::class, ConfigProperties.Student::class)
class FetchArchiveApplication(val configure: ConfigProperties, val studentConfigure: ConfigProperties.Student): ApplicationRunner {
	override fun run(args: ApplicationArguments?) {
		println("myPort:${configure.port}")
		println("myPort:${studentConfigure.name}")

		println("hi")
	}
}

fun main(args: Array<String>) {
	SpringCglibUtils.initGeneratedClassHandler("cglib")
	val application = runApplication<FetchArchiveApplication>(*args) {
		setBannerMode(Banner.Mode.OFF)
	}

	for (i in 1..<20) {
		val config = application.getBean<ConfigProperties>();
	}
}
