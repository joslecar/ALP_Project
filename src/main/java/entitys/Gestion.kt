package entitys

import com.google.gson.annotations.SerializedName

data class Gestion(@SerializedName("id") val ID:String,
                   @SerializedName("nombre") val nombre:String,
                   @SerializedName("nomcorto") val nom_corto:String,
                   @SerializedName("namebmp") val namebmp:String,
                   @SerializedName("cod_2") val cod:String,
                   var listaModulos: MutableList<Module>) {

    init {
        listaModulos = mutableListOf()
        listaModulos.add(Module())
    }


    fun setModulos(){

    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Gestion

        if (ID != other.ID) return false

        return true
    }

    override fun hashCode(): Int {
        return ID.hashCode()
    }

}