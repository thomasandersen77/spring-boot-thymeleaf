package com.github.andtho.spring.springdataexample.controller

import com.github.andtho.spring.springdataexample.repository.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import org.thymeleaf.spring5.view.ThymeleafView
import javax.validation.Valid

@Controller
class ViewUserController constructor(val userRepository: UserRepository) {

    @GetMapping("users")
    fun showUserList(model: Model): String? {
        with(model) {
            addAttribute("users", userRepository.findAll())
            return "list-users"
        }
    }

    @GetMapping("add-user")
    fun showUserAdd(): ModelAndView {
        return ModelAndView("add-user").apply {
            addObject("user", UserDto())
        }
    }

    @PostMapping("/user")
    fun createUser(@Valid user : UserDto, result : BindingResult, model : Model) : String {
        with(model) {
            addAttribute("user", user)
            userRepository.save(user.toUserEntity())
            return "redirect:/list-users"
        }
    }
}