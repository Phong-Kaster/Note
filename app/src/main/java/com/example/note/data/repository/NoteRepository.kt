package com.example.note.data.repository

import com.example.note.data.database.NoteDatabase
import com.example.note.data.database.entity.NoteEntity
import com.example.note.domain.mapper.NoteMapper.toEntity
import com.example.note.domain.mapper.NoteMapper.toModel
import com.example.note.domain.model.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository
@Inject
constructor(
    database: NoteDatabase,
    private val dispatchIO: CoroutineDispatcher,
) {
    private val dao = database.noteDao()

    suspend fun getAllFlow(): Flow<List<Note>> {
        return dao.getAllFlow().map { listOfNote -> listOfNote.map { note -> note.toModel() } }
    }

    suspend fun getAll(): List<NoteEntity> = withContext(dispatchIO) {
        dao.getAll()
    }

    suspend fun delete(note: Note) = withContext(dispatchIO) {
        dao.delete(entity = note.toEntity())
    }

    suspend fun deleteMultiple(vararg notes: Note) = withContext(dispatchIO) {
        dao.deleteMultiple(entity = notes.map { it.toEntity() }.toTypedArray())
    }

    suspend fun clear() = withContext(dispatchIO) {
        dao.clear()
    }

    suspend fun insert(note: Note) = withContext(dispatchIO) {
        dao.insert(entity = note.toEntity())
    }

    suspend fun update(note: Note) = withContext(dispatchIO) {
        dao.update(entity = note.toEntity())
    }

    suspend fun findNoteById(id: Long): Flow<Note> {
        return dao.findById(id).map { note -> note.toModel() }
    }
}