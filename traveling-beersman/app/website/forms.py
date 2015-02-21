from flask.ext.wtf import Form
from wtforms import TextField
from wtforms.validators import Required

class CityForm(Form):
    city_name = TextField('City', [Required()])

