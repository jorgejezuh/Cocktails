package com.jorge.cocktails.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class Main {
	
	
	public static void main(String[] args) {
		
		int opcion = 0;
		
		Scanner sc;
		sc = new Scanner(System.in);
		
		do {
			System.out.print("\n");
			System.out.println("****Menu de Cocktails****");
			System.out.println("1. Listar Bebidas");
			System.out.println("2. Buscar una Bebida concreta");
			System.out.println("3. Introducir Bebida");
			System.out.println("4. Actualizar Bebida por ID");
			System.out.println("5. Eliminar Bebida");
			System.out.println("0. Salir\n");
			
			opcion = readNumber("Introduzca una opción válida: ", sc);
			
			Bebida bebida = new Bebida();
			
			System.out.println("\n");
			
			if(opcion < 0 || opcion > 5) {
				System.out.println("Opción no válida. Por favor vuelva a elegir.");
			}else if(opcion != 0) {
				switch(opcion) {
					
				case 1: //Listar todas las bebidas
					
					System.out.println("Listar todas las bebidas\n");
					imprimirBebida(allOfBebida());
					break;
					
				case 2: //Buscar una bebida concreta
					
					System.out.println("Buscar una bebida concreta\n");
					
					imprimirBebida(new Bebida[] {
							buscarBebida(readNumber("Introduce el ID de la bebida: ", sc))
					});
					
					break;
					
				case 3: //Introducir una bebida
					
					System.out.println("Introducir una bebida");
					
					int id_mostrar_bebida = insertBebida(pedirDatos(sc));
					
					if(id_mostrar_bebida != 0) {
						System.out.println("Bebida creada con éxito.\n");
						imprimirBebida(new Bebida[] {buscarBebida(id_mostrar_bebida)});
					}else {
						System.out.println("Error creando la bebida!!\n");
					}
					
					break;
					
				case 4: //Actualizar bebida por ID
					System.out.println("Actualizar bebida por ID\n");
					
					bebida = buscarBebida(readNumber("Introduce el ID de la bebida: ", sc));
					
					if(bebida != null) {
						imprimirBebida(new Bebida[] {bebida});
						
						if(updateBebida(bebida.getId(), pedirDatos(sc))) {
							System.out.println("La bebida se ha actualizado correctamente.\n");
						}else {
							System.out.println("La bebida NO se ha actualizado correctamente.\n");
						}
					}else {
						System.out.println("Esa bebida no existe en la base de datos.");
					}
					
					
					break;
				
				case 5: //Eliminar bebida
					
					System.out.println("Elimnar bebida");
					
					if(deleteBebida(buscarBebida(readNumber("Introduce el ID de la bebida: ", sc)))){
						System.out.println("Bebida eliminada correctamente");
					}else {
						System.out.println("La bebida NO ha sido eliminada porque no existe.");
					}
					
					
					break;
				}
				
			}
			
		}while(opcion != 0);
		
		sc.close();
		
		System.out.println("Has finalizado la ejecución del programa.");
	}
	
	//Métodos CRUD

	public static int insertBebida(Bebida bebida) {
		String sql = "INSERT INTO bebidas (nombre, tipo) VALUES (?,?)";
		int id = 0;
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			conn = new DBConnection().connection();
			statement = conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
			statement.setString(1, bebida.getNombre());
			statement.setString(2, bebida.getTipo());
			
			statement.executeUpdate();
			
			rs = statement.getGeneratedKeys();
			rs.next();
			
			id = rs.getInt(1);
		} catch (SQLException ex) {
			System.out.println("Error " + ex);
		}finally {
			if(!cerrarConexBD(conn, statement, rs)) {
				System.out.println("ERROR cerrando la conexión con la base de datos.");
			}
		}
		
		return id;
	}

	public int insertIngrediente(Ingrediente ingrediente) {
		// TODO Auto-generated method stub
		return 0;
	}


	public int insertCocktail(Cocktail cocktail) {
		// TODO Auto-generated method stub
		return 0;
	}


	public static boolean updateBebida(int id, Bebida bebida) {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE bebidas SET nombre=?, tipo=? WHERE id=?";
		int rowsUpdated = 0;
		Connection conn = null;
		PreparedStatement statement = null;
		
		try {
			conn = new DBConnection().connection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, bebida.getNombre());
			statement.setString(2, bebida.getTipo());
			statement.setInt(3, id);
			rowsUpdated = statement.executeUpdate();
			
		}catch (SQLException ex){
			System.out.println("ERROR: " + ex);
		}finally {
			if(!cerrarConexBD(conn, statement)) {
				System.out.println("ERROR cerrando la conexión con la base de datos.");
			}
		}
		
		//Es lo mismo que lo de arriba
		return rowsUpdated > 0;
		
	}


	public boolean updateIngrediente(Ingrediente ingrediente) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateCocktail(Cocktail cocktail) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public static boolean deleteBebida(Bebida bebida) {
		// TODO Auto-generated method stub
		
		String sql = "DELETE FROM bebidas WHERE id=?";
		int rowsDeleted = 0;
		Connection conn = null;
		PreparedStatement statement = null;
		
		try {
			conn = new DBConnection().connection();
			statement = conn.prepareStatement(sql);
			statement.setInt(1, bebida.getId());
			
			rowsDeleted = statement.executeUpdate();
		} catch(SQLException ex) {
			
		}finally {
			if(!cerrarConexBD(conn, statement)) {
				System.out.println("ERROR cerrando la conexión con la base de datos.");
			}else System.out.println("Conexión cerrada\n");
		}
		
		return rowsDeleted > 0;
	}

	
	public boolean deleteIngrediente(Ingrediente ingrediente) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean deleteCocktail(Cocktail cocktail) {
		// TODO Auto-generated method stub
		return false;
	}

	public static Bebida[] allOfBebida() {
		
		String sql = "SELECT * FROM bebidas";
		
		List<Bebida> bebidas = new ArrayList<Bebida>();
		Connection conn = null;
		Statement statement = null;
		ResultSet result = null;
		
		
		try {
			conn = new DBConnection().connection();
			statement = conn.createStatement();
			result = statement.executeQuery(sql);
			
			while(result.next()) {
				Bebida bebida = new Bebida(result.getInt(1), result.getString(2), result.getString(3));
				
				bebidas.add(bebida);
			}
			
		} catch (SQLException ex) {
			
		}finally {
			if(!cerrarConexBD(conn, statement)) {
				System.out.println("ERROR cerrando la conexión con la base de datos.");
			}
		}
		
		Bebida[] bebidas_array = new Bebida[bebidas.size()];
		bebidas_array = bebidas.toArray(bebidas_array);
		
		return bebidas_array;
	}
	
	public static Bebida buscarBebida(int id) {
		String sql = "SELECT * FROM bebidas";
		Bebida bebida = null;
		
		Connection conn = null;
		Statement statement = null;
		ResultSet result = null;
		
		try {
			conn = new DBConnection().connection();
			statement = conn.createStatement();
			result = statement.executeQuery(sql);
			
			while(result.next()) {
				if(result.getInt(1) == id) {
					bebida = new Bebida (result.getInt(1), result.getString(2), result.getString(3));
				}
			}
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex);
		}finally {
			if(!cerrarConexBD(conn, statement)) {
				System.out.println("ERROR cerrando la conexión con la base de datos.");
			}
		}
		
		return bebida;
	}
	
	private static int readNumber(String cadena, Scanner sc) {
		int num = 0;
		do {
			System.out.print(cadena);
			try {
				num = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Introduce un número pedaso de animal :(\n");
			}
		} while(num < 1);
		
		return num;
	}
	
	private static void imprimirBebida(Bebida[] bebida) {
		System.out.printf("%s%20s%20s %n", "ID", "NOMBRE", "TIPO");
		for(int i=0; i<bebida.length; i++) {
			System.out.printf("%s %20s %20s %n", bebida[i].getId(), bebida[i].getNombre(), bebida[i].getTipo());
		}
	}
	
	private static Bebida pedirDatos(Scanner sc) {
		String nombre, tipo;
		
		System.out.println("Introduce el nombre de la bebida: ");
		nombre = sc.nextLine();
		
		System.out.println("Introduce el tipo de la bebida: ");
		tipo = sc.nextLine();
		
		return new Bebida(nombre, tipo);
	}
	
	private static boolean cerrarConexBD(Connection conn, Statement statement, ResultSet rs) {
		
		try {
			try {
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(statement != null) statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	
private static boolean cerrarConexBD(Connection conn, Statement statement) {
		
		try {
			try {
				if(statement != null) statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

}
