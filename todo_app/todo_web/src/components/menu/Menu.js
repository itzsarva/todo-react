import React from 'react';
import {Nav} from 'react-bootstrap'
import {Link} from 'react-router-dom';
import './menu.css';
import {withRouter} from "react-router-dom";

const Menu = ({setUserState, isUserLoggedIn, history}) => {

    const logout = () => {
        localStorage.clear();
        setUserState(false);
        history.push("/login");
    }

    const routing = (
        <div>
            <Nav className="navbar navbar-expand-lg jumbotron menu">
                <ul className="navbar-nav mr-auto">
                    {isUserLoggedIn && <li><Link to={'/todo'} className="nav-link"> Todos </Link></li>}
                </ul>
                {(!!isUserLoggedIn) &&
                <button className="logoutButton" to={'/logout'} onClick={logout}>Logout</button>}
            </Nav>
        </div>
    )
    return (routing);
}

export default withRouter(Menu);