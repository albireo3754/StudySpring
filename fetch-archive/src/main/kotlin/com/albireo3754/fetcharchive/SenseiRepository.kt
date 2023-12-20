package com.albireo3754.fetcharchive

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SenseiRepository: JpaRepository<Sensei, Long> {
    @Query("select s from Sensei s left join fetch s.students")
    fun findAllJPQLFetch(): List<Sensei>;
}