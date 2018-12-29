package com.example.admin.pet_managing.Model;

public class Pet {
    private String Name;
    private String Image;
    private String Type;
    private String Weight;
    private String Height;
    private String Gender;
    private String Age;
    private String BloodType;
    private String FavouriteFood;
    private String Email;


    public Pet() {
    }

    public Pet(String name, String image, String type, String weight, String height, String gender, String age, String bloodType, String favouriteFood, String email) {
        Name = name;
        Image = image;
        Type = type;
        Weight = weight;
        Height = height;
        Gender = gender;
        Age = age;
        BloodType = bloodType;
        FavouriteFood = favouriteFood;
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getBloodType() {
        return BloodType;
    }

    public void setBloodType(String bloodtype) {
        BloodType = bloodtype;
    }

    public String getFavouriteFood() {
        return FavouriteFood;
    }

    public void setFavouriteFood(String favouritefood) {
        FavouriteFood = favouritefood;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
