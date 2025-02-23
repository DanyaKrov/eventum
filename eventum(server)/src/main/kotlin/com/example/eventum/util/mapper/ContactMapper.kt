package com.example.eventum.util.mapper

import com.example.eventum.database.entity.Contact
import com.example.eventum.model.response.ContactResponse
import org.springframework.stereotype.Component

@Component
class ContactMapper {
    fun entityToResponse(entity: Contact): ContactResponse {
        return ContactResponse(entity.id,
            entity.name)
    }
}