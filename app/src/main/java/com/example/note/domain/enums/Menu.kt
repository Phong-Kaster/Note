package com.example.note.domain.enums

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.example.note.R

enum class Menu
constructor(
    @StringRes val nameId: Int,
    @DrawableRes val drawableId: Int,
    @IdRes val destinationId: Int,
    val directions: Int,
    @IdRes val homeDestinationId: Int,
){
    Note(
        nameId = R.string.note,
        drawableId = R.drawable.ic_launcher_foreground,
        destinationId = R.id.homeFragment,
        directions = R.id.toHome,
        homeDestinationId = R.id.homeFragment
    ),
    Task(
        nameId = R.string.task,
        drawableId = R.drawable.ic_launcher_foreground,
        destinationId = R.id.taskFragment,
        directions = R.id.toTask,
        homeDestinationId = R.id.homeFragment
    )
}