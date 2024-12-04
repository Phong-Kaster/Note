package com.example.jetpack.core

interface CoreBehavior {

    fun showToast(message: String)

    fun hideNavigationBar()

    /**
     * this function draw app's content in full screen that ignore navigation bar & status bar
     */
    fun makeStatusBarTransparent()
}