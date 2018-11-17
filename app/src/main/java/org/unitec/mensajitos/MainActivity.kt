package org.unitec.mensajitos

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

class MainActivity : AppCompatActivity() {
    var estatus = Estatus()
    var practica=Datos()
    var algo: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TareaRegistrarse().execute(null,null,null)
    }

    inner class  TareaRegistrarse : AsyncTask<Void, Void, Void>(){

        override fun onPreExecute() {
            super.onPreExecute()

            //Generamos o llenamos la practica

           // obtenerUbicacion()




        }

        override fun doInBackground(vararg params: Void?): Void? {

            try {
                var url2 ="https://www.viinacademy.com/xjismz/Action.php";
                //  var url2="http://192.168.100.7:8080/api/practica"

                val restTemplate = RestTemplate()
                restTemplate.messageConverters.add(MappingJackson2HttpMessageConverter())
             //   print("ZZZZZZZZZZZZZZZZZZZZZZZ " + practica?.id);

                val maper = ObjectMapper()
                //  usuarios = maper.readValue(estring, object : TypeReference<ArrayList<Usuario>>() {})
                 practica.name="Ismael"
                practica.emailaddress="rapidclimate@gmail.com"
                practica.subject="algo"
                val respuesta = restTemplate.postForObject(url2, practica, String::class.java)
                algo= maper.readValue(respuesta, String::class.java)
                // else estatus = null
                print("EL DATO ES ALGO"+algo)

                println("DESPUES DE REST");
            }catch(t:Throwable){
                //  Toast.makeText(applicationContext,"No tienes internet", Toast.LENGTH_LONG).show();

                print("HAAAAAGH   "+t.message);
            }

            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
          Toast.makeText(applicationContext, "mensaje:"+algo, Toast.LENGTH_LONG).show()
        }
    }

}
