package sg.edu.nus.StackOverflow.model;

/**
 * Class that wraps the MongoDB internal ID. 
 * Needed for converting MongoDB objects to JSON and back. 
 * 
 * @author ilija
 *
 */
public class ID {
    public ID(String id) {
        this.$oid = id;
    }

    private String $oid;

    @Override
    public String toString() {
        return get$oid();
    }

    public String get$oid() {
        return $oid;
    }

    public void set$oid(String $oid) {
        this.$oid = $oid;
    }
}
