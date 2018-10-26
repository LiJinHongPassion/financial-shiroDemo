package com.cqut.financial.ssm.entity;

import com.cqut.financial.ssm.entity.base.Entity;

public class User extends Entity {
    private Integer user_id;

    private String user_name;

    private String password;

    private Integer total_assets;

    private Integer money;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getTotal_assets() {
        return total_assets;
    }

    public void setTotal_assets(Integer total_assets) {
        this.total_assets = total_assets;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public String getPrimaryKey() {
        return "user_id";
    }
}