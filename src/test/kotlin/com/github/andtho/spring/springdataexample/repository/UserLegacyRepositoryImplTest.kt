package com.github.andtho.spring.springdataexample.repository

import com.github.andtho.spring.springdataexample.entity.UserEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class UserLegacyRepositoryImplTest {

    @Autowired
    @Qualifier("userLegacyRepositoryImpl")
    lateinit var userLegacyRepository: UserLegacyRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @BeforeEach
    internal fun setUp() {
        userRepository.deleteAll()
    }

    @Test
    fun createAndGetUserById_legacy_repository() {
        assertNotNull(userLegacyRepository)
        val newUser = userLegacyRepository.createUser(UserEntity("thomas", "password"))
        userLegacyRepository.createUser(UserEntity("sara", "password"))

        val user = userLegacyRepository.getUserById(newUser.id!!)
        assertEquals("thomas", user?.username)

        assertEquals("sara", userLegacyRepository.getUserByName("sara")?.username)
    }

    @Test
    fun createAndGetUserById_spring_data_repository() {
        assertNotNull(userRepository)
        val newUser = userRepository.save(UserEntity("thomas", "password"))
        userRepository.save(UserEntity("sara", "password"))

        assertEquals(2, userRepository.findAll().size)

        val user = userRepository.findById(newUser.id!!)
        assertTrue(user.isPresent)
        assertEquals("thomas", user.get().username)

        assertEquals("sara", userRepository.findByUsername("sara")?.username)
    }
}