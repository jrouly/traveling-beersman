from flask.ext.wtf import Form
from wtforms import TextField

class CityForm(Form):
    city_name = TextField('City')
    address = TextField('Address')
