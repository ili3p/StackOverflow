package sg.edu.nus.StackOverflow.util;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import sg.edu.nus.StackOverflow.model.Model;
import sg.edu.nus.StackOverflow.model.User;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public enum DB {

    INSTANCE;

    /**
     * The database name. 
     */
    private static final String DB_NAME = "stackoverflow";
    private static final MongoClient mc = getClient();

    /**
     * Testing that the mongoDB connection is working.
     * Note that you need manually to insert record in the test collection in the MongoDB database during installation. 
     * @return
     */
    public static String test() {
        DBCollection coll = mc.getDB(DB_NAME).getCollection("test");
        return coll.find().next().toString();

    }

    /**
     * Get mongoDB client
     * @return
     */
    private static MongoClient getClient() {
        try {
            return new MongoClient();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get all users from the database. 
     * @return list of users
     */
    public static List<User> getAllUsers() {
        List<User> list = new ArrayList<User>();
        Gson gson = new Gson();
        DBCollection coll = mc.getDB(DB_NAME)
                .getCollection(getCollectionName(User.class));
        DBCursor cursor = coll.find();
        while (cursor.hasNext()) {
            list.add(gson.fromJson(cursor.next().toString(), User.class));
        }

        return list;
    }

    /**
     * Get the user with account id. 
     * @param accountId, the user's account id
     * @return the user or null if not found
     */
    public static User getUser(long accountId) {
        Gson gson = new Gson();
        DBCollection coll = mc.getDB(DB_NAME)
                .getCollection(getCollectionName(User.class));
        DBObject obj = coll.findOne(new BasicDBObject("account_id", accountId));
        User user = null;

        if (obj != null) {
            user = gson.fromJson(obj.toString(), User.class);
        }

        return user;
    }

    /**
     * Save the model in the database. 
     * The collection name is the class name. 
     * 
     * @param model, the model to be saved
     * @return the model with the ID property updated. 
     */
    public static Model saveModel(Model model) {
        BasicDBObject obj = model.toDBObject();
        mc.getDB(DB_NAME).getCollection(getCollectionName(model.getClass())).save(obj);
        model.setIdAsString(obj.getObjectId("_id").toString());
        return model;
    }

    /**
     * Save all the models in the array. 
     * It calls saveModel(model) for each model in the array. 
     * @param models, array of models
     */
    public static void saveModels(Model[] models) {
        for (Model model : models) {
            saveModel(model);
        }
    }

    /**
     * Update the properties of the model in the database. 
     * 
     * @param model
     * @return
     */
    public static Model updateModel(Model model) {
        BasicDBObject obj = model.toDBObject();
        mc.getDB(DB_NAME)
                .getCollection(getCollectionName(model.getClass()))
                .update(new BasicDBObject("_id", new ObjectId(model.getIdAsString())),
                        obj);
        return model;
    }

    /**
     * Delete this model from the database. 
     * The deletion is done based on the internal database id. 
     * @param model
     */
    public static void deleteModel(Model model) {
        String id = model.getIdAsString();
        DBCollection coll = mc.getDB(DB_NAME).getCollection(
                getCollectionName(model.getClass()));
        coll.remove(new BasicDBObject("_id", new ObjectId(id)));
    }

    /**
     * Get the mongoDB collection name from the Java class name. 
     * 
     * @param modelClass
     * @return
     */
    public static String getCollectionName(Class<? extends Model> modelClass) {
        return modelClass.getSimpleName();
    }

}
