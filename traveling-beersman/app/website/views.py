from flask import Blueprint, render_template

mod = Blueprint('website', __name__, url_prefix='/')

@mod.route('/')
def home():
    return render_template("website/home.html")


