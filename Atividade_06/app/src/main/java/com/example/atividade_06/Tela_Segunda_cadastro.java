package com.example.atividade_06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.DateFormat;
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
            Date data = null;

            String tarefa = atividade.getText().toString();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            // essa linha foi para testes
            try {
                data = formatter.parse("11/12/2021");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // formatando a data
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = dateFormat.format(data);

            Tarefas tarefas = new Tarefas(tarefa,dataFormatada);
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