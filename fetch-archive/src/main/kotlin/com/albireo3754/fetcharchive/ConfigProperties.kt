package com.albireo3754.fetcharchive

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import org.springframework.context.annotation.Configuration


@ConfigurationProperties(prefix = "mail")
class ConfigProperties(
    val hostName: String,
    val port: Int,
    val from: String,
    val city: Millenium
) {
    @ConfigurationProperties(prefix = "mail.student")
    class Student(val name: String)
    class Millenium(val city: String)

//    @ConstructorBinding
//    constructor (city: String): this("hi", 1, "a", Millenium(city)) {
//    }
};