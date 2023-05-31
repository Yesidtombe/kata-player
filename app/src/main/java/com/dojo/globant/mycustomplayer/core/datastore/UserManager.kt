package com.dojo.globant.mycustomplayer.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataUser: DataStore<Preferences> by preferencesDataStore(name = "favorites")

class UserManager(private val context: Context) {

    suspend fun saveFavoriteTrack(key: String, value: String) {
        context.dataUser.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    suspend fun deleteFavoriteTrack(key: String) {
        context.dataUser.edit {
            if (it.contains(stringPreferencesKey(key)))
                it.remove(stringPreferencesKey(key))
        }
    }

    suspend fun getFavoriteTrack(key: String): String {
        val prefs = context.dataUser.data.first()
        return prefs[stringPreferencesKey(key)].orEmpty()
    }

    suspend fun getAllFavoriteTracks(): List<String> {
        val keys = context.dataUser.data.map {
            it.asMap().keys
        }
        val favorites = mutableListOf<String>()
        keys.first().forEach { key ->
            favorites.add(context.dataUser.data
                .map {
                    it[key]
                }.first() as String)
        }
        return favorites
    }
}