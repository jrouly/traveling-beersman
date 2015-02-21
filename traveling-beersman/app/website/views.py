from flask import Blueprint, render_template

mod = Blueprint('website', __name__, url_prefix='/')

@mod.route('/')
def home():

    # if the user enters a city name:
    if False:
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
