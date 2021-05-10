package com.github.andtho.spring.springdataexample.controller

import com.github.andtho.spring.springdataexample.entity.UserEntity
import com.github.andtho.spring.springdataexample.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import java.net.URI

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class UserControllerTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var template: TestRestTemplate

    fun String.toUri(): URI = URI.create(this)

    @BeforeEach
    internal fun setUp() {
        userRepository.deleteAll()
    }

    @Test
    fun createUser() {
        val userDto = UserDto(null, "thomas", "test")
        val response1 = template.postForEntity("/api/user", userDto, Nothing::class.java)
        val response2 = template.postForEntity("/api//user", userDto, Nothing::class.java)
        val response3 = template.postForEntity("/api/user", userDto, Nothing::class.java)
        assertTrue(response1.statusCode.is2xxSuccessful)
        assertTrue(response2.statusCode.is2xxSuccessful)
        assertTrue(response3.statusCode.is2xxSuccessful)

        assertEquals(3, userRepository.findAll().size)
    }

    @Test
    fun getUser() {
    }

    @Test
    fun getUserList() {
        userRepository.saveAll(
            arrayListOf(
                UserEntity("", ""), UserEntity("", ""), UserEntity("", "")
            )
        )
        val response = template.exchange(
            RequestEntity<Any>(HttpMethod.GET, "/api//user".toUri()),
            object : ParameterizedTypeReference<List<UserDto>>() {}
        )
        assertTrue(response.statusCode.is2xxSuccessful)
        assertNotNull(response.body)
        assertEquals(3, response.body?.size)
    }

    @Test
    fun getByUserName() {
    }

    @Test
    fun loginUser() {
    }
}