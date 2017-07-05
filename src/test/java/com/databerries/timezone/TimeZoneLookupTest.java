package com.databerries.timezone;

import java.time.ZoneId;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TimeZoneLookupTest {
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private static TimeZoneLookup tz;

  @BeforeClass
  public static void initData() {
    tz = new TimeZoneLookup();
  }

  @Test
  public void testGetSomePointInAmericaNewYorkTimeZone() {
    final double latitude = 38.8044;
    final double longitude = -77.0920;
    ZoneId result = tz.getZoneId(latitude, longitude);
    ZoneId expected = ZoneId.of("America/New_York");
    Assert.assertEquals(result, expected);
  }

  @Test
  public void testGetSomePointInIndiana() {
    final double latitude = 37.952126;
    final double longitude = -86.764727;
    ZoneId result = tz.getZoneId(latitude, longitude);
    ZoneId expected = ZoneId.of("America/Indiana/Tell_City");
    Assert.assertEquals(result, expected);
  }

  @Test
  public void testGetSomePointInToulouse() {
    final double latitude = 43.6047;
    final double longitude = 1.4442;
    ZoneId result = tz.getZoneId(latitude, longitude);
    ZoneId expected = ZoneId.of("Europe/Paris");
    Assert.assertEquals(result, expected);
  }

  @Test
  public void testGetSomePointInLille() {
    final double latitude = 50.6292;
    final double longitude = 3.0573;
    ZoneId result = tz.getZoneId(latitude, longitude);
    ZoneId expected = ZoneId.of("Europe/Paris");
    Assert.assertEquals(result, expected);
  }

  @Test
  public void testGetLasVegasShouldBeInLosAngelesTimeZone() {
    final double latitude = 35.9629502;
    final double longitude = -114.8354856;
    ZoneId result = tz.getZoneId(latitude, longitude);
    System.out.println(result);
    ZoneId expected = ZoneId.of("America/Los_Angeles");
    Assert.assertEquals(result, expected);
  }

  @Test
  public void testGetAtlantaShouldBeInNewYorkTimeZone() {
    final double latitude = 33.7490;
    final double longitude = -84.3880;
    ZoneId result = tz.getZoneId(latitude, longitude);
    ZoneId expected = ZoneId.of("America/New_York");
    Assert.assertEquals(result, expected);
  }

  @Test
  public void testGetTallahasseeShouldBeInNewYorkTimeZone() {
    final double latitude = 30.4383;
    final double longitude = -84.2807;
    ZoneId result = tz.getZoneId(latitude, longitude);
    ZoneId expected = ZoneId.of("America/New_York");
    Assert.assertEquals(result, expected);
  }

  @Test
  public void testGetTallahasseeShouldBeInNewYorkZoneid() {
    final double latitude = 30.4383;
    final double longitude = -84.2807;
    ZoneId result = tz.getZoneId(latitude, longitude);
    ZoneId expected = ZoneId.of("America/New_York");
    Assert.assertEquals(result, expected);
  }

  @Test
  public void testGetBrestZoneId() {
    final double latitude = 48.3904;
    final double longitude = -4.4861;
    ZoneId result = tz.getZoneId(latitude, longitude);
    ZoneId expected = ZoneId.of("Europe/Paris");
    Assert.assertEquals(result, expected);
  }

  @Test
  public void testGetParisZoneId() {
    final double latitude = 48.8752778;
    final double longitude = 2.35861111;
    ZoneId result = tz.getZoneId(latitude, longitude);
    ZoneId expected = ZoneId.of("Europe/Paris");
    Assert.assertEquals(result, expected);
  }

  @Test
  public void testGetNullZoneId() {
    final double latitude = 0;
    final double longitude = 0;
    ZoneId result = tz.getZoneId(latitude, longitude);
    Assert.assertEquals(result, null);
  }

  @Test
  public void testGetMaxZoneId() {
    final double latitude = 89.99;
    final double longitude = 179.99;
    ZoneId result = tz.getZoneId(latitude, longitude);
    Assert.assertEquals(result, null);
  }

  @Test
  public void testGetMinZoneId() {
    final double latitude = -90;
    final double longitude = -180;
    ZoneId result = tz.getZoneId(latitude, longitude);
    Assert.assertEquals(result, null);
  }

  @Test
  public void testGetSomewhereInAtlantic() {
    final double latitude = 19.5340226;
    final double longitude = -43.3708184;
    ZoneId result = tz.getZoneId(latitude, longitude);
    Assert.assertEquals(result, null);
  }

  @Test
  public void testFailOnMaxBorderCase() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Latitude must be between -90 (included) and 90 (excluded) " +
            "and longitude must be between -180 (included) and 180 (excluded)");

    tz.getZoneId(90, 180);
  }

  @Test
  public void testFailOnMinBorderCase() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Latitude must be between -90 (included) and 90 (excluded) " +
            "and longitude must be between -180 (included) and 180 (excluded)");

    tz.getZoneId(-90.001, -180.001);
  }

}
