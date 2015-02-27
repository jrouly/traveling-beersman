from flask import Blueprint, request, jsonify

from app.api import constants as API

mod = Blueprint('api', __name__, url_prefix='/api')

@mod.route('/', methods=['GET'])
def api():
    return jsonify({'response': 'invalid request'})

@mod.route('/test', methods=['GET'])
def test():
    response = {'hello': 'world'}
    return jsonify(response)
