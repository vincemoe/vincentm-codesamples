#! /usr/bin/env python

__author__ = 'vmoeykens'

import os

from flask_script import Manager

from flask_cors import CORS

from rescue_dispatch import create_app, db


app = create_app(os.getenv('RESCUE_DISPATCH_CONFIG', 'default'))
CORS(app)
manager = Manager(app)


@manager.shell
def make_shell_context():
    return dict(app=app, db=db)


if __name__ == '__main__':
    manager.run()
