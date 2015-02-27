from bs4 import BeautifulSoup
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


def geocode_to_city_string(json):
    """
    Given a geocoded JSON object, extract the city name.
    """

    locality = None
    admin = None

    try:
        components = json["results"][0]["address_components"]
        for component in components:
            if "locality" in component["types"]:
                locality = component["long_name"]
            if "administrative_area_level_1" in component["types"]:
                admin = component["short_name"]
    except:
        return None

    if locality is None or admin is None:
        return None

    return str(locality) + ', ' + str(admin)


def geocode_to_latlng(json):
    """
    Given a geocoded JSON object, extract the lat/lng coordinates.
    """

    try:
        geometry = json["results"][0]["geometry"]
        location = geometry["location"]
        latlng = (location["lat"], location["lng"])
    except:
        return None

    return latlng


def city_string_to_ba_id(city_string):

    for city_id in WEB.CITY_IDS:
        if WEB.CITY_IDS.get(city_id) == city_string:
            return city_id
    return None


def ba_scrape_locations(ba_id):

    url = WEB.BA_CITY % ba_id
    soup = BeautifulSoup(requests.get(url).text)

    locations = soup.find(id="baContent").find_all("ul")
    breweries = locations[0].find_all("li")[:5] # top 5
    eateries = locations[1].find_all("li")[:5]  # top 5
    locations = breweries + eateries
    #locations = [location for sublist in locations for location in sublist]
    locations = [(x.b.text, x.span.text[3:]) for x in locations]

    return locations
