package com.albireo3754.fetcharchive

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Aru: Student {
    constructor(sensei: Sensei) : super("Aru", sensei) {}

    fun attack(): Long {
        return 3
    }
}