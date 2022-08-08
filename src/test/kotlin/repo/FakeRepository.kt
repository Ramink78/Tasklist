package repo

import Task

class FakeRepository : TaskRepository {
    private val tasks = mutableListOf<Task>()
    override val isEmpty: Boolean
        get() = tasks.isEmpty()
    override val lastId: Int
        get() {
            return if (isEmpty) 0 else tasks.lastIndex + 1
        }

    override fun addTask(task: Task) {
        tasks.add(task.copy(id = lastId + 1))
    }

    override fun addAll(tasks: List<Task>) {
        this.tasks.addAll(tasks.mapIndexed { index, task ->
            task.copy(id = index + 1)
        })
    }

    override fun remove(id: Int) {
        tasks.removeAt(id - 1)
    }

    override fun remove(task: Task) {
        tasks.remove(task)
    }

    override fun edit(id: Int, editedTask: Task) {
        tasks.removeAt(id - 1)
        tasks.add(id - 1, editedTask)
    }

    override fun get(id: Int): Task {
        return tasks[id - 1].copy(id = id)
    }

    override fun getAllTasks(): List<Task> {
        return tasks.mapIndexed { index, task ->
            task.copy(id = index + 1)
        }
    }

    override fun contains(task: Task): Boolean {
        return tasks.contains(task)
    }
}