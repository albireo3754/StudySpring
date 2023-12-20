package com.albireo3754.fetcharchive

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FetchArchiveApplication

fun main(args: Array<String>) {
	runApplication<FetchArchiveApplication>(*args) {
		setBannerMode(Banner.Mode.OFF)
	}
}
