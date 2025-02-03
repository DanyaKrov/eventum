package com.example.eventum.controller

import com.example.eventum.database.entity.User
import com.example.eventum.model.request.UserRequest
import com.example.eventum.model.response.UserResponse
import com.example.eventum.service.UserService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    @GetMapping
    fun getAll(): List<UserResponse> {return userService.getAll()}

    @GetMapping("/getUserById")
    fun getUserById(@RequestParam userId: Long): User = userService.getById(userId)

    @GetMapping("/getUserByEmail")
    fun getUserByEmail(@RequestParam userEmail: String): User = userService.getByEmail(userEmail)

    @GetMapping("/{userId}/contacts")
    fun getContacts(@PathVariable userId: Long): List<UserResponse> {return userService.getContacts(userId)}

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody user: UserRequest) = userService.update(id, user)

    @PostMapping
    fun create(@RequestBody user: UserRequest): UserResponse = userService.create(user)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long, response: HttpServletResponse): String = userService.delete(id)


}