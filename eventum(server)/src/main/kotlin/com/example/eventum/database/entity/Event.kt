package com.example.eventum.database.entity
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.LocalDateTime

@Entity
@Table(name = "event")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "name", nullable = false)
    var name: String,
    @Column(name = "description")
    var description: String,
    @Column(name = "time")
    var time: LocalDateTime,
    @Column(name = "picture", nullable = true)
    var picture: String,
    @ManyToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "tag_id", nullable = true)
    var tag: Tag?, // tag of event
    @ManyToMany
    @JoinTable(
        name = "event_user",
        joinColumns = [JoinColumn(name = "event_id", referencedColumnName = "id", nullable = true)],
        inverseJoinColumns = [JoinColumn(name = "user_id", nullable = true)],
    )
    var usersIds: MutableSet<User>, // users, which target in event
    @ManyToMany
    @JoinTable(
        name = "event_contact",
        joinColumns = [JoinColumn(name = "event_id", referencedColumnName = "id", nullable = true)],
        inverseJoinColumns = [JoinColumn(name = "contact_id", nullable = true)],
    )
    var contactsIds: MutableSet<Contact>, // contacts, which target in event
)