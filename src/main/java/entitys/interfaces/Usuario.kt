package entitys.interfaces

import me.liuwj.ktorm.entity.Entity
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

interface Usuario : Entity<Usuario> {
    companion object : Entity.Factory<Usuario>()
    val id: String
    var usuario:String
    var pass:String
    var nombre:String
    var userfec: LocalDateTime
    var modulo: String
}