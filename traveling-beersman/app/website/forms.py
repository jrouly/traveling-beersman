from flask.ext.wtf import Form
from wtforms import TextField

class CityForm(Form):
    city_name = TextField('City')
    address = TextField('Address')

    def validate_city_name(form, field):
        if len(form.city_name.data) == 0 and len(form.address.data) == 0:
            raise ValidationError("You must enter at least the address or city.")

