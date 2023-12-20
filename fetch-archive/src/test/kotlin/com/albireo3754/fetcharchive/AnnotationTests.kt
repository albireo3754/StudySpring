package com.albireo3754.fetcharchive

import org.junit.jupiter.api.Test
import org.springframework.core.annotation.AnnotatedElementUtils

class AnnotationTests {
    @Test
    fun te() {
        Te().te()
    }

    @Test
    fun myClass() {
        MyClass().javaClass.annotations.forEach {
            println("annotation: $it")
        }

        println("-------")
        AnnotatedElementUtils.findMergedAnnotation(MyClass().javaClass, One::class.java)?.let { one ->
            println(one.value)
        }
        println("======")
        MyClass().javaClass.getAnnotation(Two::class.java)?.let { one ->
            println(one.value2)
        }
    }
}