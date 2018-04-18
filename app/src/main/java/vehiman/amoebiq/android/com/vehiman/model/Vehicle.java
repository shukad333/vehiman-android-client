package vehiman.amoebiq.android.com.vehiman.model;

/**
 * Created by skadavath on 4/12/18.
 */

public class Vehicle {

    private Long id;
    private String brand;
    private String type;
    private String number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String model) {
        this.number = model;
    }
}
