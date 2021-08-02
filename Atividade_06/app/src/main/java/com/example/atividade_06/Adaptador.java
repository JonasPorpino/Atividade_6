package com.example.atividade_06;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// ---------Esse adaptador irá sobrescrever a listiview da activity_main

public class Adaptador extends ArrayAdapter<Tarefas> {


    public Adaptador(Context context, int resource, List<Tarefas> objects) {
            super(context, resource, objects);
    }


    @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Tarefas tarefas = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_para_lista, parent, false);
            }

            TextView tvTarefefa = convertView.findViewById(R.id.textoTarefa);
            TextView tvData = convertView.findViewById(R.id.textoData);

            // setar as tarefas e datas no listeview
            tvTarefefa.setText(tarefas.getTarefa());
            tvData.setText(tarefas.getDataFormatada());

            // comparar a data atual com a data recebida
            String dataa = tvData.getText().toString();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date now = new Date(System.currentTimeMillis());
            Date data = null;
            try {
                data = formatter.parse(dataa);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer r = now.compareTo(data);
                Integer retorno = r;

                if(retorno > 0){
                    tvData.setTextColor(Color.RED);
                }else
                    if (retorno < 0){
                        tvData.setTextColor(Color.GREEN);
                    }

            return convertView;
        }


}
