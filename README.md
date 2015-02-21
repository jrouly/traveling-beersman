# The Traveling Beersman

[![Join the chat at https://gitter.im/jrouly/traveling-beersman](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/jrouly/traveling-beersman?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

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
