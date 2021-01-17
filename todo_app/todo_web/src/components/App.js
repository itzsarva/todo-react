import React, {Profiler} from 'react';
import {Route} from 'react-router-dom';
import Menu from './menu/Menu';
import Login from './login/Login';
import ErrorBoundary from "./util/ErrorBoundary";
import TodoBucket from "./todo/TodoBucket";

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = this.getInitialState(props);
        this.setUserState = this.setUserState.bind(this);
    }

    getInitialState() {
        return {
            isUserLoggedIn: false
        };
    }

    setUserState(value) {
        this.setState({isUserLoggedIn: value});
    }

    render() {
        return (
            <ErrorBoundary>
                <div id="app" className="page-header">
                    <Menu isUserLoggedIn={this.state.isUserLoggedIn} setUserState={this.setUserState}/>
                    <Route exact path="/"
                           render={props => <Login history={this.props.history} setUserState={this.setUserState}
                                                   isUserLoggedIn={this.state.isUserLoggedIn}/>}/>
                    <Route exact path="/login"
                           render={props => <Login {...props} setUserState={this.setUserState}/>}/>
                    <Route path="/todo" render={props => <TodoBucket/>}/>
                </div>
            </ErrorBoundary>
        );
    }
}

export default App;