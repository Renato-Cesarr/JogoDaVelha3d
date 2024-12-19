package com.jogodavelha3d.model;

public class Jogador {


	private String nome;
	private int qtdVitorias;
	private boolean jogaga;
	
	public Jogador(String nome, int qtdVitorias, boolean jogada) {
		this.nome = nome;
		this.qtdVitorias = qtdVitorias;
		this.jogaga = jogada;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtdVitorias() {
		return qtdVitorias;
	}

	public void setQtdVitorias(int qtdVitorias) {
		this.qtdVitorias = qtdVitorias;
	}

	public boolean isJogaga() {
		return jogaga;
	}

	public void setJogaga(boolean jogaga) {
		this.jogaga = jogaga;
	}
}
