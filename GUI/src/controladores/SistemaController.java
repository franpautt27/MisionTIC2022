package controladores;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import modelos.SchoolGradingSystem;

public class SistemaController {

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfMateria;

    @FXML
    private Label lbError;

    @FXML
    private TextField tfNota;

    @FXML
    private TextField tfGenero;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCargar;

    @FXML
    private TextArea taInput;

    @FXML
    private Button btnProcesar;

    @FXML
    private TextArea taOutput;

    @FXML
    private TextField tfConsultarNombre;

    @FXML
    private TextField tfConsultarMateria;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TextArea taResultadosConsulta;

    @FXML
    private Label lbMensaje;

    @FXML
    void cargarDatos(ActionEvent event) {
        String cadenaSQL = "SELECT * FROM notas";
        int idNombre = 0;
        int idMaterias = 0;
        int idGenero = 0;
        String cadena = "";

        try{
            Connect objConexion = new Connect();
            Connection conn = objConexion.connect();
            Statement stmt = conn.createStatement();
            ResultSet resConsulta = stmt.executeQuery(cadenaSQL); //para traer datos
            while(resConsulta.next()){
                if(resConsulta.getString("nombre").equals("armando")){
                    idNombre = 1;
                }else if(resConsulta.getString("nombre").equals("nicolas")){
                    idNombre = 2;
                }else if(resConsulta.getString("nombre").equals("daniel")){
                    idNombre = 3;
                }else if(resConsulta.getString("nombre").equals("maria")){
                    idNombre = 4;
                }else if(resConsulta.getString("nombre").equals("marcela")){
                    idNombre = 5;
                }else if(resConsulta.getString("nombre").equals("alexandra")){
                    idNombre = 6;
                }

                if(resConsulta.getString("materia").equals("idiomas")){
                    idMaterias = 1;
                }else if(resConsulta.getString("materia").equals("historia")){
                    idMaterias = 2;
                }else {
                    idMaterias = 3;
                }

                if(resConsulta.getString("genero").equals("m")){
                    idGenero = 0;
                }else{
                    idGenero = 1;
                }

                cadena += idNombre + " " + idGenero + " " + idMaterias + " " + resConsulta.getDouble("nota") + "\n";
            }
            taInput.setText(cadena);
            conn.close();
            stmt.close();
            System.out.println("lista visualizada");
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void consultarDatos(ActionEvent event) throws SQLException {
        String nombre = tfConsultarNombre.getText();
        String materia = tfConsultarMateria.getText();
        String cadenaSQL = "";
        String error = "";
        String cadena = "";

        if(nombre.equals("") && !(materia.equals(""))){
            cadenaSQL = "SELECT  nombre,  genero,    materia,  nota FROM notas WHERE materia = '" + materia + "'";
            Connect objConexion = new Connect();//devuelve el objeto conectado a la URL
            Connection conn = objConexion.connect();
            Statement stmt = conn.createStatement(); 
            ResultSet resConsulta = stmt.executeQuery(cadenaSQL);
    
            while(resConsulta.next()){
                cadena += resConsulta.getString("nombre") + " " + resConsulta.getString("genero") + " " + resConsulta.getString("materia") + " " + resConsulta.getString("nota") + "\n";
            }
            taResultadosConsulta.setText(cadena);
            conn.close();
            stmt.close();
            if(taResultadosConsulta.getText().equals("")){
                error = "no se encontraron resultados";
            }
    
        }else if( !(nombre.equals("")) && materia.equals("") ){
            cadenaSQL = "SELECT  nombre,  genero,    materia,  nota FROM notas WHERE nombre = '" + nombre + "'";
            Connect objConexion = new Connect();//devuelve el objeto conectado a la URL
            Connection conn = objConexion.connect();
            Statement stmt = conn.createStatement(); 
            ResultSet resConsulta = stmt.executeQuery(cadenaSQL);
    
            while(resConsulta.next()){
                cadena += resConsulta.getString("nombre") + " " + resConsulta.getString("genero") + " " + resConsulta.getString("materia") + " " + resConsulta.getString("nota") + "\n";
            }
            taResultadosConsulta.setText(cadena);
            conn.close();
            stmt.close();
            if(taResultadosConsulta.getText().equals("")){
                error = "no se encontraron resultados";
            }
    
        }else if( !(nombre.equals("")) && !(materia.equals("")) ){
            cadenaSQL = "SELECT  nombre,  genero,    materia,  nota FROM notas WHERE nombre = '" + nombre + "' AND materia = '" + materia + "'";
            Connect objConexion = new Connect();//devuelve el objeto conectado a la URL
            Connection conn = objConexion.connect();
            Statement stmt = conn.createStatement(); 
            ResultSet resConsulta = stmt.executeQuery(cadenaSQL);
    
            while(resConsulta.next()){
                cadena += resConsulta.getString("nombre") + " " + resConsulta.getString("genero") + " " + resConsulta.getString("materia") + " " + resConsulta.getString("nota") + "\n";
            }
            taResultadosConsulta.setText(cadena);
            conn.close();
            stmt.close();
            if(taResultadosConsulta.getText().equals("")){
                error = "no se encontraron resultados";
            }
    
        }else if (nombre.equals("") && materia.equals("")){
            error = "campos no pueden ser vacios";
        }




        lbMensaje.setText(error);



    }

    @FXML
    void eliminarDatos(ActionEvent event) throws SQLException {
        String nombre = tfConsultarNombre.getText();
        String materia = tfConsultarMateria.getText();
        String cadenaSQL = "";
        String error = "";
        String cadena = "";
        String mensajePrevio = lbMensaje.getText();

        if(nombre.equals("") && !(materia.equals(""))){
            cadenaSQL = "DELETE FROM notas WHERE materia = '" + materia + "'";
            Connect objConexion = new Connect();//devuelve el objeto conectado a la URL
            Connection conn = objConexion.connect();
            Statement stmt = conn.createStatement(); 
            stmt.executeUpdate(cadenaSQL);
            conn.close();
            stmt.close();
            System.out.println("producto eliminado");
            error = "Registro eliminado correctamente";
            taResultadosConsulta.setText("");
            tfConsultarNombre.setText("");
            tfConsultarMateria.setText("");
        }else if( !(nombre.equals("")) && materia.equals("") ){
            cadenaSQL = "DELETE FROM notas WHERE nombre = '" + nombre + "'";
            Connect objConexion = new Connect();//devuelve el objeto conectado a la URL
            Connection conn = objConexion.connect();
            Statement stmt = conn.createStatement(); 
            stmt.executeUpdate(cadenaSQL);
            conn.close();
            stmt.close();
            System.out.println("producto eliminado");
            error = "Registro eliminado correctamente";
            taResultadosConsulta.setText("");
            tfConsultarNombre.setText("");
            tfConsultarMateria.setText("");
        }else if( !(nombre.equals("")) && !(materia.equals("")) ){
            cadenaSQL = "DELETE FROM notas WHERE nombre = '" + nombre + "' AND materia = '" + materia + "'";
            Connect objConexion = new Connect();//devuelve el objeto conectado a la URL
            Connection conn = objConexion.connect();
            Statement stmt = conn.createStatement(); 
            stmt.executeUpdate(cadenaSQL);
            conn.close();
            stmt.close();
            System.out.println("producto eliminado");
            error = "Registro eliminado correctamente";
            taResultadosConsulta.setText("");
            tfConsultarNombre.setText("");
            tfConsultarMateria.setText("");
        }else if (nombre.equals("") && materia.equals("")){
            error = "campos no pueden ser vacios";
        }

        if(mensajePrevio.equals("no se encontraron resultados")){
            error = "no se encontraron resultados";
        }





        lbMensaje.setText(error);



    }

    @FXML
    void guardarDatos(ActionEvent event) throws SQLException{
        String nombre = tfNombre.getText();
        String genero = tfGenero.getText();
        String materia = tfMateria.getText();
        String nota = tfNota.getText();
        //Double nota = Double.parseDouble(tfNota.getText());

        String error = "";
        

        
        String cadenaSQL = "INSERT INTO notas (    nombre,      genero,      materia,     nota   )    VALUES (  '" + nombre + "',      '" + genero + "',     '" + materia + "',    " + nota + "    );";

        if((nombre.equals("armando") || nombre.equals("nicolas") || nombre.equals("daniel") || nombre.equals("maria") || nombre.equals("marcela") || nombre.equals("alexandra")) && ( genero.equals("m") || genero.equals("f") ) && ( materia.equals("idiomas") || materia.equals("historia") || materia.equals("literatura") )  ){
            
            try{
                Connect objConexion = new Connect();//devuelve el objeto conectado a la URL
                Connection conn = objConexion.connect();
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(cadenaSQL);
                conn.close();
                stmt.close();
                System.out.println("producto creado");
                error = "Registro ingresado correctamente";
                tfNombre.setText("");
                tfGenero.setText("");
                tfMateria.setText("");
                tfNota.setText("");
    
    
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else if(nombre.equals("") || genero.equals("") || materia.equals("") || nota.equals("") ){
            error = "no se permiten campos vacios";
        } else{
            error = "datos no válidos";
        }

        lbError.setText(error);
        
        

    }

    @FXML
    void procesarDatos(ActionEvent event) throws SQLException {
        try{
            String cadenaSQL = "SELECT count(id) FROM notas";
            Connect objConexion = new Connect();
            Connection conn = objConexion.connect();
            Statement stmt = conn.createStatement();
            ResultSet resConsulta = stmt.executeQuery(cadenaSQL);
            System.out.println(resConsulta.getInt(1));
            int n = resConsulta.getInt(1);
            conn.close();
            stmt.close();
            String registros = taInput.getText();
            calcular(n, registros);

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Primero debe cargar los datos");
            alert.showAndWait();

        }



    }

    private void calcular(int n, String registro){
        SchoolGradingSystem p1 = new SchoolGradingSystem();
        p1.loadData(registro, n);
        String respuesta1 = p1.stat1() + "";
        String respuesta2 = p1.stat2() + "";
        String respuesta3 = p1.stat3() + "";
        String respuesta4 = p1.stat4() + "";

        //colocamos las respuestas como output en el text area de resultado
        taOutput.setText(respuesta1 + "\n" + respuesta2 + "\n" + respuesta3 + "\n" + respuesta4);
    }

}
