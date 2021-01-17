import React from "react";
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import {render} from 'react-dom';
import { BrowserRouter, Route, hashHistory } from 'react-router-dom';

import {AppContainer} from 'react-hot-loader';
import App from './components/App';
import './app.css';

render(
    <AppContainer>
        <BrowserRouter history={hashHistory}>
            <Route path = "/" render={props => <App {...props} />} />
        </BrowserRouter>
    </AppContainer>,
    document.getElementById('carRentalApp')
);