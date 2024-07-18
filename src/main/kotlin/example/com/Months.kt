package example.com

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.time

object Months: Table("months") {
    val id = integer("id").autoIncrement()
    val month = varchar("month", 50)
    var year = integer("year")

    override val primaryKey = PrimaryKey(id)
}

object Days : Table("days") {
    val id = integer("id").autoIncrement()
    val monthId = integer("month_id") references Months.id
    val day = integer("day")
    val isBooked = bool("is_booked")

    override val primaryKey = PrimaryKey(id)
}

object TimeSlots : Table("time_slots") {
    val id = integer("id").autoIncrement()
    val dayId = integer("day_id") references Days.id
    val time = time("time")
    val isBooked = bool("is_booked")

    override val primaryKey = PrimaryKey(id)
}