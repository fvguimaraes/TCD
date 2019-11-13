package br.com.fiap69aoj.chamados.model;

public enum StatusEnum {

	ABERTO("aberto"),
    ANALISE("analise"),
    FECHADO("fechado");
 
    private String descricao;
 
    StatusEnum(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }
}
