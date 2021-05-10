package com.github.andtho.spring.springdataexample.controller

import com.github.andtho.spring.springdataexample.repository.UserRepository
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/user")
class UserController constructor(val userRepository: UserRepository) {

    @PostMapping
    fun createUser(@RequestBody user: UserDto) =
        userRepository.save(user.toUserEntity())

    @GetMapping("{id}")
    fun getUser(@RequestParam("id") id: Long): UserDto =
        userRepository.findById(id)
            .map { it.toUserDto() }
            .orElseThrow { RuntimeException("No user with id=$id") }

    @GetMapping
    fun getUsers(): List<UserDto> =
        userRepository.findAll().map { it.toUserDto() }

    @GetMapping("username/{username}")
    fun getByUserName(@RequestParam("username") username: String): UserDto? =
        userRepository.findByUsername(username)?.toUserDto()

    @PostMapping("login")
    fun loginUser(dto: LoginInfoDto) : UserDto? =
        userRepository.findByUsernameAndPassword(dto.username, dto.password)?.toUserDto()

}
