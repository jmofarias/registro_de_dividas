package br.ufal.sd.entities;

public class Entidade {
    private long id;
    private String nome;
    private float valor;
    private String status;

    public Entidade() {

    }

    public Entidade(long id, String nome, float valor, String status) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.status = status;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public String getNome() {return nome;}
    public float getValor() {return valor;}
    public String getStatus() {return status;}
    public void setNome(String nome) {this.nome = nome;}
    public void setValor(float valor) {this.valor = valor;}
    public void setStatus(String status) {this.status = status;}

}