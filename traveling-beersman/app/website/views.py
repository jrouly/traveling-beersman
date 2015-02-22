from flask import Blueprint, request, render_template

from app.website import constants as WEB
from app.website.forms import CityForm
from app.website.models import City

from app.geocode import geocode_address, geocode_to_city_string
from app.geocode import geocode_to_latlng, city_string_to_ba_id

mod = Blueprint('website', __name__, url_prefix='/')


@mod.route('', methods=['GET', 'POST'])
def home():

    cityForm = CityForm(request.form)

    # if the user enters a city name:
    if cityForm.validate_on_submit():

        # Retrieve data from the form.
        #city_data = cityForm.city_name.data
        addr = cityForm.address.data

        # TODO: Would do caching here, but city is disabled.

        # Request the geocoded address.
        geocode = geocode_address(addr)
        if geocode is None:
            return render_template("website/error.html",
                    error_code=WEB.ERROR_CITY)

        # Extract the city name and coordinates
        city_string = geocode_to_city_string(geocode)
        latlng = geocode_to_latlng(geocode)

        if city_string is None or latlng is None:
            return render_template("website/error.html",
                    error_code=WEB.ERROR_CITY)

        # Identify the Beer Advocate ID of the city.
        ba_id = city_string_to_ba_id(city_string)
        if ba_id is None:
            return render_template("website/error.html",
                    error_code=WEB.ERROR_NOT_IN_BA)

        locations = ba_scrape_locations(ba_id)

        # Render the template.
        return render_template("website/test.html",
                form=cityForm,
                city_string=city_string,
                ba_id=ba_id,
                latlng=latlng)


        # else:
            # soup = scrape-BA
            # locations = raw_locations(soup)
            # locations = geocoded_locations(locations)
            # locations = TSP(locations)
            # geojson = format(locations)

        # render_template("website/map.html", city=city name, data=geojson)

    else:
        return render_template("website/home.html",
                form=cityForm)

@mod.route("about")
def about():
    return render_template("website/about.html")
