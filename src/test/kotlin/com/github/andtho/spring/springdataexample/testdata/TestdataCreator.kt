package com.github.andtho.spring.springdataexample.testdata

import com.github.andtho.spring.springdataexample.entity.UserEntity
import com.github.andtho.spring.springdataexample.repository.UserRepository
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@Disabled
@SpringBootTest
@TestPropertySource(properties = ["spring.datasource.url=jdbc:h2:file:~/data/demo-app"])
class TestdataCreator {
    @Autowired
    lateinit var userRepository: UserRepository
    @Test
    internal fun createUsers() {
        userRepository.save(UserEntity("thomas", "password"))
        userRepository.save(UserEntity("espen", "password"))
        userRepository.save(UserEntity("marius", "password"))
    }
}