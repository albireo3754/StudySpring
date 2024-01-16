package com.albireo3754.fetcharchive

import org.springframework.stereotype.Service

@Service
open class ArenaLogService {
    @LoggingStopWatch
    open fun attackAru() {
        println("Attack Aru")
        killAru()
    }

    @LoggingStopWatch
    open fun killAru() {
        println("Kill Aru")
    }
}

@Service
class ArenaLogService2() {
    fun attackAru() {
        println("Attack Aru")
    }
}