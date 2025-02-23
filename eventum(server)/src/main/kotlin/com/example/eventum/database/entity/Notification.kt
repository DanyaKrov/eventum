package com.example.eventum.database.entity

import jakarta.persistence.*
import java.sql.Timestamp
import java.time.LocalDate

@Entity
@Table(name = "notification")
data class Notification(
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "name", unique = false)
    var name: String = "",
    @Column(name = "description", unique = false)
    var description: String = "",
    @Column(name ="time")
    var time: LocalDate,
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    var event: Event
)
