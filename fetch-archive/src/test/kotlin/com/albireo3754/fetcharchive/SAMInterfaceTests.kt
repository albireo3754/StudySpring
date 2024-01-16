package com.albireo3754.fetcharchive

import org.junit.jupiter.api.Test

fun interface Gragorio {
    fun attackGragorio(): Boolean
}

fun interface Hod {
    fun attackHod(): Boolean
}

class A { // implicit label @A
    inner class B { // implicit label @B
        fun Int.foo() { // implicit label @foo
            val a = this@A // A's this
            val b = this@B // B's this

            val c = this // foo()'s receiver, an Int
            val c1 = this@foo // foo()'s receiver, an Int

            val funLit = lambda@ fun String.() {
                val d = this // funLit's receiver, a String
            }

            val funLit2 = { s: String ->
                // foo()'s receiver, since enclosing lambda expression
                // doesn't have any receiver
                val d1 = this
            }
        }
    }
}

class HTML {
    fun body() { print("body") }
    fun body2() { println("body2") }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()  // create the receiver object
    html.init()        // pass the receiver object to the lambda
    return html
}



class SAMInterfaceTests {
    @Test
    fun givenSAMInterface_whenMultipleInheritance_able() {
        fun raid(boss: () -> Boolean) {
            boss()
        }
        val raid2 = Gragorio {
            println(1)
            println(2)
            return@Gragorio true
        }
    }

    @Test
    fun lambda_tests() {
        val funLit = labda2@ fun String.() {
            println("this: " + this)
            println("this@lambda: " + this@labda2)
            fun String.nested() {
                println("this in String.nested(): " + this)
                println("this@nested in String.nested(): " + this@nested)
                println("this@lambda in String.nested(): " + this@labda2)
            }
            "nested".nested()
        }
        "funLit".funLit()
    }

    @Test
    fun lambda_tests2() {
        val funLit = fun String.() {
            println("this: " + this)
            println("this@lambda: " + this)
            fun String.nested() {
                println("this in String.nested(): " + this)
                println("this@nested in String.nested(): " + this@nested)
                println("this@lambda in String.nested(): " + this)
            }
            "nested".nested()
        }
        "funLit".funLit()
    }

    @Test
    fun test_body() {
        html {
            body2()
            println(this@SAMInterfaceTests.lambda_tests())
        }
    }
}