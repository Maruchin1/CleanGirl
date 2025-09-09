package com.maruchin.cleangirl.data.model

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate

data class Room(
    val id: String,
    val name: String,
    val type: RoomType,
    val tasks: List<Task> = emptyList()
)

val sampleRoomLivingRoom = Room(
    id = "living_room",
    name = "Salon",
    type = RoomType.LivingRoom,
    tasks = listOf(
        Task(
            id = "1",
            name = "Odkurzyć dywan",
            recurrence = Recurrence.Weekly,
            daysOfWeek = setOf(DayOfWeek.SATURDAY),
            daysOfMonth = emptySet(),
            records = setOf(
                LocalDate(2024, 6, 1),
                LocalDate(2024, 6, 8)
            )
        ),
        Task(
            id = "2",
            name = "Umyć okna",
            recurrence = Recurrence.Monthly,
            daysOfWeek = emptySet(),
            daysOfMonth = setOf(1),
            records = setOf(
                LocalDate(2024, 5, 1),
                LocalDate(2024, 6, 1)
            )
        ),
        Task(
            id = "3",
            name = "Zrobić porządek z książkami",
            recurrence = Recurrence.Monthly,
            daysOfWeek = emptySet(),
            daysOfMonth = setOf(15),
            records = setOf(
                LocalDate(2024, 5, 15),
                LocalDate(2024, 6, 15)
            )
        ),
        Task(
            id = "13",
            name = "Przetrzeć meble",
            recurrence = Recurrence.Weekly,
            daysOfWeek = setOf(DayOfWeek.WEDNESDAY),
            daysOfMonth = emptySet(),
            records = setOf(
                LocalDate(2024, 6, 5),
                LocalDate(2024, 6, 12)
            )
        ),
        Task(
            id = "14",
            name = "Podlać kwiaty",
            recurrence = Recurrence.Weekly,
            daysOfWeek = setOf(DayOfWeek.MONDAY),
            daysOfMonth = emptySet(),
            records = setOf(
                LocalDate(2024, 6, 3),
                LocalDate(2024, 6, 10)
            )
        ),
        Task(
            id = "15",
            name = "Wymienić świece zapachowe",
            recurrence = Recurrence.Monthly,
            daysOfWeek = emptySet(),
            daysOfMonth = setOf(1),
            records = setOf(
                LocalDate(2024, 5, 1),
                LocalDate(2024, 6, 1)
            )
        ),
        Task(
            id = "16",
            name = "Wyprać zasłony",
            recurrence = Recurrence.Monthly,
            daysOfWeek = emptySet(),
            daysOfMonth = setOf(20),
            records = setOf(
                LocalDate(2024, 5, 20),
                LocalDate(2024, 6, 20)
            )
        )
    )
)

val sampleRoomBedroom = Room(
    id = "bedroom",
    name = "Sypialnia",
    type = RoomType.Bedroom,
    tasks = listOf(
        Task(
            id = "4",
            name = "Zrobić łóżko",
            recurrence = Recurrence.Daily,
            daysOfWeek = emptySet(),
            daysOfMonth = emptySet(),
            records = setOf(
                LocalDate(2024, 6, 10),
                LocalDate(2024, 6, 11)
            )
        ),
        Task(
            id = "5",
            name = "Umyć lustro",
            recurrence = Recurrence.Weekly,
            daysOfWeek = setOf(DayOfWeek.SUNDAY),
            daysOfMonth = emptySet(),
            records = setOf(
                LocalDate(2024, 6, 2),
                LocalDate(2024, 6, 9)
            )
        ),
        Task(
            id = "6",
            name = "Przewietrzyć pokój",
            recurrence = Recurrence.Daily,
            daysOfWeek = emptySet(),
            daysOfMonth = emptySet(),
            records = setOf(
                LocalDate(2024, 6, 10),
                LocalDate(2024, 6, 11)
            )
        )
    )
)

val sampleRoomKitchen = Room(
    id = "kitchen",
    name = "Kuchnia",
    type = RoomType.Kitchen,
    tasks = listOf(
        Task(
            id = "7",
            name = "Umyć naczynia",
            recurrence = Recurrence.Daily,
            daysOfWeek = emptySet(),
            daysOfMonth = emptySet(),
            records = setOf(
                LocalDate(2024, 6, 10),
                LocalDate(2024, 6, 11)
            )
        ),
        Task(
            id = "8",
            name = "Odkurzyć podłogę",
            recurrence = Recurrence.Weekly,
            daysOfWeek = setOf(DayOfWeek.FRIDAY),
            daysOfMonth = emptySet(),
            records = setOf(
                LocalDate(2024, 6, 7),
                LocalDate(2024, 6, 14)
            )
        ),
        Task(
            id = "9",
            name = "Wyczyścić lodówkę",
            recurrence = Recurrence.Monthly,
            daysOfWeek = emptySet(),
            daysOfMonth = setOf(1),
            records = setOf(
                LocalDate(2024, 5, 1),
                LocalDate(2024, 6, 1)
            )
        )
    )
)

val sampleRoomBathroom = Room(
    id = "bathroom",
    name = "Łazienka",
    type = RoomType.Bathroom,
    tasks = listOf(
        Task(
            id = "10",
            name = "Umyć wannę",
            recurrence = Recurrence.Weekly,
            daysOfWeek = setOf(DayOfWeek.SATURDAY),
            daysOfMonth = emptySet(),
            records = setOf(
                LocalDate(2024, 6, 1),
                LocalDate(2024, 6, 8)
            )
        ),
        Task(
            id = "11",
            name = "Wyczyścić toaletę",
            recurrence = Recurrence.Weekly,
            daysOfWeek = setOf(DayOfWeek.WEDNESDAY),
            daysOfMonth = emptySet(),
            records = setOf(
                LocalDate(2024, 6, 5),
                LocalDate(2024, 6, 12)
            )
        ),
        Task(
            id = "12",
            name = "Umyć lustro",
            recurrence = Recurrence.Weekly,
            daysOfWeek = setOf(DayOfWeek.SUNDAY),
            daysOfMonth = emptySet(),
            records = setOf(
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
