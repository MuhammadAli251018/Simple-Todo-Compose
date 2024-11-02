package com.example.simpletodo.features.core.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.simpletodo.utl.TestRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomTasksDaoTest : TestRunner<TaskDb>() {

    private val dao =  Room.databaseBuilder(
        ApplicationProvider.getApplicationContext(),
    TasksDatabase::class.java,
    "tasks-db"
    ).allowMainThreadQueries().build().tasksDao()

    private val testTask = TaskDb(
        id = 0,
        title= "test-title",
        content = "test-content",
        issueTime = 0L,
        completeTime = -5L
    )

    @Test
    fun success_when_inserting_task() = runTest {
        val id = dao.insertTask(testTask)
        val result = dao.getTask(id.toInt())

        assert(testTask matches result)
    }

    override val rule: TaskDb.(TaskDb) -> Boolean = {anther ->
        content == anther.content && title == anther.title
                && issueTime == anther.issueTime
                && completeTime == anther.completeTime
    }
}