package com.maruchin.cleangirl.data.model

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Room(
    val id: String,
    val name: String,
    val icon: RoomType,
    val tasks: List<Task> = emptyList()
) {

    fun update(updatedRoom: UpdatedRoom): Room = copy(
        name = updatedRoom.name,
        icon = updatedRoom.type
    )

    fun toggleTaskCompleted(taskCompletionToggle: TaskCompletionToggle): Room {
        val updatedTasks = tasks.map { task ->
            if (task.id == taskCompletionToggle.taskId) {
                task.toggleCompleted(taskCompletionToggle.date, taskCompletionToggle.completed)
            } else {
                task
            }
        }
        return copy(tasks = updatedTasks)
    }

    fun addTask(newTask: NewTask): Room = copy(
        tasks = tasks + Task.from(newTask)
    )

    fun updateTask(updatedTask: UpdatedTask): Room {
        val updatedTasks = tasks.map { task ->
            if (task.id == updatedTask.id) {
                task.copy(name = updatedTask.name, recurrence = updatedTask.recurrence)
            } else {
                task
            }
        }
        return copy(tasks = updatedTasks)
    }

    fun deleteTask(taskId: String): Room = copy(
        tasks = tasks.filter { it.id != taskId }
    )

    companion object {

        @OptIn(ExperimentalUuidApi::class)
        fun from(newRoom: NewRoom) = Room(
            id = Uuid.random().toString(),
            name = newRoom.name,
            icon = newRoom.icon,
            tasks = emptyList()
        )
    }
}

val sampleRoomLivingRoom = Room(
    id = "living_room",
    name = "Salon",
    icon = RoomType.LivingRoom,
    tasks = listOf(
        Task(
            id = "1",
            name = "Odkurzyć dywan",
            recurrence = Recurrence.Weekly(
                daysOfWeek = setOf(DayOfWeek.SATURDAY)
            ),
            records = listOf(
                LocalDate(2024, 6, 1),
                LocalDate(2024, 6, 8)
            )
        ),
        Task(
            id = "2",
            name = "Umyć okna",
            recurrence = Recurrence.Monthly(
                daysOfMoth = setOf(1)
            ),
            records = listOf(
                LocalDate(2024, 5, 1),
                LocalDate(2024, 6, 1)
            )
        ),
        Task(
            id = "3",
            name = "Zrobić porządek z książkami",
            recurrence = Recurrence.Monthly(
                daysOfMoth = setOf(15)
            ),
            records = listOf(
                LocalDate(2024, 5, 15),
                LocalDate(2024, 6, 15)
            )
        ),
        Task(
            id = "13",
            name = "Przetrzeć meble",
            recurrence = Recurrence.Weekly(
                daysOfWeek = setOf(DayOfWeek.WEDNESDAY)
            ),
            records = listOf(
                LocalDate(2024, 6, 5),
                LocalDate(2024, 6, 12)
            )
        ),
        Task(
            id = "14",
            name = "Podlać kwiaty",
            recurrence = Recurrence.Weekly(
                daysOfWeek = setOf(DayOfWeek.MONDAY)
            ),
            records = listOf(
                LocalDate(2024, 6, 3),
                LocalDate(2024, 6, 10)
            )
        ),
        Task(
            id = "15",
            name = "Wymienić świece zapachowe",
            recurrence = Recurrence.Monthly(
                daysOfMoth = setOf(1)
            ),
            records = listOf(
                LocalDate(2024, 5, 1),
                LocalDate(2024, 6, 1)
            )
        ),
        Task(
            id = "16",
            name = "Wyprać zasłony",
            recurrence = Recurrence.Monthly(
                daysOfMoth = setOf(20)
            ),
            records = listOf(
                LocalDate(2024, 5, 20),
                LocalDate(2024, 6, 20)
            )
        )
    )
)

val sampleRoomBedroom = Room(
    id = "bedroom",
    name = "Sypialnia",
    icon = RoomType.Bedroom,
    tasks = listOf(
        Task(
            id = "4",
            name = "Zrobić łóżko",
            recurrence = Recurrence.Daily,
            records = listOf(
                LocalDate(2024, 6, 10),
                LocalDate(2024, 6, 11)
            )
        ),
        Task(
            id = "5",
            name = "Umyć lustro",
            recurrence = Recurrence.Weekly(
                daysOfWeek = setOf(DayOfWeek.SUNDAY)
            ),
            records = listOf(
                LocalDate(2024, 6, 2),
                LocalDate(2024, 6, 9)
            )
        ),
        Task(
            id = "6",
            name = "Przewietrzyć pokój",
            recurrence = Recurrence.Daily,
            records = listOf(
                LocalDate(2024, 6, 10),
                LocalDate(2024, 6, 11)
            )
        )
    )
)

val sampleRoomKitchen = Room(
    id = "kitchen",
    name = "Kuchnia",
    icon = RoomType.Kitchen,
    tasks = listOf(
        Task(
            id = "7",
            name = "Umyć naczynia",
            recurrence = Recurrence.Daily,
            records = listOf(
                LocalDate(2024, 6, 10),
                LocalDate(2024, 6, 11)
            )
        ),
        Task(
            id = "8",
            name = "Odkurzyć podłogę",
            recurrence = Recurrence.Weekly(
                daysOfWeek = setOf(DayOfWeek.FRIDAY)
            ),
            records = listOf(
                LocalDate(2024, 6, 7),
                LocalDate(2024, 6, 14)
            )
        ),
        Task(
            id = "9",
            name = "Wyczyścić lodówkę",
            recurrence = Recurrence.Monthly(
                daysOfMoth = setOf(1)
            ),
            records = listOf(
                LocalDate(2024, 5, 1),
                LocalDate(2024, 6, 1)
            )
        )
    )
)

val sampleRoomBathroom = Room(
    id = "bathroom",
    name = "Łazienka",
    icon = RoomType.Bathroom,
    tasks = listOf(
        Task(
            id = "10",
            name = "Umyć wannę",
            recurrence = Recurrence.Weekly(
                daysOfWeek = setOf(DayOfWeek.SATURDAY)
            ),
            records = listOf(
                LocalDate(2024, 6, 1),
                LocalDate(2024, 6, 8)
            )
        ),
        Task(
            id = "11",
            name = "Wyczyścić toaletę",
            recurrence = Recurrence.Weekly(
                daysOfWeek = setOf(DayOfWeek.WEDNESDAY)
            ),
            records = listOf(
                LocalDate(2024, 6, 5),
                LocalDate(2024, 6, 12)
            )
        ),
        Task(
            id = "12",
            name = "Umyć lustro",
            recurrence = Recurrence.Weekly(
                daysOfWeek = setOf(DayOfWeek.SUNDAY)
            ),
            records = listOf(
                LocalDate(2024, 6, 2),
                LocalDate(2024, 6, 9)
            )
        )
    )
)

val sampleRoomList = listOf(
    sampleRoomLivingRoom,
    sampleRoomBedroom,
    sampleRoomKitchen,
    sampleRoomBathroom
)
