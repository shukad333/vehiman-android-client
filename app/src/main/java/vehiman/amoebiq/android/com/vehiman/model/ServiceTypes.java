package vehiman.amoebiq.android.com.vehiman.model;

/**
 * Created by skadavath on 4/23/18.
 */

public class ServiceTypes {

    private long id;
    private String type;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
