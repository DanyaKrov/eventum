package com.example.eventum.util.mapper

import com.example.eventum.data.local.model.entity.ContactEntity
import com.example.eventum.data.remote.model.request.ContactRequest
import com.example.eventum.data.remote.model.response.ContactRemote
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_contacts.domain.model.ContactRequestModel
import dagger.internal.DaggerGenerated

@DaggerGenerated
class ContactMapper {
    fun fromEntityToModel(contactEntity: ContactEntity): Contact = Contact(
        remoteId = contactEntity.remoteId,
        name = contactEntity.name,
        userRemoteId = contactEntity.userRemoteId,
        userLogin = contactEntity.userLogin
    )

    fun fromRemoteToModel(contactRemote: ContactRemote): Contact = Contact(
        remoteId= contactRemote.id,
        name = contactRemote.name,
        userRemoteId = contactRemote.hostUserId,
        userLogin = contactRemote.friendUser?.name
    )

    fun fromModelToEntity(contact: Contact, remoteId: Long): ContactEntity = ContactEntity(
        remoteId = remoteId,
        name = contact.name,
        userRemoteId = contact.userRemoteId
    )

    fun fromRemoteToEntity(contact: ContactRemote): ContactEntity = ContactEntity(
        remoteId = contact.id,
        name = contact.name,
        userRemoteId = contact.hostUserId,
        userLogin = contact.friendUser?.name
    )

    fun fromModelToUpdateRequest(contact: Contact): ContactRequest = ContactRequest(
        contact.name
    )

    fun fromModelToRequest(contact: ContactRequestModel): ContactRequest = ContactRequest(
        contact.name,
        contact.authorisedLogin
    )
}