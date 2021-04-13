package com.jorge.cocktails.config;

public class Bebida {
	private int id;
	private String nombre, tipo;
	
	//constructors
	public Bebida() {}
	
	public Bebida(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public Bebida(int id, String nombre, String tipo) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Bebida [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + "]";
	}
	
}
