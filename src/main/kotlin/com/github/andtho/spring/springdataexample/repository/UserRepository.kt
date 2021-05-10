package com.github.andtho.spring.springdataexample.repository

import com.github.andtho.spring.springdataexample.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long>, UserLegacyRepository {
    fun findByUsername(username : String) : UserEntity?
    fun findByUsernameAndPassword(username: String, password : String) : UserEntity?
}