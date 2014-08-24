package sg.edu.nus.StackOverflow.model;

import java.lang.reflect.Field;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

/**
 * Class that all MongoDB models should extend. 
 * It contains utility methods for converting objects in BasicDBObjects, 
 * needed for saving the objects in mongoDB. 
 * @author ilija
 *
 */
public abstract class Model {

    private ID _id;

    public void setIdAsString(String id) {
        this.set_id(new ID(id));
    }

    public String getIdAsString() {
        if (_id == null) {
            return null;
        } else {
            return _id.get$oid();
        }
    }

    public ObjectId getObjectId() {
        String id = getIdAsString();
        if (id == null) {
            return null;
        } else {
            return new ObjectId(id);
        }
    }

    /**
     * Convert the object in BasicDBObject.
     * The object's field names will be used as keys in the BasicDBObject. 
     * @return
     */
    public BasicDBObject toDBObject() {

        BasicDBObject doc = new BasicDBObject();
        Class<? extends Object> c = this.getClass();
        Field[] fields = c.getDeclaredFields();
        try {

            for (Field field : fields) {

                String name = field.getName();
                String mN = "get" + Character.toUpperCase(name.charAt(0))
                        + name.substring(1);
                Object value = c.getDeclaredMethod(mN).invoke(this);

                doc.put(name, value);
            }

            ObjectId id = (ObjectId) c.getMethod("getObjectId").invoke(this);
            if (id != null) {
                doc.put("_id", id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
    }

    public ID get_id() {
        return _id;
    }

    public void set_id(ID _id) {
        this._id = _id;
    }
}
