package com.github.andtho.spring.springdataexample.controller

import com.github.andtho.spring.springdataexample.entity.UserEntity


internal fun UserDto.toUserEntity() =
    UserEntity(id = this.id, username = this.username, password = this.password)

internal fun UserEntity.toUserDto() =
    UserDto(id = this.id, username = this.username, password = this.password)