# Python Samples

## Python RESTful API using Flask

### Description
This API is designed to allow for the storage and modification of Emergency Dispatches that have been transcribed. 
It generates unique IDs and timestamps for every added dispatch, and implements POST, DELETE, GET, and PUT. It has the ability 
to reference dispatches by a numerical ID, or by the UID. It also contains an endpoint that allows for a 
remote JSON file to be passed in, processed, and added to the database. This is so Amazon Transcribe can 
access my API to allow the dispatch to be added when it is finished transcription. 

### Notes
- The entire API is stored in the directory ```dispatch-api```. All references to my specific databases have been replaced 
with placeholders. 
- The Python interpreter version for this project is 3.7
- Everything is run using the ```manage.py``` script.
