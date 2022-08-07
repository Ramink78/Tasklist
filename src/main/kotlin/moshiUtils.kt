package tasklist

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

fun tasksToJson(tasks: List<Task>): String? {
    if (tasks.isEmpty()) return null
    val type = Types.newParameterizedType(List::class.java, Task::class.java)
    val taskListAdapter = moshi.adapter<List<Task>>(type)
    return taskListAdapter.toJson(tasks)
}

fun jsonToTasks(json: String): List<Task>? {
    val type = Types.newParameterizedType(List::class.java, Task::class.java)
    val taskListAdapter = moshi.adapter<List<Task>>(type)
    return taskListAdapter.fromJson(json)
}