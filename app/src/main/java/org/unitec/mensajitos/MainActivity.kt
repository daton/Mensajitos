package org.unitec.mensajitos

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

class MainActivity : AppCompatActivity() {
var mensajes=ArrayList<Mensaje>()
    var mensaje="hola mundo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TareaMensajes().execute(null,null,null)


    }

    inner  class TareaMensajes :AsyncTask<String,String,String>(){

        override fun doInBackground(vararg p0: String?): String {
            //Aqui dentroa vamos a establecer nuestra conexion al back end
            //pero no podemos mostrar nada en las componentes visuales
            //Primero la url
            var url="https://topoyiyo.herokuapp.com/api/mensaje"

            //Generamos un objeto de la clase RestTemplate
            var rest=RestTemplate()

            //a ese objeto rest le aplicamos el convertidor de json a objetos
            rest.messageConverters.add(MappingJackson2HttpMessageConverter())

            //Invocamos el respeectivo metodo http con nuestro objeto rest
         var valor=   rest.getForObject(url,String::class.java)

            //Por ultimo este string que se llama valor lo cambiamos a un
            //tipo de referncia arralist generico a mensajes
            var maper=ObjectMapper()
            //ya convertimos
  mensajes= maper.readValue(valor,object :TypeReference<ArrayList<Mensaje>>(){})


            return "mensajes"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            // Depues del doInbackground la ejecucion de este thread pasa a este
            //metodo el cuañ si puede enlazasre a componentes visuales
           Toast.makeText(applicationContext,"Mensajes hallados "+mensajes.size,
               Toast.LENGTH_LONG).show()
        }
    }



}
