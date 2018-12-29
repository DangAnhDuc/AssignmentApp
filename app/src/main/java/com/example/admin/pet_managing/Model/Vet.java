package com.example.admin.pet_managing.Model;

public class Vet {
    private String Name;
    private String Address;
    private String Website;
    private String Phone;
    private String Image;

    public Vet() {
    }

    public Vet(String name, String address, String website, String phone, String image) {
        Name = name;
        Address = address;
        Website = website;
        Phone = phone;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
