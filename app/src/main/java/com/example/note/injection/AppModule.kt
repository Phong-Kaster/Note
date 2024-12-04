package com.example.note.injection

import android.content.Context
import com.example.note.NoteApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext context: Context): NoteApplication {
        return context as NoteApplication
    }


    @Provides
    fun provideDispatcherIO(): CoroutineDispatcher = Dispatchers.IO
}