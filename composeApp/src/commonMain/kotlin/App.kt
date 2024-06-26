import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.example.todolistkmp.presentation.add_task.AddTaskScreen
import org.example.todolistkmp.presentation.agenda.AgendaScreen
import org.example.todolistkmp.presentation.detail.DetailScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        MaterialTheme {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "main_screen"
            ) {
                composable("main_screen") {
                    AgendaScreen(navController)
                }
                composable("add_task_screen") {
                    AddTaskScreen(navController)
                }
                composable(
                    route = "detail_screen/{taskId}",
                    arguments = listOf(navArgument("taskId") { type = NavType.IntType })
                ) {
                    DetailScreen(navController, taskId = it.arguments?.getInt("taskId") ?: 0)
                }
            }
        }
    }
}