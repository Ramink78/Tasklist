package repo

import Task

class TaskRepositoryImpl : TaskRepository {
    private var tasks = mutableListOf<Task>()
    override val isEmpty: Boolean
        get() = tasks.isEmpty()
    override val lastId: Int
        get() = if (isEmpty) 0 else tasks.lastIndex + 1


    override fun addTask(task: Task) {
        tasks.add(task)
    }

    override fun addAll(tasks: List<Task>) {
        this.tasks.addAll(tasks)
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

    override fun get(id: Int) = with(tasks[id - 1]) {
        copy(id = tasks.indexOf(this))
    }

    override fun getAllTasks(): List<Task> {
        return tasks.mapIndexed { index, task ->
            task.copy(id = index + 1)
        }
    }

    override fun contains(task: Task) = tasks.contains(task)
}