package service.dto;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class UserWithVehiclesDTO {


    private String CUIL;

    private String name;
    private String surname;
    private String address;

    @Id
    private String email;
    private double account;
    private boolean status;
    private List<Integer> scores;
    private String userName;
    private List<VehicleDTO> vehicles;

    public UserWithVehiclesDTO(){
        this.scores = new ArrayList<Integer>();
    }

    public String getCUIL() {
        return CUIL;
    }

    public void setCUIL(String CUIL) {
        this.CUIL = CUIL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    public List<VehicleDTO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleDTO> idVehicle) {
        this.vehicles = idVehicle;
    }
}
