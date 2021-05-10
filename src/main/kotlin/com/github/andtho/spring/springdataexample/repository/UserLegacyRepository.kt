package com.github.andtho.spring.springdataexample.repository

import com.github.andtho.spring.springdataexample.entity.UserEntity

interface UserLegacyRepository {
    fun getUserById(id : Long) : UserEntity?
    fun createUser(user : UserEntity) : UserEntity
    fun getUserByName(name: String): UserEntity?
}