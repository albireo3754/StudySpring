package com.albireo3754.fetcharchive

import jakarta.persistence.Id
import org.springframework.stereotype.Service

@Service
class SenseiService(val repository: SenseiRepository, val configure: ConfigProperties) {

    fun makeSensei() {
        val sensei = Sensei(ArrayList<Student>())
        repository.save(sensei)
    }

    fun gacha(sensei: Sensei) {
        // 상점에서 가차를 뽑는걸 시뮬레이션한다
        // 일단 아루가 뽑혓다고 가정하겟음
        val aru = Aru(sensei)
        sensei.addStudent(aru)
        repository.save(sensei)
    }

    fun gacha10(sensei: Sensei) {
        val haruna = Haruna(0.5f, sensei)
        sensei.addStudent(haruna)
        repository.save(sensei)
    }

    fun load(senseiId: Long) {
        repository.findById(senseiId)
    }

    fun getPort(): Int {
        return configure.port
    }
}