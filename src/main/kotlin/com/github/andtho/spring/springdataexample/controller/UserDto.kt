package com.github.andtho.spring.springdataexample.controller

data class UserDto(
    val id: Long?,
    val username: String,
    val password: String
) {
    constructor() : this(null, "", "")
    constructor(username: String, password: String) : this(null, username, password)
}
