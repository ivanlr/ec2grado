package com.ivanlr.ivanlr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Math.sqrt;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2, et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.etA);
        et2 = (EditText) findViewById(R.id.etB);
        et3 = (EditText) findViewById(R.id.etC);

        Button btnCalcular = (Button) findViewById(R.id.btnCalcular);
        final TextView tv1 = (TextView) findViewById(R.id.tvResultado1);
        final TextView tv2 = (TextView) findViewById(R.id.tvResultado2);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double auxA = Double.valueOf(et1.getText().toString());
                double auxB = Double.valueOf(et2.getText().toString());
                double auxC = Double.valueOf(et3.getText().toString());
                double res1 = ((auxB * -1) + (sqrt((auxB*auxB) - 4 * auxA * auxC))) / (2*auxA);
                double res2 = ((auxB * -1) - (sqrt((auxB*auxB) - 4 * auxA * auxC))) / (2*auxA);

                tv1.setText("" + res1);
                tv2.setText("" + res2);


            }
        });


    }
}
