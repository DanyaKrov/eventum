package com.example.eventum.util.reader

import android.content.Context
import dagger.internal.DaggerGenerated

@DaggerGenerated
class StringRepository(private val context: Context) {
    fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }
}