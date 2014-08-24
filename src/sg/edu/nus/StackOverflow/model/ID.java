package sg.edu.nus.StackOverflow.model;

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
