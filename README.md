# The Traveling Beersman

This is a silly project I created to practice Scala & Spark. Given an input
CSV of locations, the Traveling Beersman will route the shortest path
to visit every location. The current use case is to order the breweries in
Denver for an efficient tourist.

This could potentially be expanded to become more generalized, but I don't
have the time right now.


ToDo:
* Clean up CSV syntax (actually use strings for addresses instead of lat/long)
* Use a Scala library to geocode addresses using Google Maps
* Use Google's DistanceMatrix to find true path time
* Remove weird Establishment object structure
