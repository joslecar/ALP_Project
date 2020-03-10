package controllers

import entitys.ApiService
import entitys.Gestion
import entitys.Module
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.*
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tornadofx.close
import tornadofx.select
import java.net.URL
import java.util.*


class MenuPrincipal: Initializable{
    //override val tabPane1:TabPane by fxml()
    @FXML var menu : MenuBar = MenuBar()
    @FXML lateinit var root:BorderPane
    @FXML lateinit var tabPane: TabPane
    @FXML lateinit var scroll: ScrollPane
    private val listaGestion = mutableListOf<Gestion>()

    init {
        runBlocking {
            var a = Alert(Alert.AlertType.CONFIRMATION)
            a.contentText="Cargando"
            a.show()
            var cargo = async {cargarDatosApi()}
            cargo.await()
            a.close()
        }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        generarMenu()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://186.68.42.218:8084/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    private fun cargarDatosApi() {
        val call = getRetrofit().create(ApiService::class.java).getGestionNames("gesti/getgestis").execute()
        var call1 = getRetrofit().create(ApiService::class.java).getModuleNames("menu/getmenu").execute()
        val gestis = call.body() as List<Gestion>
        val modules = call1.body() as List<Module>
        gestis.forEach() { gest ->
            if(gest.listaModulos.isNullOrEmpty()){
                gest.listaModulos = mutableListOf<Module>()
            }
            modules.forEach(){mod ->
                if(mod.idGesti == gest.ID){
                    gest.listaModulos.add(mod)
                }
            }
            listaGestion.add(gest)
        }
    }

    fun generarMenu(){
        //inicializarMapa()

        var anchor:AnchorPane = scroll.content as AnchorPane
        var acordion:Accordion = anchor.children.get(0) as Accordion
        tabPane.tabs.clear()
        acordion.panes.clear()
        listaGestion.forEach(){ gest ->
            var opcion:TitledPane = TitledPane()
            opcion.text = gest.nombre
            var contenido:VBox = VBox()
            gest.listaModulos.forEach(){s->
                var label = Label(s.msg)
                label.setOnMouseClicked {
                    var tab = Tab(label.text)
                    tabPane.tabs.add(tab)
                    println(s.nombre)
                    tab.content = loadTabContent(gest.nombre,s.nombre)

                    tab.graphic = Button("x")

                    var btnClose = tab.graphic as Button
                    btnClose.setOnAction { tab.close()}
                    tab.select()
                }
                contenido.children.addAll(label)
            }
            opcion.content = contenido
            acordion.panes.add(opcion)

        }
    }
    fun loadTabContent(content:String,programa:String):Node{
        val loader = FXMLLoader(javaClass.getResource("/fxml/$programa.fxml"))
        val page = loader.load<Parent>()
        val programaController = loader.getController<Programa1>()
        programaController.setTextTButton(content)

        //val page = FXMLLoader.load<Parent>(javaClass.getResource("/fxml/Programa1.fxml"))
        return loader.getRoot()
    }


}

