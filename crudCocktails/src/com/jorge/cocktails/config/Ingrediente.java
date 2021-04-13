package com.jorge.cocktails.config;

public class Ingrediente {
	private int id;
	private String nombre;
	
	//constructors
	public Ingrediente() {}
	
	public Ingrediente(String nombre) {
		this.nombre = nombre;
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

	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
	
}
