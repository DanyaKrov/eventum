package com.example.eventum.util.mapper

import com.example.eventum.data.local.model.entity.ContactEntity
import com.example.eventum.data.remote.model.request.ContactRequest
import com.example.eventum.screen_contacts.domain.model.Contact
import dagger.internal.DaggerGenerated

@DaggerGenerated
class ContactMapper {
    fun fromEntityToModel(contactEntity: ContactEntity): Contact = Contact(
        id = contactEntity.id,
        name = contactEntity.name,
        userRemoteId = contactEntity.userRemoteId
    )

    fun fromModelToEntity(contact: Contact): ContactEntity = ContactEntity(
        id = contact.id,
        remoteId = contact.remoteId,
        name = contact.name,
        userRemoteId = contact.userRemoteId
    )

    fun fromModelToRequest(contact: Contact): ContactRequest = ContactRequest(
        contact.name
        // need to handle if contact based on existing user
    )
}