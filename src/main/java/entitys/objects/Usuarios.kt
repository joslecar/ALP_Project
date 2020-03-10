package entitys.objects

import entitys.interfaces.Usuario
import me.liuwj.ktorm.schema.Table
import me.liuwj.ktorm.schema.datetime
import me.liuwj.ktorm.schema.varchar

object Usuarios: Table<Usuario>("Usuario") {
    val id by varchar("userID").bindTo { it.id }
    val username by varchar("usuario").bindTo { it.usuario }
    val password by varchar("pass").bindTo { it.pass }
    val nombre by varchar("nombre").bindTo { it.nombre }
    val fecha by datetime("userfec").bindTo { it.userfec }
    val modulo by varchar("modulo").bindTo { it.modulo }
}