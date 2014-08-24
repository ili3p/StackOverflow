package sg.edu.nus.StackOverflow.model;

/**
 * Class that encapsulates StackOverflow user data. 
 * @author ilija
 *
 */
public class User extends Model {

    /**
     * StackOverflow internal account id. 
     */
    private long account_id;
    
    /**
     * StackOverflow account creation date. 
     */
    private long creation_date;

    /**
     * Link to the StackOverflow profile of the user. 
     */
    private String link;

    /**
     * Location of the user.
     */
    private String location;
    
    /**
     * Reputation points for the user.
     */
    private int reputation;
    
    /**
     * User's age. 
     */
    private int age;

    /**
     * Location extracted from the HTML code of the StackOverflow profile.
     */
    private String parsedLocation;
    
    /**
     * Reputation points extracted from the HTML code of the StackOverflow profile.
     */
    private int parsedReputation;
    
    /**
     * User's age extracted from the HTML code of the StackOverflow profile.
     */
    private int parsedAge;

    /**
     * Country code from Google's Geocoding API on the user's location. 
     */
    private String country;

    /**
     * @return the reputation
     */
    public int getReputation() {
        return reputation;
    }

    /**
     * @param reputation the reputation to set
     */
    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the parsedLocation
     */
    public String getParsedLocation() {
        return parsedLocation;
    }

    /**
     * @param parsedLocation the parsedLocation to set
     */
    public void setParsedLocation(String parsedLocation) {
        this.parsedLocation = parsedLocation;
    }

    /**
     * @return the parsedReputation
     */
    public int getParsedReputation() {
        return parsedReputation;
    }

    /**
     * @param parsedReputation the parsedReputation to set
     */
    public void setParsedReputation(int parsedReputation) {
        this.parsedReputation = parsedReputation;
    }

    /**
     * @return the parsedAge
     */
    public int getParsedAge() {
        return parsedAge;
    }

    /**
     * @param parsedAge the parsedAge to set
     */
    public void setParsedAge(int parsedAge) {
        this.parsedAge = parsedAge;
    }

    /**
     * @return the account_id
     */
    public long getAccount_id() {
        return account_id;
    }

    /**
     * @param account_id the account_id to set
     */
    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    /**
     * @return the creation_date
     */
    public long getCreation_date() {
        return creation_date;
    }

    /**
     * @param creation_date the creation_date to set
     */
    public void setCreation_date(long creation_date) {
        this.creation_date = creation_date;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

}
