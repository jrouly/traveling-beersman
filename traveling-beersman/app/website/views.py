from flask import Blueprint, request, render_template

from app.website.forms import CityForm

mod = Blueprint('website', __name__, url_prefix='/')

@mod.route('', methods=['GET', 'POST'])
def home():

    cityForm = CityForm(request.form)

    # if the user enters a city name:
    if cityForm.validate_on_submit():
        pass

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
        return render_template("website/home.html")

@mod.route("about")
def about():
    return render_template("website/about.html")
