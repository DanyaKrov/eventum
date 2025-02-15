package com.example.eventum.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "`user`")
data class User(
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "name", nullable = false)
    var name: String,
    @Column(name = "email", nullable = false, unique = true)
    var email: String,
    @Column(name = "picture", nullable = true)
    var picture: String,

    @Column(name = "password", nullable = true)
    var password: String,

    @ManyToMany
    @JoinTable(
        name = "user_friends",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)],
        inverseJoinColumns = [JoinColumn(name = "friend_id", referencedColumnName = "id", nullable = true)],
    )
    var friends: MutableSet<User> = mutableSetOf()
)
