import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



public class Cliente {
	
	static /*
	 * Variables de clase.
	 */
	Connection conexion = null;
	static Scanner sc = new Scanner(System.in);

	
public static int menu(){
	System.out.println(">Seleccione una prueba:");
	System.out.println("\t1.  El usuario se registra en el sistema");
	System.out.println("\t2.  El usuario inicia sesion en el sistema");
	System.out.println("\t3.  Consultar los espectaculos disponibles por parte del usuario");
	System.out.println("\t4.  Consultar los espectaculos en los que se permite la entrada a bebes por parte del usuario");
	System.out.println("\t5.  Consultar la lista participanes de un espectaculo por parte del usuario");
	System.out.println("\t6.  Consultar los eventos de un espectaculo por parte del usuario");
	System.out.println("\t7.  Consultar toda la lista de espectaculos por parte del usuario");
	System.out.println("\t8.  Consultar las gradas de un evento por parte del usuario");
	System.out.println("\t9.  Consultar los precios de una grada por parte del usuario");
	System.out.println("\t10. Consultar los eventos de un espectaculo por parte del usuario");
	System.out.println("\t11. El usuario prereserva una localidad");
	System.out.println("\t12. El usuario anula una localidad");
	System.out.println("\t13. El usuario consulta sus compras");
	System.out.println("\t14. El usuario compra una localidad");

	System.out.println("\t15. El usuario realiza una consulta cuyo resultado da un elemento vacio");
	System.out.println("\t16. El tiempo de pre-reserva expira");
	System.out.println("\t17. Se anula una reserva pasado el tiempo de penalizacion");
	System.out.println("\t18. Acaba un evento");
	System.out.println("\t19. Salir");
	int opt = Integer.parseInt(sc.nextLine());
	return opt;
	
}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		/*
		 * Establece la conexión a la base de datos.
		 */
		while(true){
			conectar();
			
			int opt=menu();
			
			switch(opt){
			case 1:
				prueba1();
				break;
				
			case 2:
				prueba2();
				break;
				
			case 3:	
				prueba3();
				break;
				
			case 4:
				prueba4();
				break;
				
			case 5:	
				prueba5();
				break;
				
			case 6:	
				prueba6();
				break;
				
			case 7:	
				prueba7();
				break;
				
			case 8:	
				prueba8();
				break;
				
			case 9:	
				prueba9();
				break;
				
			case 10:	
				prueba10();
				break;
				
			case 11:	
				prueba11();
				break;
				
			case 12:	
				prueba12();
				break;
				
			case 13:	
				prueba13();
				break;
			case 14:
				prueba14();
				break;
				
			case 15:	
				prueba14();
				break;
				
			case 16:	
				prueba16();
				break;
				
			case 17:	
				prueba17();
				break;
				
			case 18:	
				//prueba18();
				break;
				
			case 19:
				System.out.println("*********SALIENDO*********");
				System.exit(0);
				
			default:	
				System.out.println("Introduzca una opción valida");
				break;
				
			}
			
			System.out.println();
		}
		
	}

	
	public static void prueba() throws SQLException {

		Statement s = conexion.createStatement();
		    ResultSet result = s.executeQuery("SELECT * FROM Cliente;");
		    writeResultSet(result);
		
	}
	public static void conectar() throws ClassNotFoundException, SQLException{
		String url ="jdbc:mysql://localhost/taquilla_virtual"; 
		
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(url, "root", "markoscl");
		
	}
	public static void prueba10() throws SQLException{
		System.out.println(">Introduzca el ID del espectaculo");
		int espectaculo=Integer.parseInt(sc.nextLine());
		
		
		CallableStatement cst = conexion.prepareCall("{ call eventosEspectaculo (?)}");
		cst.setInt(1,espectaculo);		
		    writeResultSet(cst.executeQuery());
		    
		    
	}
	public static void prueba1() throws ClassNotFoundException {
		
			try{
				
				System.out.println(">Introduzca el nombre");
				String nombre=sc.nextLine();
				System.out.println(">Introduzca la direccion");
				String direccion=sc.nextLine();
				System.out.println(">Introduzca la contraseña");
				String contrasena=sc.nextLine();
				System.out.println(">Introduzca el metodo de pago");
				String pago=sc.nextLine();
				
				CallableStatement cst = conexion.prepareCall("{ call signup (?,?,?,?)}");
				cst.setString(1,nombre);
				cst.setString(2,direccion);
				cst.setString(3,contrasena);
				cst.setString(4,pago);
				cst.execute();
				
				Statement s = conexion.createStatement();
     		    ResultSet result = s.executeQuery("SELECT * FROM Cliente;");
     		    writeResultSet(result);
     		    
     		    

			} catch (SQLException e) {
			  System.err.print("Error: No existe o no se pudo gestionar la BD");
			  e.printStackTrace(); 
			} finally { 
				try { 
					conexion.close(); 
				} catch (SQLException e) {
					System.err.print("Error: No se pudo cerrar la conexion"); 
				} catch (Exception e) { 
					e.printStackTrace(); 
				}	
			} 
	}

	public static void prueba2() throws ClassNotFoundException{
		
		try { 
			
			System.out.println(">Introduzca el ID");
			int id=Integer.parseInt(sc.nextLine());
			System.out.println(">Introduzca la contraseña");
			String contrasena=sc.nextLine();
			
			CallableStatement cst = conexion.prepareCall("{ call login (?,?)}");
			cst.setInt(1,id);
			cst.setString(2,contrasena);
			ResultSet result = cst.executeQuery();
			writeResultSet(result);
			
			
		
		} catch (SQLException e) {
		  System.err.print("Error: No existe o no se pudo gestionar la BD");
		  e.printStackTrace(); 
		} finally { 
			try { 
				conexion.close(); 
			} catch (SQLException e) {
				System.err.print("Error: No se pudo cerrar la conexion"); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			}	
		} 
	}

	public static void prueba3() throws ClassNotFoundException{
		
		try { 
			CallableStatement cst = conexion.prepareCall("{call mostrarEspectaculos}");
			ResultSet result = cst.executeQuery();
			writeResultSet(result);
		
		} catch (SQLException e) {
		  System.err.print("Error: No existe o no se pudo gestionar la BD");
		  e.printStackTrace(); 
		} finally { 
			try { 
				conexion.close(); 
			} catch (SQLException e) {
				System.err.print("Error: No se pudo cerrar la conexion"); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			}	
		} 
	}

	public static void prueba4() throws ClassNotFoundException{
		
		try { 
			CallableStatement cst = conexion.prepareCall("{call mostrarBebes}");
			ResultSet result = cst.executeQuery();
			writeResultSet(result);
		
		} catch (SQLException e) {
		  System.err.print("Error: No existe o no se pudo gestionar la BD");
		  e.printStackTrace(); 
		} finally { 
			try { 
				conexion.close(); 
			} catch (SQLException e) {
				System.err.print("Error: No se pudo cerrar la conexion"); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			}	
		} 
	}

	public static void prueba5() throws ClassNotFoundException{
		 
		try { 
		
			System.out.println(">Introduzca ID de Espectaculo");
			int id=Integer.parseInt(sc.nextLine());
			CallableStatement cst = conexion.prepareCall("{call participantesEspectaculos(?)}");
			cst.setInt(1, id);
			ResultSet result = cst.executeQuery();
			writeResultSet(result);
			
		
		} catch (SQLException e) {
		  System.err.print("Error: No existe o no se pudo gestionar la BD");
		  e.printStackTrace(); 
		} finally { 
			try { 
				conexion.close(); 
			} catch (SQLException e) {
				System.err.print("Error: No se pudo cerrar la conexion"); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			}	
		} 
	}

	public static void prueba6() throws ClassNotFoundException{
		 
		try { 
		
			System.out.println(">Introduzca ID de Espectaculo");
			int id=Integer.parseInt(sc.nextLine());
			CallableStatement cst = conexion.prepareCall("{call eventosEspectaculo(?)}");
			cst.setInt(1, id);
			ResultSet result = cst.executeQuery();
			writeResultSet(result);
			
		
		} catch (SQLException e) {
		  System.err.print("Error: No existe o no se pudo gestionar la BD");
		  e.printStackTrace(); 
		} finally { 
			try { 
				conexion.close(); 
			} catch (SQLException e) {
				System.err.print("Error: No se pudo cerrar la conexion"); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			}	
		} 
	}

	//TODO esto es de administrador
	public static void prueba7() throws ClassNotFoundException, SQLException{
		Statement s = conexion.createStatement();
	    ResultSet result = s.executeQuery("SELECT * FROM Espectaculos;");
	    writeResultSet(result);
		
	}

	public static void prueba8() throws ClassNotFoundException{
		
		try { 
		
			
			System.out.println(">Introduzca ID de Evento");
			int id=Integer.parseInt(sc.nextLine());
			CallableStatement cst = conexion.prepareCall("{call mostrarGradas(?)}");
			cst.setInt(1, id);
			ResultSet result = cst.executeQuery();
			writeResultSet(result);
			
		
		} catch (SQLException e) {
		  System.err.print("Error: No existe o no se pudo gestionar la BD");
		  e.printStackTrace(); 
		} finally { 
			try { 
				conexion.close(); 
			} catch (SQLException e) {
				System.err.print("Error: No se pudo cerrar la conexion"); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			}	
		} 
	}

	public static void prueba9() throws ClassNotFoundException{
		
		try { 
		
			
			System.out.println(">Introduzca ID de Evento");
			int idE=Integer.parseInt(sc.nextLine());
			System.out.println(">Introduzca ID de Grada");
			int idG=Integer.parseInt(sc.nextLine());
			CallableStatement cst = conexion.prepareCall("{call mostrarPrecioGrada(?,?)}");
			cst.setInt(1, idG);
			cst.setInt(2, idE);
			ResultSet result = cst.executeQuery();
			writeResultSet(result);
			
		
		} catch (SQLException e) {
		  System.err.print("Error: No existe o no se pudo gestionar la BD");
		  e.printStackTrace(); 
		} finally { 
			try { 
				conexion.close(); 
			} catch (SQLException e) {
				System.err.print("Error: No se pudo cerrar la conexion"); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			}	
		} 
	}


	public static void prueba11() throws ClassNotFoundException{
		
		try { 
			
			
			
			System.out.println(">Introduzca ID de Evento");
			int evento=Integer.parseInt(sc.nextLine());
			
			Statement s = conexion.createStatement();
			 ResultSet result = s.executeQuery("select FK_ID_localidad as 'ID_localidad',estado,FK_ID_evento as 'ID_evento' from estado_localidad where FK_ID_evento = "+evento+" AND estado='libre' LIMIT 500;");
			   writeResultSet(result);		
			
			System.out.println(">Introduzca ID de Localidad");
			int localidad=Integer.parseInt(sc.nextLine());
			System.out.println(">Introduzca ID de Usuario");
			int usuario=Integer.parseInt(sc.nextLine());
			CallableStatement cst = conexion.prepareCall("{call reservalocalidad(?,?,?)}");
			cst.setInt(1, usuario);
			cst.setInt(2, evento);
			cst.setInt(3, localidad);
			ResultSet result2 = cst.executeQuery();
			
			
			Statement stat = conexion.createStatement();
			 ResultSet result3 = stat.executeQuery("select * from Venta;");
			   writeResultSet(result3);
			
		
		} catch (SQLException e) {
		  System.err.print("Error: No existe o no se pudo gestionar la BD");
		  e.printStackTrace(); 
		} finally { 
			try { 
				conexion.close(); 
			} catch (SQLException e) {
				System.err.print("Error: No se pudo cerrar la conexion"); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			}	
		} 
	}

	public static void prueba12() throws ClassNotFoundException{
		
		try { 
		
			System.out.println(">Introduzca ID de Usuario");
			int idU=Integer.parseInt(sc.nextLine());
			CallableStatement cs = conexion.prepareCall("{call consultarcompras(?)}");
			cs.setInt(1, idU);
			ResultSet resultset = cs.executeQuery();
			writeResultSet(resultset);
			
			
			System.out.println(">Introduzca ID de Evento");
			int idE=Integer.parseInt(sc.nextLine());
			System.out.println(">Introduzca ID de Localidad");
			int idL=Integer.parseInt(sc.nextLine());
			
			CallableStatement cst = conexion.prepareCall("{call anulacionLocalidad(?,?,?)}");
			cst.setInt(1, idE);
			cst.setInt(2, idL);
			cst.setInt(3, idU);
			ResultSet result = cst.executeQuery();
			writeResultSet(result);
			Statement s = conexion.createStatement();
			ResultSet result2 = s.executeQuery("SELECT * FROM Venta;");
		    writeResultSet(result2);
			
		
		} catch (SQLException e) {
		  System.err.print("Error: No existe o no se pudo gestionar la BD");
		  e.printStackTrace(); 
		} finally { 
			try { 
				conexion.close(); 
			} catch (SQLException e) {
				System.err.print("Error: No se pudo cerrar la conexion"); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			}	
		} 
	}

	public static void prueba13() throws ClassNotFoundException{
		
		try { 
		
			
			System.out.println(">Introduzca ID de Usuario");
			int idU=Integer.parseInt(sc.nextLine());
			CallableStatement cst = conexion.prepareCall("{call consultarcompras(?)}");
			cst.setInt(1, idU);
			ResultSet result = cst.executeQuery();
			writeResultSet(result);
			
		
		} catch (SQLException e) {
		  System.err.print("Error: No existe o no se pudo gestionar la BD");
		  e.printStackTrace(); 
		} finally { 
			try { 
				conexion.close(); 
			} catch (SQLException e) {
				System.err.print("Error: No se pudo cerrar la conexion"); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			}	
		} 
	}

	
	
	public static void prueba16() throws ClassNotFoundException{
		
		try { 

			System.out.println(">Introduzca ID de Evento");
			int evento=Integer.parseInt(sc.nextLine());
			
			Statement s = conexion.createStatement();
			 ResultSet result = s.executeQuery("select FK_ID_localidad as 'ID_localidad',estado,FK_ID_evento as 'ID_evento' from estado_localidad where FK_ID_evento = "+evento+" AND estado='libre' LIMIT 500;");
			   writeResultSet(result);		
			
			System.out.println(">Introduzca ID de Localidad");
			int localidad=Integer.parseInt(sc.nextLine());
			System.out.println(">Introduzca ID de Usuario");
			int usuario=Integer.parseInt(sc.nextLine());
			CallableStatement cst = conexion.prepareCall("{call reservalocalidad(?,?,?)}");
			cst.setInt(1, usuario);
			cst.setInt(2, evento);
			cst.setInt(3, localidad);
			ResultSet result2 = cst.executeQuery();
			
			
			
			
			System.out.println("La localidad ha sido pre-reservada.");
			Statement stat = conexion.createStatement();
			 ResultSet result4 = stat.executeQuery("select * from Venta;");
			   writeResultSet(result4);
			   
			 
		} catch (SQLException e) {
		  System.err.print("Error: No existe o no se pudo gestionar la BD");
		  e.printStackTrace(); 
		} finally { 
			try { 
				conexion.close(); 
			} catch (SQLException e) {
				System.err.print("Error: No se pudo cerrar la conexion"); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			}	
		} 
	}
public static void prueba14() throws ClassNotFoundException{
		
		try { 

			System.out.println(">Introduzca ID de Evento");
			int evento=Integer.parseInt(sc.nextLine());
			
			Statement s = conexion.createStatement();
			 ResultSet result = s.executeQuery("select FK_ID_localidad as 'ID_localidad',estado,FK_ID_evento as 'ID_evento' from estado_localidad where FK_ID_evento = "+evento+" AND estado='libre' LIMIT 500;");
			   writeResultSet(result);		
			
			System.out.println(">Introduzca ID de Localidad");
			int localidad=Integer.parseInt(sc.nextLine());
			System.out.println(">Introduzca ID de Usuario");
			int usuario=Integer.parseInt(sc.nextLine());
			CallableStatement cstt = conexion.prepareCall("{call reservalocalidad(?,?,?)}");
			cstt.setInt(1, usuario);
			cstt.setInt(2, evento);
			cstt.setInt(3, localidad);
			ResultSet result22 = cstt.executeQuery();
			CallableStatement cst = conexion.prepareCall("{call compralocalidad(?,?,?)}");
			cst.setInt(1, usuario);
			cst.setInt(2, evento);
			cst.setInt(3, localidad);
			ResultSet result2 = cst.executeQuery();
			
			
			
			
			System.out.println("La localidad ha sido comprada.");
			Statement stat = conexion.createStatement();
			 ResultSet result4 = stat.executeQuery("select * from Venta;");
			   writeResultSet(result4);
			   
			 
		} catch (SQLException e) {
		  System.err.print("Error: No existe o no se pudo gestionar la BD");
		  e.printStackTrace(); 
		} finally { 
			try { 
				conexion.close(); 
			} catch (SQLException e) {
				System.err.print("Error: No se pudo cerrar la conexion"); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			}	
		} 
	}

	public static void prueba17() throws ClassNotFoundException{
		
		try { 
			System.out.println(">Introduzca ID de Usuario");
			int idU=Integer.parseInt(sc.nextLine());
			CallableStatement cst = conexion.prepareCall("{call consultarcompras(?)}");
			cst.setInt(1, idU);
			ResultSet result = cst.executeQuery();
			writeResultSet(result);
		
			
			System.out.println(">Introduzca ID de Evento");
			int idE=Integer.parseInt(sc.nextLine());
			System.out.println(">Introduzca ID de Localidad");
			int idL=Integer.parseInt(sc.nextLine());
			
			CallableStatement cstt = conexion.prepareCall("{call anulacionLocalidad(?,?,?)}");
			cstt.setInt(1, idL);
			cstt.setInt(2, idE);
			cstt.setInt(3, idU);
			ResultSet resultt = cstt.executeQuery();
			writeResultSet(resultt);
			
			
		
		} catch (SQLException e) {
		  System.err.print("Error: No existe o no se pudo gestionar la BD");
		  e.printStackTrace(); 
		} finally { 
			try { 
				conexion.close(); 
			} catch (SQLException e) {
				System.err.print("Error: No se pudo cerrar la conexion"); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			}	
		} 
	}

	
	
	
	
	public static void writeResultSetMejorado(ResultSet resultSet) throws SQLException {
		/*
		 * El ancho debe ser adquirido por columa, para esto debemos de recorrer primero el título de esta
		 * columna, que será el primer valor a comprobar!, después de esto recorreremos cada una de las filas
		 * y cuando lleguemos al final del algoritmo tendremos para cada columna el ancho MÁximo, con lo que 
		 * la talba quda más mejor.
		 * 
		 */
		
		//Primero recorremos el RSMD y sacamos los anchos iniciales.
		//		Columna Máximo
		HashMap<Integer,Integer> MaxLenghtPerColumn = new HashMap<Integer,Integer>();
		
		
		
		int ancho = 40;
		ResultSetMetaData RSMD = (ResultSetMetaData) resultSet.getMetaData();
		int ColumnCount = RSMD.getColumnCount();

		ArrayList<String> ColumnNames = new ArrayList<String>();
		
		for (int i = 1; i <= ColumnCount; i++) {
			ColumnNames.add(RSMD.getColumnName(i));
			MaxLenghtPerColumn.put(i,RSMD.getColumnName(i).length());
		}
		// Una vez aquí tenemos un ancho de los campos título de la columna para cada columa,
		//en caso de que los propios datos tengan un ancho mayor debemos de tenerlo en cuenta, procedemos
		//a recorrer dicha lista
		System.out.println();
		
		for (int i = 0; i < ColumnNames.size() * ancho; i++) System.out.print("_");
		
		System.out.println();
		
		for (int i = 0; i < ColumnNames.size(); i++) {
			System.out.format("%-" + ancho + "s", ColumnNames.get(i));
			if(ColumnNames.get(i).length()>MaxLenghtPerColumn.get(i))MaxLenghtPerColumn.put(i, ColumnNames.get(i).length());
		}
		
		System.out.println();
		
		for (int i = 0; i < ColumnNames.size() * ancho; i++) System.out.print("-");
		
		System.out.println();
		
		while (resultSet.next()) {
			for (int i = 0; i < ColumnNames.size(); i++) {
				System.out.format("%-" + ancho + "s", resultSet.getString(i + 1));
			}
			System.out.println();
		}
		
		System.out.println();
		
		for (int i = 0; i < ColumnNames.size() * ancho; i++) System.out.print("-");
		
		System.out.println("\n");

	}
	public static void writeResultSet(ResultSet resultSet) throws SQLException {
		int ancho = 45;
		ResultSetMetaData RSMD = (ResultSetMetaData) resultSet.getMetaData();
		int ColumnCount = RSMD.getColumnCount();

		ArrayList<String> ColumnNames = new ArrayList<String>();
		
		for (int i = 1; i <= ColumnCount; i++) {
			ColumnNames.add(RSMD.getColumnName(i));
		}
		
		System.out.println();
		
		for (int i = 0; i < ColumnNames.size() * ancho; i++) System.out.print("_");
		
		System.out.println();
		
		for (int i = 0; i < ColumnNames.size(); i++) {
			System.out.format("%-" + ancho + "s", ColumnNames.get(i));
		}
		
		System.out.println();
		
		for (int i = 0; i < ColumnNames.size() * ancho; i++) System.out.print("-");
		
		System.out.println();
		
		while (resultSet.next()) {
			for (int i = 0; i < ColumnNames.size(); i++) {
				System.out.format("%-" + ancho + "s", resultSet.getString(i + 1));
			}
			System.out.println();
		}
		
		System.out.println();
		
		for (int i = 0; i < ColumnNames.size() * ancho; i++) System.out.print("-");
		
		System.out.println("\n");

	}
}
