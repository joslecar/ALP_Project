package controllers

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ToggleButton
import java.net.URL
import java.util.*

class Programa1:Initializable {
    @FXML lateinit var buttonT: ToggleButton

    override fun initialize(location: URL?, resources: ResourceBundle?) {

    }
    fun setTextTButton(text:String){
        buttonT.text = text
    }

}