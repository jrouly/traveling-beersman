import requests

from app.website import constants as WEB


def geocode_address(address):
    """
    Given an address, return the geocoded JSON representation.
    """

    # Geocode the address.
    response = requests.get(WEB.GEOCODE % address);

    # Check if the page was successfuly reached.
    if response.status_code != 200:
        return None

    # Represent the results as json.
    json = response.json()

    # Check if the query was successful.
    status = json["status"]
    if status != "OK":
        return None

    return json


def geocode_to_city(json):
    """
    Given a geocoded JSON object, extract the city name.
    """

    city = (None, None)

    components = json["results"][0]["address_components"]
    for component in components:
        if "locality" in component["types"]:
            city[0] = component["long_name"]
        if "administrative_area_level_1" in component["types"]:
            city[1] = component["short_name"]
    city_string = ', '.join(city)

    geometry = json["results"][0]["geometry"]
    location = geometry["location"]
    latlon = (location["lat"], location["lon"])

    return (city_string, latlon)
