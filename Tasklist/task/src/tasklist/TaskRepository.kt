package tasklist

object TaskRepository {
    private var tasks = mutableListOf<Task>()
    val isEmpty: Boolean
        get() = tasks.isEmpty()
    val lastId: Int
        get() {
            return if (isEmpty) 0 else tasks.lastIndex + 1
        }

    fun addTask(task: Task) {
        tasks.add(task)
    }

    fun addAll(tasks: List<Task>) {
        this.tasks.addAll(tasks)
    }

    fun removeTask(task: Task) {
        tasks.remove(task)
    }

    fun removeTask(id: Int) {
        tasks.removeAt(id - 1)
    }

    fun editTask(id: Int, editedTask: Task) {
        tasks.removeAt(id - 1)
        tasks.add(id - 1, editedTask)
    }

    fun get(id: Int) = tasks[id - 1]
    fun getAllTasks() = tasks.toList()


}

