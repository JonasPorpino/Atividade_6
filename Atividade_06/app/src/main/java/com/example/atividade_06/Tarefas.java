package com.example.atividade_06;


import java.io.Serializable;
import java.util.Date;


public class Tarefas implements Serializable {

    private String tarefa;
    private Date data;
    private String dataFormatada;


    public Tarefas(){

    }

    public Tarefas(String tarefa, String dataFormatada) {
        this.tarefa = tarefa;
        this.dataFormatada = dataFormatada;
    }


    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDataFormatada() {
        return dataFormatada;
    }

    public void setDataFormatada(String dataFormatada) {
        this.dataFormatada = dataFormatada;
    }



    @Override
    public String toString() {
        return  tarefa + dataFormatada;
    }
}