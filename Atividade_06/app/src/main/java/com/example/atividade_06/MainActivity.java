package com.example.atividade_06;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private Button botaoCadastro;
    private ListView lista;
    private EditText atividade;
    private EditText datas;
    private Button btSalvar;
    private Button btSair;
    private ArrayList<Tarefas> todasTarefas = new ArrayList<>();
    private ArrayAdapter<Tarefas> adapter;
    private View view;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // botao
        botaoCadastro = findViewById(R.id.button);
        botaoCadastro.setOnClickListener(new OuvinteBotaoMesmaTela());

        // elementos da tela
        lista = findViewById(R.id.listaView);
        
        adapter = new Adaptador(MainActivity.this, android.R.layout.simple_list_item_1, todasTarefas);
        //adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, todasTarefas);
        lista.setAdapter(adapter);

        lista.setOnItemLongClickListener(new OuvinteCliqueLongo());

    }
    private static int CADASTRAR = 1;

    //---------------------------janela alertDialog da segunda tela ---------------------

    private class OuvinteBotaoMesmaTela implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            AlertDialog.Builder caixa = new  AlertDialog.Builder(MainActivity.this);
            LayoutInflater inflater = getLayoutInflater();
            view = inflater.inflate(R.layout.activity_tela_segunda_cadastro, null);
            caixa.setTitle("Nova Tarefa");
            caixa.setView(view);

            //botoes e EtidTexts
            btSalvar = view.findViewById(R.id.button3);
            btSair = view.findViewById(R.id.button2);
            atividade = view.findViewById(R.id.editTextTextPersonName);
            datas = view.findViewById(R.id.editTextTextPersonName2);
            final Dialog dialog = caixa.create();
            btSalvar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    EditText atividade = view.findViewById(R.id.editTextTextPersonName);
                    EditText datas = view.findViewById(R.id.editTextTextPersonName2);
                    Date data = null;

                    String tarefa = atividade.getText().toString();
                    String dataa = datas.getText().toString();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                    try {
                        data = formatter.parse(dataa);
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String dataFormatada = dateFormat.format(data);

                        Tarefas tarefas = new Tarefas(tarefa,dataFormatada);

                        adapter.add(tarefas);
                        Toast.makeText(MainActivity.this, "SALVO" , Toast.LENGTH_SHORT).show();
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Data INVALIDA" , Toast.LENGTH_SHORT).show();
                    }
                    dialog.cancel();
                }

            });


            btSair.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "CANCELADO", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });
            dialog.show(); }
    }

    //-------------------chamada para a outra janela ------------------------
    // ---- essa parte também foi para testes
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

    //---- aqui é feita exclusão do itens nas listas
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
