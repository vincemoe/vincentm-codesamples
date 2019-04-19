import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import config from 'react-global-configuration';
import configuration from './config/config';
import * as serviceWorker from './serviceWorker';

config.set(configuration);

ReactDOM.render(<App />, document.getElementById('root'));

serviceWorker.unregister();
