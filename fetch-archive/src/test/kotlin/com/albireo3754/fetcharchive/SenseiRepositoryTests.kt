package com.albireo3754.fetcharchive

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.lang.annotation.Inherited
import java.lang.annotation.RetentionPolicy

//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class SenseiRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val senseiRepository: SenseiRepository,
    val studentRepository: StudentRepository) {


//    @MockK
//    lateinit var senseiRepository: SenseiRepository
    @BeforeEach
    fun before() {
        println("before")
    }

    @Test
    fun getAru() {
        "hi"
        var sensei = Sensei(ArrayList())
        var sensei2 = Sensei(ArrayList())
        senseiRepository.save(sensei2)
        senseiRepository.saveAndFlush(sensei)
        println(sensei.senseiId);

        var aru = Aru(sensei)
        var aru2 = Aru(sensei2)
        var haruna = Haruna(1.0f, sensei)
        println("===============")
        studentRepository.save(aru2)
        studentRepository.saveAndFlush(aru);
        studentRepository.saveAndFlush(haruna);
        println("----------------")
//        sensei.addStudent(aru)
        println(sensei.senseiId)
        println(sensei.students)
//        senseiRepository.saveAndFlush(sensei)
//        entityManager.flush()
        entityManager.clear()
        println("----------------")

        senseiRepository.findAllJPQLFetch().forEach {
            println("----------------=============")
//            val aru = it.students.first()
            for (student in it.students) {
                println(student.name())
            }
//            Assertions.assertEquals(aru.name(), "Aru")
//            Assertions.assertEquals(aru.sensei(), it)
            println(it.senseiId)
        }
//        senseiRepository.findAll().forEach {
//            println("----------------=============")
////            val aru = it.students.first()
//            for (student in it.students) {
//                println(student.name())
//            }
////            Assertions.assertEquals(aru.name(), "Aru")
////            Assertions.assertEquals(aru.sensei(), it)
//            println(it.senseiId)
//        }



    }
}