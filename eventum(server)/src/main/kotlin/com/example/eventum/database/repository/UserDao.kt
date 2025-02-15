package com.example.eventum.database.repository

import com.example.eventum.database.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDao: CrudRepository<User, Long> {
    fun findById(userId: Long?): User
    fun findByEmail(email: String?): User
}