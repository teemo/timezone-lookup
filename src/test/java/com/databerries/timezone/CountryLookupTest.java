package com.databerries.timezone;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

public class CountryLookupTest {
    private static CountryLookup countryLookup;

    @BeforeClass
    public static void initData() {
        countryLookup = CountryLoader.createWithDefaultDataset();
    }

    @Test
    public void testShouldBeInEurope() {
        boolean isInEurope = countryLookup.isTimezoneInEU("Europe/Paris");
        Assert.assertThat(isInEurope, is(true));
    }

    @Test
    public void testShouldNotBeInEurope() {
        boolean isInEurope = countryLookup.isTimezoneInEU("America/New_York");
        Assert.assertThat(isInEurope, is(false));
    }

    @Test
    public void testShouldNotBeInEuropeNullTimezone() {
        boolean isInEurope = countryLookup.isTimezoneInEU(null);
        Assert.assertThat(isInEurope, is(false));
    }

    @Test
    public void testCountryCodeShouldBeFR() {
        Country country = countryLookup.getCountry("Europe/Paris");
        Assert.assertThat(country.countryCode(), is("FR"));
    }

    @Test
    public void testCountryCodeShouldBeUS() {
        Country country = countryLookup.getCountry("America/New_York");
        Assert.assertThat(country.countryCode(), is("US"));
    }

    @Test
    public void testCountryCodeShouldBeNull() {
        Country country = countryLookup.getCountry(null);
        Assert.assertThat(country, nullValue());
    }

    @Test
    public void testAdministrationCodeShouldReturnFR() {
        String administrationCode = countryLookup.getAdministrationCode("Indian/Reunion");
        Assert.assertThat(administrationCode, is("FR"));
    }

    @Test
    public void testAdministrationCodeShouldReturnUS() {
        String administrationCode = countryLookup.getAdministrationCode("America/New_York");
        Assert.assertThat(administrationCode, is("US"));
    }
}
