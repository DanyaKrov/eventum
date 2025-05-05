package com.example.eventum.util.mapper

import com.example.eventum.data.local.model.entity.ContactEntity
import com.example.eventum.data.remote.model.request.ContactRequest
import com.example.eventum.data.remote.model.response.ContactRemote
import com.example.eventum.screen_contacts.domain.model.Contact
import dagger.internal.DaggerGenerated

@DaggerGenerated
class ContactMapper {
    fun fromEntityToModel(contactEntity: ContactEntity): Contact = Contact(
        id = contactEntity.id,
        name = contactEntity.name,
        userRemoteId = contactEntity.userRemoteId
    )

    fun fromRemoteToModel(contactEntity: ContactRemote): Contact = Contact(
        id = contactEntity.id,
        name = contactEntity.name,
        userRemoteId = contactEntity.hostUserId,
        authorisedStatus = (contactEntity.friendUser != null)
    )

    fun fromModelToEntity(contact: Contact, remoteId: Long): ContactEntity = ContactEntity(
        id = contact.id,
        remoteId = remoteId,
        name = contact.name,
        userRemoteId = contact.userRemoteId
    )

    fun fromRemoteToEntity(contact: ContactRemote): ContactEntity = ContactEntity(
        remoteId = contact.id,
        name = contact.name,
        userRemoteId = contact.hostUserId
    )

    fun fromModelToRequest(contact: Contact): ContactRequest = ContactRequest(
        contact.name
        // need to handle if contact based on existing user
    )
}