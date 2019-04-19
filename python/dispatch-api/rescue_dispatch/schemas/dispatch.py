__author__ = 'vmoeykens'

from .. import ma
from ..models.dispatch import Dispatch
from marshmallow import fields


class DispatchSchema(ma.Schema):

    id = fields.Int(required=False)
    text_uid = fields.Str(required=False)
    dispatch_text = fields.Str(required=False)
    address = fields.Str(required=False)
    timestamp = fields.Str(required=False)

    class Meta:
        model = Dispatch


dispatch_schema = DispatchSchema()
dispatches_schema = DispatchSchema(many=True)
