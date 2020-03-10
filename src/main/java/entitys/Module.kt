package entitys

import com.google.gson.annotations.SerializedName

data class Module(@SerializedName("id") val id: Int,
                  @SerializedName("idGesti") val idGesti: String,
                  @SerializedName("tipo") val tipo: String,
                  @SerializedName("nombre") val nombre: String,
                  @SerializedName("vez") val vez: String,
                  @SerializedName("menu") val menu: String,
                  @SerializedName("describe") val describe: String,
                  @SerializedName("programa") val programa: String,
                  @SerializedName("msg") val msg: String,
                  @SerializedName("exe") val exe: Boolean,
                  @SerializedName("ext") val ext: Boolean,
                  @SerializedName("opciones") val opciones: String) {

    constructor() : this(0,"-","","","","","","","",false,false,"") {

    }

}