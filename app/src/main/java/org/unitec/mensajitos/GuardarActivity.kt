package org.unitec.mensajitos

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_guardar.*

class GuardarActivity : AppCompatActivity() {
   var mensa=Mensaje();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guardar)
    }

    inner class TareaGuardar :AsyncTask<String,String,String>(){
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }

        override fun doInBackground(vararg p0: String?): String {

            return "hola"
        }

        override fun onPreExecute() {
            super.onPreExecute()
            //pedimos el valor del titulo del mensaje
           mensa.titulo= textoMiTitulo.text.toString()
            //Pedimos en valor al cuerpo del mensaje;
            mensa.cuerpo=textoMiCuerpo.text.toString();
            //Construir un objeto de tipo mensaje a partir de estos valores


        }
    }
}
