package com.albireo3754.fetcharchive

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Haruna constructor(private val cherished: Float, private val sensei: Sensei) : Student("Haruna", sensei) {
}