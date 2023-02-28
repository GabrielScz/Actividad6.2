package sanchezcarlos_a6.pkg2;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection {
    
    DB baseDatos;
    DBCollection coleccion;
    BasicDBObject documento = new BasicDBObject();
    
    //Metodo connection
    public Connection(){
        Mongo mongo = new Mongo("localhost", 27017);
        baseDatos = mongo.getDB("restaurante");
        coleccion = baseDatos.getCollection("platos");
        System.out.println("Conexion exitosa!");
    }
    
    //Metodo insert
    public boolean insert(String nombre){
        documento.put("nombre", nombre);
        coleccion.insert(documento);
        
        return true;
    }
    
    //Metodo delete
    public boolean delete(String nombre){
        documento.put("nombre", nombre);
        coleccion.remove(documento);
        
        return true;
    }
    
    //Metodo update
    public boolean update(String platoViejo, String platoNuevo){
        documento.put("nombre", platoViejo);
        BasicDBObject documentoNuevo = new BasicDBObject();
        documentoNuevo.put("nombre", platoNuevo);
        coleccion.findAndModify(documento, documentoNuevo);
        
        return true;
    }
    
    //Metodo find
    public void find(){
        DBCursor cursor = coleccion.find();
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }
}
