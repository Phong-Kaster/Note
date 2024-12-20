package com.example.jetpack.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.Density
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.note.ui.theme.DarkCustomizedTheme
import com.example.note.ui.theme.LightCustomizedTheme
import com.example.note.ui.theme.NoteTheme
import com.example.note.util.AppUtil
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject


val LocalLocale = staticCompositionLocalOf { Locale.getDefault() }
val LocalNavController = staticCompositionLocalOf<NavController?> { null }
val LocalTheme = compositionLocalOf { DarkCustomizedTheme }

@AndroidEntryPoint
open class CoreFragment : Fragment(), CoreBehavior {

    //@Inject
    //lateinit var settingRepository: SettingRepository
    protected open val TAG: String = this.javaClass.simpleName

    /** Dark Mode*/
    private var enableDarkMode by mutableStateOf(true)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        makeStatusBarTransparent()
        //setupDarkMode()
        return ComposeView(requireActivity()).apply {
            setViewCompositionStrategy(strategy = ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                CompositionLocalProvider(
                    LocalNavController provides findNavController(),
                    LocalLocale provides requireActivity().resources.configuration.locales[0],
                    LocalDensity provides Density(LocalDensity.current.density, 1f),
                    LocalTheme provides if (enableDarkMode) DarkCustomizedTheme else LightCustomizedTheme,
                    *compositionLocalProvider().toTypedArray()
                ) {
                    NoteTheme {
                        ComposeView()
                    }
                }
            }
        }
    }

    @Composable
    protected open fun compositionLocalProvider(): List<ProvidedValue<*>> {
        return listOf()
    }

    @Composable
    open fun ComposeView() {
    }

    override fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun hideNavigationBar() {
        AppUtil.hideNavigationBar(window = requireActivity().window)
    }

    override fun makeStatusBarTransparent() {
        /*with(requireActivity().window) {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }*/
    }

    /*private fun setupDarkMode() {
        lifecycleScope.launch {
            settingRepository.enableDarkModeFlow().collect {
                enableDarkMode = it
            }
        }
    }*/
}