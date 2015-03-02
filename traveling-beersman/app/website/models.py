from app.db import Model, Column, Integer, String, Float

class City(Model):

    __tablename__ = "website_city"
    id = Column(Integer, primary_key=True)
    name = Column(String(50), unique=True)
    address = Column(String(100))
    lat = Column(Float)
    lon = Column(Float)

    def __init__(self, name=None, locations=None):
        self.name = name
        self.locations = locations

    def __repr__(self):
        return '<City: %r>' % (self.name)
