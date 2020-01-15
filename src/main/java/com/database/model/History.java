package com.database.model;

import java.sql.Date;
import java.sql.Time;

public class History {

    private String user;
    private String employee;
    private String action;
    private Date date;
    private Time time;

    public History( String user, String employee, String action, Date date, Time time) {

        this.user = user;
        this.employee = employee;
        this.action = action;
        this.date = date;
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public String getEmployee() {
        return employee;
    }

    public String getAction() {
        return action;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
}
