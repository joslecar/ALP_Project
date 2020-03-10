package controllers

import entitys.ApiService
import entitys.DogsResponse
import entitys.Usuario
import javafx.event.Event
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import javafx.stage.Stage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL
import java.util.*

class LoginCont():Initializable {
    @FXML lateinit var contenedorVBox:VBox
    @FXML lateinit var loginButton: Button
    @FXML lateinit var passField:PasswordField
    @FXML lateinit var userField:TextField
    //val menuPrincipalView:menuPrincipal = menuPrincipal()
    var listaUsuarios:List<Usuario> = arrayListOf()
    val usuarios:MutableList<Usuario> = mutableListOf(
            Usuario(1,"sa","Rootpass1","Prueba"),
            Usuario(2,"sa1","Rootpass1","Prueba1"),
            Usuario(3,"sa2","Rootpass1","Prueba2")
    )

    private fun getRetrofit(): Retrofit {
        println("HEY")
        return Retrofit.Builder()
                .baseUrl("http://186.68.42.218:8084/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }
    private fun loginCheck(usuario: String,pass: String):Boolean{
        val user = Usuario(usuario,pass)
        val call = getRetrofit().create(ApiService::class.java).getUsernames("user/getusers").execute()
        listaUsuarios = call.body() as List<Usuario>
        listaUsuarios.forEach(){e ->
            if(e.equals(user)){
                return true;
            }
        }
        return false
    }
    private fun loginPost(usuario:String, pass:String) {
        val user = Usuario(usuario,pass)
        println(user.toString())
            val call = getRetrofit().create(ApiService::class.java).postUsuario(user).enqueue(object:Callback<Usuario>{
                override fun onResponse(p0: Call<Usuario>, p1: Response<Usuario>) {
                    if(p1.isSuccessful){
                        println(p1.body().toString())
                    }
                }
                override fun onFailure(p0: Call<Usuario>, p1: Throwable) {
                    println("ERROR")
                }

            })
    }
    override fun initialize(location: URL?, resources: ResourceBundle?) {

        loginButton.setOnAction { e->
            if(loginCheck(userField.text,passField.text)){
                loadView(e)
            }else{
                val a = Alert(Alert.AlertType.ERROR)
                a.title = "Error"
                a.headerText = "Login Error"
                a.contentText = "Usuario o contrasena invalidos"
                a.showAndWait()
            }
        }

    }

    fun loginEvent() {

    }

    fun loadView(e:Event){
        val root = FXMLLoader.load<Parent>(javaClass.getResource("/fxml/MenuPrincipal.fxml"))
        val scene:Scene = Scene(root)
        val stage:Stage = (e.source as Node).scene.window as Stage
        stage.scene = scene
        //stage.show()

    }

}

