/**
 * User Model Class
 */
package com.example.fuelway.model;

import java.io.Serializable;

public class user implements Serializable {

    private int id;
    private String name;
    private String userMobile;
    private String nic;
    private String password;

    public user(int id, String name, String userMobile, String nic, String password) {
        this.id = id;
        this.name = name;
        this.userMobile = userMobile;
        this.nic = nic;
        this.password = password;
    }

    public user(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
