package com.albireo3754.fetcharchive

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
@Inheritance
abstract class Student(
    protected val name: String,
    @ManyToOne(targetEntity = Sensei::class)
    @JoinColumn(name = "sensei_id", nullable = false)
    private val sensei: Sensei,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected var studentId: Long = 0L
) {

    fun name(): String {
        return name
    }

    fun sensei(): Sensei {
        return sensei
    }
}