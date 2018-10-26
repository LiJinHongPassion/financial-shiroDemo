package com.cqut.financial.ssm.entity;


import com.cqut.financial.ssm.entity.base.Entity;

public class UserInvestment extends Entity {
    private Integer id;

    private Integer fixed_id;

    private Integer user_id;

    private Integer invest_money;

    private Double year_rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFixed_id() {
        return fixed_id;
    }

    public void setFixed_id(Integer fixed_id) {
        this.fixed_id = fixed_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getInvest_money() {
        return invest_money;
    }

    public void setInvest_money(Integer invest_money) {
        this.invest_money = invest_money;
    }

    public Double getYear_rate() {
        return year_rate;
    }

    public void setYear_rate(Double year_rate) {
        this.year_rate = year_rate;
    }

    @Override
    public String getTableName() {
        return "user_investment";
    }

    @Override
    public String getPrimaryKey() {
        return "id";
    }
}