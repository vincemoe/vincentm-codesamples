__author__ = 'vmoeykens'

import os

basedir = os.path.abspath(os.path.dirname(__file__))


class Config(object):
    DEBUG = False
    TESTING = False


class ProductionConfig(Config):
    POSTGRES = {
        'user': 'YOUR_USER',
        'pw': 'YOUR_PW',
        'db': 'postgres',
        'host': 'YOUR_HOST',
        'port': '5432',
    }

    SQLALCHEMY_DATABASE_URI = 'postgres://%(user)s:%(pw)s@%(host)s:%(port)s/%(db)s' % POSTGRES


class DevelopmentConfig(Config):
    DEBUG = True
    POSTGRES = {
        'user': 'YOUR_USER',
        'pw': 'YOUR_PW',
        'db': 'postgres',
        'host': 'YOUR_HOST',
        'port': '5432',
    }

    SQLALCHEMY_DATABASE_URI = 'postgres://%(user)s:%(pw)s@%(host)s:%(port)s/%(db)s' % POSTGRES


class TestingConfig(Config):
    TESTING = True
    SQLALCHEMY_DATABASE_URI = os.environ.get(
        'RESCUE_DISPATCH_TESTING_DATABASE_URI'
    )


config = {
    'production': ProductionConfig,
    'development': DevelopmentConfig,
    'testing': TestingConfig,
    'default': ProductionConfig,
}
