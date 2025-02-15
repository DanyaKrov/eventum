package com.example.eventum.database.entity

import jakarta.persistence.*

@Entity
@Table(name="present")
data class Present( // maybe later on will add links or picture as parameters
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "title", nullable = false)
    var title: String,
    @Column(name = "description", nullable = false)
    var description: String,
    @ManyToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "wishlist_id", nullable = true)
    var wishList: WishList
)
