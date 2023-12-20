package com.albireo3754.fetcharchive

import jakarta.persistence.*

@Entity
class Sensei(
    @OneToMany(mappedBy = "sensei", fetch = FetchType.EAGER)
    var students: MutableList<Student>,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val senseiId: Long = 0L) {

    fun addStudent(student: Student) {
        students.add(student)
    }

}