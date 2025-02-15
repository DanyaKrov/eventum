package com.example.eventum.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "tag")
data class Tag(
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "name", nullable = false)
    var name: String
)