package com.example.eventum.util.mapper

import com.example.eventum.data.local.entity.ContactEntity
import com.example.eventum.screen_contacts.domain.model.Contact
import dagger.internal.DaggerGenerated

@DaggerGenerated
class ContactMapper {
    fun fromEntityToModel(contactEntity: ContactEntity): Contact = Contact(
        id = contactEntity.id,
        name = contactEntity.name
    )

    fun fromModelToEntity(contact: Contact): ContactEntity = ContactEntity(
        id = contact.id,
        remoteId = contact.remoteId,
        name = contact.name
    )
}