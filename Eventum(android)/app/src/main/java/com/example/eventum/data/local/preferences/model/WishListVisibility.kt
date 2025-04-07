package com.example.eventum.data.local.preferences.model

enum class WishListVisibility(val value: String) {
    OPEN_VIEW("open"), // everyone will see
    FRIENDS_VIEW("FRIENDS"), // only for friends
    CLOSE_VIEW("system"); // only for user himself

    companion object {
        fun fromString(value: String): WishListVisibility {
            return values().find { it.value == value } ?: FRIENDS_VIEW // default value
        }
    }
}