package com.albireo3754.fetcharchive

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest

@ConfigurationProperties(prefix = "student")
class StudentConfiguration(
    val name: String,
    val age: Int
    ) {};

@SpringBootTest
@EnableConfigurationProperties(StudentConfiguration::class, ConfigProperties::class)
class ConfigurationPropertiesTests @Autowired constructor(val studentConfiguration: StudentConfiguration, val configurationProperties: ConfigProperties) {
    @Test
    fun configurationTests() {
        var aru = studentConfiguration;

        assertEquals(aru.name, "aru")
    }

    @Test
    fun configureInMainSrcTests() {
        assertEquals(configurationProperties.port, 18885)
        assertEquals(configurationProperties.hostName, "host")
        assertEquals(configurationProperties.city.city, "Millenium")
    }
}