package com.example.eventum.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "`user`")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "name", nullable = false)
    var name: String,
    @Column(name = "email", nullable = false)
    var email: String,
    @Column(name = "picture", nullable = true)
    var picture: String,

    @ManyToMany
    @JoinTable(
        name = "user_contacts",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "contact_id")]
    )
    val contacts: MutableSet<User> = mutableSetOf()
    // need to add password.
    // But I need to configure format of saving it
)
