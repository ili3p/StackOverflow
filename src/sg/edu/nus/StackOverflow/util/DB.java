package sg.edu.nus.StackOverflow.util;

import org.bson.types.ObjectId;

import sg.edu.nus.StackOverflow.model.Model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public enum DB {

    INSTANCE;

    private static final MongoClient mc = getClient();
    private static final String DB_NAME = "stackoverflow";

    /**
     * Testing that the mongoDB connection is working.
     * Note that you need manually to insert record in the test collection in the MongoDB database during installation. 
     * @return
     */
    public static String test() {
        DBCollection coll = mc.getDB(DB_NAME).getCollection("test");
        return coll.find().next().toString();

    }

    private static MongoClient getClient() {
        try {
            return new MongoClient();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Model saveModel(Model model) {
        BasicDBObject obj = model.toDBObject();
        mc.getDB(DB_NAME).getCollection(getCollectionName(model.getClass())).save(obj);
        model.setIdAsString(obj.getObjectId("_id").toString());
        return model;
    }

    public static void saveModels(Model[] models) {
        for (Model model : models) {
            saveModel(model);
        }
    }

    public static Model updateModel(Model model) {
        BasicDBObject obj = model.toDBObject();
        mc.getDB(DB_NAME)
                .getCollection(getCollectionName(model.getClass()))
                .update(new BasicDBObject("_id", new ObjectId(model.getIdAsString())),
                        obj);
        return model;
    }

    public static void deleteModel(Model model) {
        String id = model.getIdAsString();
        DBCollection coll = mc.getDB(DB_NAME).getCollection(
                getCollectionName(model.getClass()));
        coll.remove(new BasicDBObject("_id", new ObjectId(id)));
    }

    public static String getCollectionName(Class<? extends Model> modelClass) {
        return modelClass.getSimpleName();
    }

}
