__author__ = 'vmoeykens'

import urllib.request
import json
import datetime
from ..models.dispatch import Dispatch
import uuid


class JsonParser:

    def __init__(self, url):
        self.url = url
        self.json_object = self.parse_file()

    def parse_file(self):
        with urllib.request.urlopen(self.url) as url:
            data = json.loads(url.read().decode())
        json_object = data
        return json_object

    def extract_transcript(self):
        return self.json_object['results']['transcripts'][0]['transcript']

    def create_dispatch(self):
        transcription = self.extract_transcript()
        dispatch_uuid = str(uuid.uuid4())
        dispatch = Dispatch(
            {"text_uid": dispatch_uuid, "dispatch_text": transcription, "address": "",
             "timestamp": datetime.datetime.now()})
        dispatch.save()
        return dispatch
