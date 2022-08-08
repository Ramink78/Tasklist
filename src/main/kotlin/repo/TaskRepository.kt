package repo

import Task

interface TaskRepository {
    val isEmpty: Boolean
    val lastId: Int
    fun addTask(task: Task)
    fun addAll(tasks: List<Task>)
    fun remove(id: Int)
    fun remove(task: Task)
    fun edit(id: Int, editedTask: Task)
    fun get(id: Int): Task
    fun getAllTasks(): List<Task>
    fun contains(task: Task): Boolean

}