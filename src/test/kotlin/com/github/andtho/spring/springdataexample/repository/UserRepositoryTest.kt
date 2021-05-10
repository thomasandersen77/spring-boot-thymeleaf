package com.github.andtho.spring.springdataexample.repository

import com.github.andtho.spring.springdataexample.entity.ContactInfoEntity
import com.github.andtho.spring.springdataexample.entity.UserEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    lateinit var userRepository: UserRepository

    @BeforeEach
    internal fun setUp() {
        userRepository.deleteAll()
    }

    @Test
    internal fun name() {
        userRepository.save(
                UserEntity(
                    username = "user", password = "pass", contactInfoEntity =
                    ContactInfoEntity(email = "thomas@knowit.no")
                )
            )
        userRepository.save(
            UserEntity(
                username = "user2", password = "pass", contactInfoEntity =
                ContactInfoEntity(email = "user2@knowit.no", mobile = "97407116")
            )
        )

        assertEquals(2, userRepository.count())

        val user = userRepository.findByUsernameAndPassword("user", "pass")
        assertNotNull(user)
        assertNotNull(user?.contactInfoEntity)
        assertEquals("thomas@knowit.no", user?.contactInfoEntity?.email)

        val user2 = userRepository.findByUsernameAndPassword("user2", "pass")
        assertNotNull(user2)

    }
}