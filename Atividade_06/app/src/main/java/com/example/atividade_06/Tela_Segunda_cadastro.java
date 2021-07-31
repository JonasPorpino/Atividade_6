package com.example.atividade_06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Tela_Segunda_cadastro extends AppCompatActivity {
    private Button btSalvar;
    private Button btSair;
    private EditText atividade;
    private EditText datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_segunda_cadastro);


        //botoes
        btSalvar = findViewById(R.id.button3);
        btSalvar.setOnClickListener(new Ouvinte_botao_Salvar());
        btSair = findViewById(R.id.button2);
        btSair.setOnClickListener(new Ouvinte_botao_Sair());
        // textos
        atividade = findViewById(R.id.editTextTextPersonName);
        datas = findViewById(R.id.editTextTextPersonName2);

    }

    private class Ouvinte_botao_Salvar implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String tarefa = atividade.getText().toString();
            String data = datas.getText().toString();

            Tarefas tarefas = new Tarefas(tarefa,data);
            Intent intent = new Intent();
            intent.putExtra("Tarefas", tarefas);

            setResult(RESULT_OK, intent);
            finish();


        }
    }
    private class Ouvinte_botao_Sair implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}