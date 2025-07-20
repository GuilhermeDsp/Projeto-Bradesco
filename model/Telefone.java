/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Telefone {
    private int id;
    private String numero;
    private TipoTelefone status;
    private String descricao;

    public Telefone(String numero, TipoTelefone status) {
        this.numero = numero;
        this.status = status;
    }

    public Telefone(String numero, TipoTelefone status, String descricao) {
        this.numero = numero;
        this.status = status;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getStatus() {
        return status;
    }
    public void setStatus(TipoTelefone status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}