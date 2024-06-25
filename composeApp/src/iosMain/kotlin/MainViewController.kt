import androidx.compose.ui.window.ComposeUIViewController
import org.example.todolistkmp.di.KoinInitializer

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) { App() }