package example.com.Database

import example.com.Days
import example.com.Months
import example.com.TimeSlots
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.time
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

fun populateTablesUntilEndOfYear() {
    val months = listOf(
        "Январь" to 1,
        "Февраль" to 2,
        "Март" to 3,
        "Апрель" to 4,
        "Май" to 5,
        "Июнь" to 6,
        "Июль" to 7,
        "Август" to 8,
        "Сентябрь" to 9,
        "Октябрь" to 10,
        "Ноябрь" to 11,
        "Декабрь" to 12
    )

    val timeSlots = listOf("09:00", "12:00", "15:00")

    val currentYear = LocalDateTime.now().year

    transaction {
        try {
            months.forEach { (monthName, monthNumber) ->
                if (monthName == null || currentYear == null) {
                    println("Error: monthName or currentYear is null")
                    return@forEach
                }
                try {
                    val monthId = Months.insert {
                        it[Months.month] = monthName
                        it[Months.year] = currentYear
                    } get Months.id

                    for (day in 1..getDaysInMonth(monthNumber, currentYear)) {
                        Days.insert {
                            it[Days.monthId] = monthId
                            it[Days.day] = day
                            it[Days.isBooked] = false
                        }
                    }
                } catch (e: Exception) {
                    println("Ошибка при вставке месяца: $e")
                    rollback()
                    throw e
                }
            }

            Days.selectAll().forEach { day ->
                timeSlots.forEach { time ->
                    TimeSlots.insert {
                        it[TimeSlots.dayId] = day[Days.id]
                        it[TimeSlots.time] = time(time)
                        it[TimeSlots.isBooked] = false
                    }
                }
            }
        } catch (e: Exception) {
            rollback()
            println("Ошибка заполнения таблиц: ${e.message}")
        }
    }
}

fun getDaysInMonth(month: Int, year: Int): Int {
    return when (month) {
        2 -> if (isLeapYear(year)) 29 else 28
        4, 6, 9, 11 -> 30
        else -> 31
    }
}

fun isLeapYear(year: Int): Boolean {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
}