package com.example.atividade_06;


import java.io.Serializable;
import java.util.Date;


public class Tarefas implements Serializable {

    private String tarefa;
    private Date data;


    public Tarefas(){
        
    }

    public Tarefas(String tarefa, Date data) {
            this.tarefa = tarefa;
            this.data = data;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa, Date data) {
        this.tarefa = tarefa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return "TAREFA "+tarefa+" || data: "+data;
    }


}
