package com.example.eventum.service.impl

import com.example.eventum.database.entity.Notification
import com.example.eventum.database.repository.EventDao
import com.example.eventum.database.repository.NotificationDao
import com.example.eventum.exception.type.NotFoundException
import com.example.eventum.model.request.NotificationRequest
import com.example.eventum.model.response.NotificationResponse
import com.example.eventum.service.NotificationService
import com.example.eventum.util.mapper.NotificationMapper
import org.springframework.stereotype.Service

@Service
class NotificationServiceImpl(
    private val dao: NotificationDao,
    private val mapper: NotificationMapper,
    private val eventDao: EventDao,
): NotificationService {
    override fun getById(id: Long): Notification = dao.findById(id).orElseThrow {NotFoundException()}

    override fun getAll(): List<NotificationResponse> = dao.findAll().map {
        mapper.entityToResponse(it)
    }

    override fun update(id: Long, newNotification: NotificationRequest): NotificationResponse {
        val oldNotification = getById(id)
        return mapper.entityToResponse(dao.save(mapper.updateNotification(oldNotification, newNotification)))
    }

    override fun delete(id: Long): String {
        try {
            dao.deleteById(id)
            return "deleted success"
        }
        catch (e: Exception) {
            throw NotFoundException()
        }
    }

    override fun create(notification: NotificationRequest, eventId: Long): NotificationResponse {
        val event = eventDao.findById(eventId).orElseThrow {NotFoundException()} // find related event
        val entity = mapper.createNotification(notification, event)
        return mapper.entityToResponse(dao.save(entity))
    }
}