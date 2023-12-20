package com.albireo3754.fetcharchive

import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SenseiServiceTest @Autowired constructor(
     val senseiRepository: SenseiRepository) {

//    @MockK
//    lateinit var senseiRepository: SenseiRepository

    @Test
    fun getAru() {
        val senseiService = SenseiService(senseiRepository)
    }

    @Test
    fun getTest() {

        val list = listOf(1, 2, 3)
    }
}