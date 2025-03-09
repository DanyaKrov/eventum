package com.example.eventum.util.mapper

import com.example.eventum.data.roomDatabase.entity.ContactEntity
import com.example.eventum.data.roomDatabase.entity.PresentEntity
import com.example.eventum.screen_contacts.domain.model.Contact
import com.example.eventum.screen_presents.domain.model.Present
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