package com.albireo3754.fetcharchive

@Te2()
class Te {
    fun te() {
        val te1 = Te::class.java.getAnnotation(Te1::class.java)
        println(te1.tes)
    }
}
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Te1(
    val tes: String = "Te1"
)

@Retention(AnnotationRetention.RUNTIME)
@Te1(tes = "Te2")
@Target(AnnotationTarget.CLASS)
annotation class Te2(val value: String = "Te2")

// Define a meta-annotation
@Retention(AnnotationRetention.RUNTIME)
internal annotation class Two(val value2: String = "Annotation Two")


// Define an annotation that is meta-annotated with @Two
@Retention(AnnotationRetention.RUNTIME)
@Two
internal annotation class One(
    val value: String = "Annotation One" // This implicitly overrides @Two's 'value'
)


// Applying the annotations
//@Two
@One
class MyClass { // Class content
}