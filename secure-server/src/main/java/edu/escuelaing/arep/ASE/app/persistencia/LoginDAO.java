package edu.escuelaing.arep.ASE.app.persistencia;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;

import java.util.Collection;
import java.util.ArrayList;

public class LoginDAO implements CRUD<Document, String, String>{

    public static LoginDAO getLoginDAO( String dataBaseName, String collectionName){
        MongoDatabase database = MongoUtil.getDB(dataBaseName);
        return new LoginDAO(database,collectionName);
    }

    private MongoCollection<Document> logsCollection;

    public LoginDAO(MongoDatabase database, String collectionNameString){
        this.logsCollection = database.getCollection(collectionNameString);
    }

    public void guardar(String userName, String password){
        Document nuevoLog = new Document("_id", userName)
                                .append("password", password);
        logsCollection.insertOne(nuevoLog);

    }

    public void eliminar(String userName){
        logsCollection.deleteOne(eq("_id", userName));
    }

    public Document buscar(String userName){
        return logsCollection.find(eq("_id", userName)).first();
    }

    public Collection<Document> buscarTodos(){
        return logsCollection.find().into(new ArrayList<>());
    }
    
}
