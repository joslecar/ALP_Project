package entitys

import com.google.gson.annotations.SerializedName

data class Usuario(@SerializedName("id") val id:Int,
                   @SerializedName("userid") val user:String,
                   @SerializedName("userlog") val pass:String,
                   @SerializedName("usernom") val nombre:String){

    constructor(user: String,pass: String): this(0,user,pass,"")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (user != other.user) return false
        if (pass != other.pass) return false

        return true
    }

    override fun hashCode(): Int {
        var result = user.hashCode()
        result = 31 * result + pass.hashCode()
        return result
    }

}


