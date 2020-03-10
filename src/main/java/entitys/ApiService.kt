package entitys

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
     @GET
     fun getCharacterByName(@Url url:String): Call<DogsResponse>

     @GET
     fun getUsernames(@Url url:String): Call<List<Usuario>>

     @GET
     fun getGestionNames(@Url url:String): Call<List<Gestion>>

     @GET
     fun getModuleNames(@Url url:String): Call<List<Module>>

     @POST("user/create")
     fun postUsuario(@Body user:Usuario): Call<Usuario>
}