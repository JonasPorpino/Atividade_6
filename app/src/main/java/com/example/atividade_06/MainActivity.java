package com.example.atividade_06;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button botaoCadastro;
    private ListView lista;
    private ArrayList<Tarefas> todasTarefas = new ArrayList<>();
    private ArrayAdapter<Tarefas> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // botao
        botaoCadastro = findViewById(R.id.button);
        botaoCadastro.setOnClickListener(new OuvinteDoBotao());

        // elementos da tela
        lista = findViewById(R.id.listaView);

        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, todasTarefas);
        lista.setAdapter(adapter);

        lista.setOnItemLongClickListener(new OuvinteCliqueLongo());

    }
    private static int CADASTRAR = 1;
    private  class OuvinteDoBotao implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Tela_Segunda_cadastro.class);

            startActivityForResult(intent, CADASTRAR);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == CADASTRAR) {
                Tarefas tarefas = (Tarefas) data.getSerializableExtra("Tarefas");
                adapter.add(tarefas);
                Toast.makeText(this, "SALVO", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "CANCELADO", Toast.LENGTH_SHORT).show();
        }

    }
    private class  OuvinteCliqueLongo implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            AlertDialog.Builder alerta = new  AlertDialog.Builder(MainActivity.this);
            alerta.setMessage("Tem certeza que deseja excluir?");
            alerta.setTitle("Excluir!");
            alerta.setPositiveButton("Sim", (dialog, which) -> {
                todasTarefas.remove(position);
                adapter.notifyDataSetChanged();
            });
            alerta.setNegativeButton("Não", (dialog, which) -> dialog.cancel());
            Dialog caixaDialogo = alerta.create();
            caixaDialogo.show();
            return true;
        }
    }
}