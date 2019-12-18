package com.example.demo.entity;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String avator;
    private String created_at;
    private String updated_at;
    private int is_admin;
    private int is_confirmed;
    private int is_deleted;
    private String employee_id;

    public int getId() {
        return id;
    }


    public int getIs_admin() {
        return is_admin;
    }

    public int getIs_confirmed() {
        return is_confirmed;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public String getAvator() {
        return avator;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getEmail() {
        return email;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public String getPassword() {
        return password;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    public void setIs_confirmed(int is_confirmed) {
        this.is_confirmed = is_confirmed;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avator='" + avator + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", is_admin=" + is_admin +
                ", is_confirmed=" + is_confirmed +
                ", is_deleted=" + is_deleted +
                ", employee_id='" + employee_id + '\'' +
                '}';
    }
}
