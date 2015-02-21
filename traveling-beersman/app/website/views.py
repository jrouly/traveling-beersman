from flask import Blueprint, request, render_template

from app.website.forms import CityForm
from app.website.models import City

mod = Blueprint('website', __name__, url_prefix='/')

@mod.route('', methods=['GET', 'POST'])
def home():

    cityForm = CityForm(request.form)

    # if the user enters a city name:
    if cityForm.validate_on_submit():

        # Retrieve data from the form.
        #city_data = cityForm.city_name.data
        addr = cityForm.address.data

        return render_template("website/test.html",
                form=cityForm,
                addr=addr)

        # if city name exists in database:
            # geojson = get(city name)

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
