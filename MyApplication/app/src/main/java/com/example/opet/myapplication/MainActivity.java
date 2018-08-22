package com.example.opet.myapplication;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void carregarImoveis(View view){

        Resources res = getResources();

        InputStream is = res.openRawResource(R.raw.imobiliaria);

        Scanner scanner = new Scanner(is);

        StringBuilder builder = new StringBuilder();

        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }

        parseJson(builder.toString());

    }

    private void parseJson(String s) {

        TextView txtDisplay = (TextView) findViewById(R.id.text_display);

        StringBuilder builder = new StringBuilder();

        try {
            JSONObject root = new JSONObject(s);

            JSONObject imobiliaria = root.getJSONObject("imoveis-imobiliaria");

            JSONArray imoveis = imobiliaria.getJSONArray("imoveis");

            for (int i = 0; i < imoveis.length(); i++){
                JSONObject imovel = imoveis.getJSONObject(i);
                builder.append(imovel.getString("id"))
                        .append(": ")
                        .append("\n")
                        .append(imovel.getString("categoria"))
                        .append(": ")
                        .append("\n")
                        .append(imovel.getString("cidade"))
                        .append("\n");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        txtDisplay.setText(builder.toString());
    }

}
