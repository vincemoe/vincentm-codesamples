import React, { Component } from 'react';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";
import './App.css';
import Home from './components/Home';
import Dispatch from './components/Dispatch';

class App extends Component {
  render() {
    return (
        <Router>
            <div>
                <Navbar bg="dark" variant="dark">
                    <Navbar.Brand href="#">Dispatch Viewer</Navbar.Brand>
                        <Nav className="mr-auto">
                            <Nav.Link href="#"><Link to="/">Home</Link></Nav.Link>
                        </Nav>
                </Navbar>
                <Switch>
                    <Route path="/" exact component={Home} />
                    <Route path="/dispatch/:uid" component={Dispatch} /> } />
                    <Route component={NoMatch} />
                </Switch>
            </div>
        </Router>
    );
  }
}

export default App;

function NoMatch({ location }) {
    return (
        <div>
            <h3>
                No match for <code>{location.pathname}</code>
            </h3>
        </div>
    );
}
