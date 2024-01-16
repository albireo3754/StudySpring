package com.albireo3754.fetcharchive

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

class CustomProxyForArenaLogService: ArenaLogService() {
    override fun attackAru() {
        println("CustomProxyForArenaLogService#attackAru")
        super.attackAru()
    }

    override fun killAru() {
        println("CustomProxyForArenaLogService#killAru")
        super.killAru()
    }
}

class CustomProxyLikeSpringAopForArenaLogService(val delegate: ArenaLogService): ArenaLogService() {
    override fun attackAru() {
        println("CustomProxyLikeSpringAopForArenaLogService#attackAru")
        delegate.attackAru()
    }

    override fun killAru() {
        println("CustomProxyLikeSpringAopForArenaLogService#killAru")
        delegate.killAru()
    }
}

@SpringBootTest
class ArenaLogServiceTest @Autowired constructor(val arenaLogService: ArenaLogService) {
    @Test
    fun testAttackAru() {
        arenaLogService.attackAru()
        arenaLogService.killAru()
    }

    @Test
    fun testAttackWithCustomProxy() {
        val arenaLogService = CustomProxyForArenaLogService()
        arenaLogService.attackAru()
        arenaLogService.killAru()
    }

    @Test
    fun testAttackWithCustomProxyLikeSpringAop() {
        val arenaLogService = CustomProxyLikeSpringAopForArenaLogService(arenaLogService)
        arenaLogService.attackAru()
        arenaLogService.killAru()
    }
}