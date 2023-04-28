package com.playdata.dto;

public class UsersDto {
    private int id;
    private String username;
    private String password;
    private int login_fail_count;
    private String created_at;

    public UsersDto(int id, String username, String password, int login_fail_count, String created_at) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.login_fail_count = login_fail_count;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getLogin_fail_count() {
        return login_fail_count;
    }

    public void setLogin_fail_count(int login_fail_count) {
        this.login_fail_count = login_fail_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
