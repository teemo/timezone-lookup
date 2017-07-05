# TimeZone 

A timezone lookup library taking as parameters a latitude and a longitude and returning a timezone as string (e.g. Europe/Paris).

The goal of the library is to retrieve as fast as possible a timezone into a dataset by giving a latitude and a longitude.

The library has two implementations based on two different datasets which we are going to discuss next. 

## Datasets
Both datasets were populated using the library [java-tzwhere](https://github.com/sensoranalytics/java-tzwhere/)

So you might wandering why not simply using java-tzwhere?
java-tzwhere is a very accurate lib, which is really great! It's also relatively fast, it takes few milliseconds. However to process billion of rows it remains too slow.
This library is much faster (about few nanoseconds), however it's less accurate.

The datasets are csv files with the following format: __latitude,longitude,timezone__

```
41.13375,-110.00886,America/Denver
41.13375,-109.84942,America/Denver
41.13375,-109.68999,America/Denver
41.13375,-109.53056,America/Denver
-90.0,-179.1,null
```

### KdTree Dataset
The first dataset is used to be loaded in a kdtree implementation by:
https://github.com/phishman3579/java-algorithms-implementation/blob/master/src/com/jwetherell/algorithms/data_structures/KdTree.java

* [lat-long-points-generator](https://github.com/databerries/lat-long-points-generator)
* [apply-tzwhere](https://github.com/databerries/apply-tzwhere)
* [kdtree-filter](TODO)

```
 ____________________________      _______________      _______________
|                            |    |               |    |               |
| lat_long_points_generator  | -> | apply-tzwhere | -> | kdtree-filter |
|____________________________|    |_______________|    |_______________|
```

* lat_long_points_generator: generates lat/long points at a certain accuracy. Output: 48.8,2.3
* apply-tzwhere: finds all the timezones for the points. Output: 48.8,2.3,Europe/Paris
* KdTree filter: TODO

Please, refer to each project individually for more information.

### Array Dataset 
The second dataset is used to be loaded in a simple array.

* [lat-long-points-generator](https://github.com/databerries/lat-long-points-generator)
* [apply-tzwhere](https://github.com/databerries/apply-tzwhere)
* [kdtree-islands](TODO)

```
 ____________________________      _______________      ________________
|                            |    |               |    |                |
| lat_long_points_generator  | -> | apply-tzwhere | -> | kdtree-islands |
|____________________________|    |_______________|    |________________|
```

* lat_long_points_generator: generates lat/long points at a certain accuracy. Output: 48.8,2.3
* apply-tzwhere: finds all the timezones for the points. Output: 48.8,2.3,Europe/Paris
* kdtree-islands: add additional timezones along the coasts until 20km

Please, refer to each project individually for more information.

## KdTree implementation
We have a dataset containing a location and a timezone for all the earth except territorial seas. All point are distant by  approximately a distance of 10Km
With the KdTree we have a timezone for every points on the earth. It will return the closest timezone to the point. Which could be wrong for point in the middle of the sea.

## Array implementation 
We have a dataset of a timeset for every point of the earth with a distance of 5km and a key corresponding to the timezone. We can calculate the position in the array with a hash function thus we dont have to iterate to find the timezone
With the array implementation we dont have any time zone for points in the middle of the sea.

# Why is it useful ?
When you want a timezone without any in call to external API and to be accurate (the error margin is 5km).
Before writing this library we have benchmarked different libraries and none of them gave us good results for our application

# TODO
- Include international seas timezones