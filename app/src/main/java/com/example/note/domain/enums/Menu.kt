package com.example.note.domain.enums

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.example.note.R

enum class Menu(
    @StringRes val nameId: Int,
    @DrawableRes val drawableId: Int,
    @IdRes val destinationId: Int,
    val directions: Int,
    @IdRes val homeDestinationId: Int,
){
    Note(
        nameId = R.string.note,
        drawableId = R.drawable.ic_note,
        destinationId = R.id.noteFragment,
        directions = R.id.toNote,
        homeDestinationId = R.id.noteFragment
    ),
    Task(
        nameId = R.string.task,
        drawableId = R.drawable.ic_task,
        destinationId = R.id.taskFragment,
        directions = R.id.toTask,
        homeDestinationId = R.id.taskFragment
    )
}