package com.example.project01.Object;

public class client {
    public int id;
    public String name;
    public String gender;
    public int age;
    public String QQ;
    public String email;
    public String username;
    public String password;

    public client(int i, String name, String gender, int age, String QQ, String email,String username, String password) {
        id = i;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.QQ = QQ;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    public client() {
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int i) {
        id = i;
    }

    @Override
    public String toString() {
        return "client{" +
                "Id=" + id+
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", QQ='" + QQ + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
