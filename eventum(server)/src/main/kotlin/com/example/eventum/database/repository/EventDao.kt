package com.example.eventum.database.repository

import com.example.eventum.database.entity.Event
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EventDao: CrudRepository<Event, Long>