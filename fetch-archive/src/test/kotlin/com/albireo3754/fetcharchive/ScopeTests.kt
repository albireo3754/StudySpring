package com.albireo3754.fetcharchive

import org.hibernate.annotations.Proxy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.beans.factory.getBean
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cglib.proxy.Enhancer
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.context.support.AbstractApplicationContext

import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.SessionScope


@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class Saki(var name: String = "Saki")

@Component
class Miyako(var name: String = "Miyako")

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
class Miyu(var name: String = "Miyu")

@Component
class SRT(val saki: Saki)

@Component
class Rabbit(val miyu: Miyu)


@SpringBootTest
class ScopeTests @Autowired constructor(val applicationContext: ApplicationContext) {
    private val NAME: String? = "John Smith"
    private val NAME_OTHER = "Anna Jones"

    @Test
    fun givenPrototypeScope_whenSetNames_thenDifferentNames() {
        var saki1 = applicationContext.getBean<Saki>()
        var saki2 = applicationContext.getBean<Saki>()
        var saki3 = applicationContext.getBean("saki") as Saki
        var saki4 = applicationContext.getBean("saki")
        saki2.name = "수영복 사키"

        assertNotEquals(saki1.name, saki2.name)
        assertNotEquals(saki2.name, saki3.name)
        assertEquals(saki1.name, saki3.name)
        assertNotEquals(saki1, saki3)
    }

    @Test
    fun givenSingletoneScope_whenSetNames_thenSameNames() {
        var miyako1 = applicationContext.getBean<Miyako>()
        var miyako2 = applicationContext.getBean<Miyako>()
        var miyako3 = applicationContext.getBean("miyako")
        miyako2.name = "수영복 미야코"

        assertEquals(miyako1.name, miyako2.name)
        assertEquals(miyako3, miyako1)
    }

    @Test
    fun givenPrototypeScopeInSingletonScope_whenSetNames_thenSameNames() {
        var srt = applicationContext.getBean<SRT>()
        var srt2 = applicationContext.getBean<SRT>()
        var saki = applicationContext.getBean<Saki>()

        srt.saki.name = "사키"
        srt2.saki.name = "수영복 사키"

        assertEquals(srt.saki.name, "수영복 사키")
        assertEquals(srt2.saki.name, "수영복 사키")
        assertEquals(srt.saki, srt2.saki)
        assertNotEquals(srt.saki.name, saki.name)
    }

    @Test
    fun givenPrototypeScopeWithProxyModeInSingletonScope_whenSetNames_thenOtherNames() {
        var rabbit1 = applicationContext.getBean<Rabbit>()
        var rabbit2 = applicationContext.getBean<Rabbit>()

        rabbit1.miyu.name = "미유"
        println("${rabbit1.miyu.name}")
        rabbit2.miyu.name = "수영복 미유"
        // prototype scope bean is stateless bean call method signature makes call new bean
        assertEquals(rabbit1.miyu.name, "Miyu")
        assertEquals(rabbit2.miyu.name, "Miyu")

    }
}