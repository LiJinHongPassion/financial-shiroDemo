package com.cqut.financial.ssm.entity;

import com.cqut.financial.ssm.entity.base.Entity;

public class FixedBid extends Entity {
    private Integer fixed_id;

    private String fixed_bid_name;

    private Double year_rate;

    private Integer server_date;

    private Integer residual_voting;

    public Integer getFixed_id() {
        return fixed_id;
    }

    public void setFixed_id(Integer fixed_id) {
        this.fixed_id = fixed_id;
    }

    public String getFixed_bid_name() {
        return fixed_bid_name;
    }

    public void setFixed_bid_name(String fixed_bid_name) {
        this.fixed_bid_name = fixed_bid_name == null ? null : fixed_bid_name.trim();
    }

    public Double getYear_rate() {
        return year_rate;
    }

    public void setYear_rate(Double year_rate) {
        this.year_rate = year_rate;
    }

    public Integer getServer_date() {
        return server_date;
    }

    public void setServer_date(Integer server_date) {
        this.server_date = server_date;
    }

    public Integer getResidual_voting() {
        return residual_voting;
    }

    public void setResidual_voting(Integer residual_voting) {
        this.residual_voting = residual_voting;
    }

    @Override
    public String getTableName() {
        return "fixed_bid";
    }

    @Override
    public String getPrimaryKey() {
        return "fixed_id";
    }
}