package com.example.eventum.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "wishlist")
data class WishList(
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "name", nullable = false)
    var name: String,
    @OneToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "user_id", nullable = true)
    var user: User,

)
