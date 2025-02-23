package com.example.eventum.database.repository

import com.example.eventum.database.entity.Event
import com.example.eventum.database.entity.Notification
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NotificationDao: CrudRepository<Notification, Long>