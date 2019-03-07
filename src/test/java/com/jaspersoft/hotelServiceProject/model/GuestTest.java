package com.jaspersoft.hotelServiceProject.model;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GuestTest {
    private Guest guest;

    @BeforeMethod
    public void beforeEachTest() {
        guest = new Guest("Tom Jameson", 55.89);
    }


    @Test(description = "Verify getter and setter methods for name property")
    public void testGetSetName() {
        guest.setName("Jon");
        Assert.assertEquals(guest.getName(), "Jon");
    }


    @Test(description = "Verify getter and setter methods for money property")
    public void testGetSetMoney() {
        guest.setMoney(22.55);
        Assert.assertEquals(guest.getMoney(), 22.55);
    }


    @Test(description = "Verify equals method compares quests by name")
    public void testEquals() {
        Guest guest1 = new Guest("Tom Jameson", 585.89);
        Assert.assertEquals(guest, guest1);

    }

    @Test(description = "Verify hashcode method takes into account only guest name")
    public void testHashCode() {
        Guest guest1 = new Guest("Tom Jameson", 755.89);
        Assert.assertEquals(guest1.hashCode(), guest.hashCode());

    }
}