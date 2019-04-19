__author__ = 'vmoeykens'

from .. import db


class Dispatch(db.Model):
    """
    Dispatch Model
    """

    # table name
    __tablename__ = 'mock_data'

    id = db.Column(db.Integer, db.Sequence("id_seq"), primary_key=True)
    text_uid = db.Column(db.VARCHAR, unique=True)
    dispatch_text = db.Column(db.VARCHAR)
    address = db.Column(db.VARCHAR)
    timestamp = db.Column(db.TIMESTAMP)

    def __init__(self, data):
        """
        Class Constructor
        :param data:
        """
        self.text_uid = data.get('text_uid')
        self.dispatch_text = data.get('dispatch_text')
        self.address = data.get('address')
        self.timestamp = data.get('timestamp')

    def save(self):
        db.session.add(self)
        db.session.commit()

    def update(self, data):
        for key, item in data.items():
            setattr(self, key, item)
        db.session.commit()

    def delete(self):
        db.session.delete(self)
        db.session.commit()

    @staticmethod
    def get_all_dispatches():
        return Dispatch.query.all()

    @staticmethod
    def get_one_dispatch(id):
        return Dispatch.query.get(id)

    @staticmethod
    def get_one_dispatch_uid(uid):
        print(Dispatch.query.filter(Dispatch.text_uid == uid).all()[0])
        return Dispatch.query.filter(Dispatch.text_uid == uid).all()[0]

    def __repr__(self):
        return 'Dispatch {}>'.format(self.id)

