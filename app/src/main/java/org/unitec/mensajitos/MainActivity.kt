package org.unitec.mensajitos

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.android.synthetic.main.activity_main.*
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

class MainActivity : AppCompatActivity() {
var mensajes=ArrayList<Mensaje>()
    var mensaje="hola mundo"
    var mensa=Mensaje();
    var miid="";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

           boton.setOnClickListener {
               TareaMensajes().execute(null,null,null)
           }



    }

    inner  class TareaMensajes :AsyncTask<String,String,String>(){
        override fun onPreExecute() {
            super.onPreExecute()

              miid=   textoId.text.toString();

            //miid="5bb42224b1ff2b18b3c139be"
        }

        override fun doInBackground(vararg p0: String?): String {
            //Aqui dentroa vamos a establecer nuestra conexion al back end
            //pero no podemos mostrar nada en las componentes visuales
            //Primero la url
            var url="https://topoyiyo.herokuapp.com/api/mensaje/"+miid

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
  //mensajes= maper.readValue(valor,object :TypeReference<ArrayList<Mensaje>>(){})
            mensa= maper.readValue(valor,object :TypeReference<Mensaje>(){})


            return "mensajes"
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            Toast.makeText(applicationContext,mensa.cuerpo,Toast.LENGTH_LONG).show()
        }
    }



}
