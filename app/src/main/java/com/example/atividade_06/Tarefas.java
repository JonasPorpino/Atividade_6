package com.example.atividade_06;

import java.io.Serializable;

public class Tarefas implements Serializable {

    private String tarefa;
    private String data;

    public  Tarefas(){

    }

    public Tarefas(String tarefa, String data) {
        this.tarefa = tarefa;
        this.data = data;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTarefa() {
        return tarefa;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Tarefa: "+tarefa + " data: " + data;
    }
}
