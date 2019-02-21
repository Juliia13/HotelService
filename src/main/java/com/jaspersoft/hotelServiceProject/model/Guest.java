package com.jaspersoft.hotelServiceProject.model;

import java.util.Objects;

public class Guest {

    private String name;
    private double money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Guest(String name, double money) {
        this.name = name;
        this.money = money;
    }


    @Override
    public String toString() {
        return "Guest{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guest)) return false;
        Guest guest = (Guest) o;
        return Double.compare(guest.getMoney(), getMoney()) == 0 &&
                getName().equals(guest.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMoney());
    }
}
