package com.example.eventum

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EventumApplication

fun main(args: Array<String>) {
	runApplication<EventumApplication>(*args)
}
