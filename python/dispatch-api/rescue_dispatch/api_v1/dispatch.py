__author__ = 'vmoeykens'

from flask import json, request, Response

from . import api
from ..models.dispatch import Dispatch
from ..schemas.dispatch import dispatch_schema
from ..modules.process_json import JsonParser
import datetime
import uuid


@api.route('/dispatches', methods=['GET'])
def get_dispatches():
    dispatches = Dispatch.get_all_dispatches()
    ser_dispatches = dispatch_schema.dump(dispatches, many=True).data
    return dispatch_response(ser_dispatches, 200)


@api.route('/dispatches/<int:id>', methods=['GET'])
def get_dispatch(id):
    dispatch = Dispatch.get_one_dispatch(id)
    ser_dispatch = dispatch_schema.dump(dispatch).data
    return dispatch_response(ser_dispatch, 200)


@api.route('/dispatches/uid/<string:uid>', methods=['GET'])
def get_dispatch_with_uid(uid):
    dispatch = Dispatch.get_one_dispatch_uid(uid)
    ser_dispatch = dispatch_schema.dump(dispatch).data
    return dispatch_response(ser_dispatch, 200)


@api.route('/dispatches', methods=['POST'])
def create_dispatch():
    dispatch_data = request.get_json()
    dispatch_data['text_uid'] = str(uuid.uuid4())
    dispatch_data['timestamp'] = datetime.datetime.utcnow()
    print(dispatch_data)
    data, error = dispatch_schema.load(dispatch_data)
    if error:
        return custom_response({"message": "Error Creating Dispatch", "error": error}, 400)
    dispatch = Dispatch(data)
    dispatch.save()

    return custom_response({"message": "Dispatch successfully added"}, 201)


@api.route('/dispatches/<int:id>', methods=['PUT'])
def update_dispatch(id):
    dispatch = Dispatch.get_one_dispatch(id)
    dispatch_data = request.get_json()
    data, error = dispatch_schema.load(dispatch_data)
    if error:
        return custom_response({"message": "Error Updating Dispatch", "error": error}, 400)
    dispatch.update(data)

    return custom_response({"message": "Dispatch successfully updated"}, 201)


@api.route('/dispatches/uid/<string:uid>', methods=['PUT'])
def update_dispatch_with_uid(uid):
    dispatch = Dispatch.get_one_dispatch_uid(uid)
    dispatch_data = request.get_json()
    data, error = dispatch_schema.load(dispatch_data)
    if error:
        return custom_response({"message": "Error Updating Dispatch", "error": error}, 400)
    dispatch.update(data)

    return custom_response({"message": "Dispatch successfully updated"}, 201)


@api.route('/dispatches/<int:id>', methods=['DELETE'])
def delete_dispatch(id):
    dispatch = Dispatch.get_one_dispatch(id)
    dispatch.delete()
    return dispatch_response({"message": "Dispatch Deleted"}, 200)


@api.route('/dispatches/parse', methods=['POST'])
def parse_json():
    json_data = request.get_json()
    url = json_data['url']
    parser = JsonParser(url)
    # transcript = parser.extract_transcript()
    dispatch = parser.create_dispatch()
    return custom_response("Dispatch successfully created. UUID: " + dispatch.text_uid, 200)


def dispatch_response(res, status_code):
    """
    Response for Dispatch Data
    :param res:
    :param status_code:
    :return:
    """
    return Response(
        mimetype="application/json",
        response=json.dumps(res),
        status=status_code
    )


def custom_response(res, status_code):
    """
    Custom Response
    :param res:
    :param status_code:
    :return:
    """
    return Response(
        mimetype="application/json",
        response=json.dumps(res),
        status=status_code
    )
