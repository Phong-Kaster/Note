package com.example.note.injection

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.note.data.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val noteDatabase = "NoteDatabase"

    @Provides
    @Singleton
    fun provideQuoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(context = context, klass = NoteDatabase::class.java, name = noteDatabase)
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .fallbackToDestructiveMigration()
            .build()
    }

}