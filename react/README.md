# Javascript/React Samples

## React Webapp

### Description
This program is a simple Node.js/React web app that is meant to be a viewer and basic editor
for the API in the python section of my code samples. This application pulls every dispatch
and displays them in a table on the main page. It then uses a router to handle url calls
for specific UIDs, and displays their data (dispatch text, address and timestamp), on the page.
You also have the ability to modify the dispatch text. The dispatch location is displayed using
a google maps react library. Finally, the location is geocoded using a google geocoding API
and React wrapper. 

### Notes
- The entire app is stored in the directory ```dispatch-viewer```.
- This project was bootstrapped using ```create-react-app```
   - To run this project you can use the following commands:
     - Install: ```npm install```
     - Run: ```npm start``` 
- All specific references to API keys or API URLs have been replaced with placeholders
