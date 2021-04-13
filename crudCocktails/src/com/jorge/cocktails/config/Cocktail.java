package com.jorge.cocktails.config;

public class Cocktail {
	private int id;
	private String nombre, instrucciones, observaciones;
	
	//constructors
	public Cocktail() {
		super();
	}
	
	public Cocktail(String nombre, String instrucciones, String observaciones) {
		super();
		this.nombre = nombre;
		this.instrucciones = instrucciones;
		this.observaciones = observaciones;
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

	public String getInstrucciones() {
		return instrucciones;
	}

	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public String toString() {
		return "Cocktail [id=" + id + ", nombre=" + nombre + ", instrucciones=" + instrucciones + ", observaciones="
				+ observaciones + "]";
	}
	
	
	
	
}
