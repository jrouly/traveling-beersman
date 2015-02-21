from app import db

class City(db.Model):

    __tablename__ = "website_city"
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(50), unique=True)
    locations = db.Column(db.String())

    def __init__(self, name=None, locations=None):
        self.name = name
        self.locations = locations

    def __repr__(self):
        return '<City: %r>' % (self.name)
