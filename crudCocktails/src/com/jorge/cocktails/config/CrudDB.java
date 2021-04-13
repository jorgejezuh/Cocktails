package com.jorge.cocktails.config;

import java.util.List;

public interface CrudDB{
	
	
	int insertBebida(Bebida bebida);
	int insertIngrediente(Ingrediente ingrediente);
	int insertCocktail(Cocktail cocktail);
	
	boolean updateBebida(Bebida bebida);
	boolean updateIngrediente(Ingrediente ingrediente);
	boolean updateCocktail(Cocktail cocktail);
	
	boolean deleteBebida(Bebida bebida);
	boolean deleteIngrediente(Ingrediente ingrediente);
	boolean deleteCocktail(Cocktail cocktail);
	
	List<Bebida> allOfBebida();
}
