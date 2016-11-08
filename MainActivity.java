package com.ivanlr.ivanlr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Math.*;

public class MainActivity extends AppCompatActivity {

    // Se declaran EditTexts.
    EditText et1, et2, et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Se inicializan con respecto a los creados en el archivo xml.
        et1 = (EditText) findViewById(R.id.etA);
        et2 = (EditText) findViewById(R.id.etB);
        et3 = (EditText) findViewById(R.id.etC);

        Button btnCalcular = (Button) findViewById(R.id.btnCalcular);
        final TextView tv1 = (TextView) findViewById(R.id.tvResultado1);
        final TextView tv2 = (TextView) findViewById(R.id.tvResultado2);

        // Metodo que ejecuta instrucciones cuando se pulsa el boton calcular.
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Se declaran las variables que recogen los datos de los EditText.
                double auxA = Double.valueOf(et1.getText().toString());
                double auxB = Double.valueOf(et2.getText().toString());
                double auxC = Double.valueOf(et3.getText().toString());

                /* Se calculan tres resultados, el primero es el nominador de la suma,
                   el segundo es el nominador de la resta, y el tercer el denominador
                   de ambas dos.(Esto se hace para despues poder reducir de una manera
                   mas sencilla las fracciones que pudieran salir como resultado.
                */

                double res1 = (auxB * -1) + (sqrt((auxB*auxB) - 4 * auxA * auxC));
                double res2 = (auxB * -1) - (sqrt((auxB*auxB) - 4 * auxA * auxC));
                double res12 = (2 * auxA);

                // Se inicializan a 0 los vectores que recogen los divisibles de los tres resultados.
                int divisibles1 [] = new int[5];
                for(int i = 0; i < 5; i++)
                {
                    divisibles1[i] = 0;
                }

                int divisibles12 [] = new int[5];
                for(int i = 0; i < 5; i++)
                {
                    divisibles12[i] = 0;
                }

                int divisibles2 [] = new int[5];
                for(int i = 0; i < 5; i++)
                {
                    divisibles2[i] = 0;
                }

                // Se inicializan estas variables a 0 ya que es necesario este valor desde el principio.
                int contador = 0;
                int divisibleMaximo1 = 0;
                int divisibleMaximo2 = 0;



                // Comprobar que es irreducible. Y si no se reduce.
                if(res1 % res12 == 0)
                {
                    tv1.setText("" + (res1 / (res12)));
                }
                else
                {
                    // Calculamos todos los divisibles del nominador del primer resultado.
                    for(int i = 2; i <= Math.abs(res1); i++)
                    {
                        if(res1 % i == 0)
                        {
                            divisibles1[contador] = i;
                            contador++;
                        }

                    }

                    // Se vuelve a inicializar en 0 el contador para no sobreescribir datos.
                    contador = 0;

                    /* Se calculan todos los divisibles del denominador, que
                     comparten ambos resultados.
                    */
                    for(int i = 2; i <= Math.abs(res12); i++)
                    {
                        if(res12 % i == 0)
                        {
                            divisibles12[contador] = i;
                            contador++;
                        }

                    }

                    /* Se comparan los arrays donde se encuentran todos los divisibles del
                    nominador del primer resultado  y del denominador que comparten ambos resultados.
                    Si algun valor de ambos vectores coincide, quiere decir que se podria simplificar
                    la fraccion del resultado. Por lo que se guarda el divisible en una variable,
                    almacenando esta el divisible maximo, para dejar mas tarde la fraccion irreducible.
                     */
                    for(int i = 0; i < divisibles1.length; i++)
                    {
                        for(int j = 0; j < divisibles12.length; j++)
                        {
                            if (divisibles1[i] == divisibles12[j])
                            {
                                if(divisibleMaximo1 < divisibles1[i])
                                {
                                    divisibleMaximo1 = divisibles1[i];
                                }

                            }
                        }
                    }

                    /* Pequeño retoque (totalmente prescindible) para que en el resultado
                       no se muestre el signo negativo en el denominador. Pero si hacemos
                       esto hay que tener en cuenta que justo despues de mostrar en pantalla
                       el primer resultado habra que retornar el valor nativo del denominador,
                       ya que se comparte con la segunda solucion y causaria un error en el signo.
                     */
                    if(res12 < 0)
                    {
                        res12 = Math.abs(res12);
                        res1 *= -1;

                        // Se muestra el resultado, ya simplificado al maximo.
                        tv1.setText("" + ((res1)/divisibleMaximo1) + "/" + ((res12)/divisibleMaximo1));

                        // Y se vuelve a retornar el verdadero valor del denominador.
                        res12 *= -1;
                    }
                    else
                    {
                        // Se muestra el resultado, ya simplificado al maximo.
                        tv1.setText("" + ((res1)/divisibleMaximo1) + "/" + ((res12)/divisibleMaximo1));
                    }

                }

                /* Se hace lo mismo que con el primer resultado pero ahora con el segundo.
                   Ademas comparten denominador, el unico cambio es que en vez de sumar en
                   la ecuacion, se resta.
                */
                if(res2 % res12 == 0)
                {
                    tv2.setText("" + (res2 / res12));
                }
                else
                {
                    contador = 0;

                    for(int i = 2; i <= Math.abs(res2); i++)
                    {
                        if(res2 % i ==0)
                        {
                            divisibles2[contador] = i;
                            contador++;
                        }

                    }

                    if (divisibles12 [0] == 0 && divisibles12[1] == 0)
                    {
                        contador = 0;
                        for(int i = 2; i <= Math.abs(res12); i++)
                        {
                            if(res12 % i == 0)
                            {
                                divisibles12[contador] = i;
                                contador++;
                            }

                        }
                    }

                    for(int i = 0; i < divisibles2.length; i++)
                    {
                        for(int j = 0; j < divisibles12.length; j++)
                        {
                            if (divisibles2[i] == divisibles12[j])
                            {
                                if(divisibleMaximo2 < divisibles2[i])
                                {
                                    divisibleMaximo2 = divisibles2[i];
                                }

                            }
                        }
                    }

                    if(res12 < 0)
                    {
                        res12 = Math.abs(res12);
                        res2 *= -1;
                    }

                    tv2.setText("" + (res2/divisibleMaximo2) + "/" + (res12/divisibleMaximo2));

                }

            }
        });


    }
}
