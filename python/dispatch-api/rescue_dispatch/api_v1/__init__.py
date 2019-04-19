__author__ = 'vmoeykens'

from flask import Blueprint


api = Blueprint('api_v1', __name__)

from . import dispatch
