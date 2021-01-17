import React from 'react';
import {Button, FormGroup, FormControl} from 'react-bootstrap';
import './login.css';
import {withRouter} from "react-router-dom";
import {WorkFlowItem} from '../util/WorkFlowItem';

import AuthService from "../auth/auth.service";

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = this.getInitialState();
        this.validateForm = this.validateForm.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.setUserName = this.setUserName.bind(this);
        this.setPassword = this.setPassword.bind(this);
        this.login = this.login.bind(this);
    }

    getInitialState() {
        return {
            userName: '',
            password: '',
            loading: false,
            message: '',
            isValidForm: true
        }
    }

    componentDidMount() {
        localStorage.removeItem("user");
    }

    validateForm() {
        return this.state.userName && this.state.password && this.state.userName.length > 0 && this.state.password.length > 0 ? true : false;
    }

    handleSubmit(event) {
        event.preventDefault();
    }

    setUserName(event) {
        this.setState({'userName': event.target.value, isValidForm: true});
    }

    setPassword(event) {
        this.setState({'password': event.target.value, isValidForm: true});
    }

    isPageLoading() {
        this.setState({
            message: "",
            loading: true
        });
    }

    login() {
        this.isPageLoading();

        if (this.validateForm()) {
            const loginRequest = {username: this.state.userName, password: this.state.password};
            const wfi = new WorkFlowItem(loginRequest);
            AuthService.login(wfi).then((response) => {
                localStorage.setItem("user", JSON.stringify(response));
                this.props.setUserState(true);
                this.props.history.push("/todo");
            }).catch((resp) => {
                this.props.setUserState(false);
            });

        } else {
            this.setState({
                loading: false, isValidForm: false
            });
        }
    }


    render() {
        const loginForm = (
            <div className="login">
                <div className="Login">
                    {!this.state.isValidForm && (
                        <span style={{color: 'red', fontSize: '11px'}}>Username and Password cannot be Empty</span>)}
                    {this.state.loading && (<span className="spinner-border spinner-border-sm"></span>)}
                    <form onSubmit={this.handleSubmit}>
                        <FormGroup controlId="userName" className="loginFormGroup">
                            <label>Username</label>
                            <FormControl
                                autoFocus
                                type="text"
                                value={this.state.userName}
                                onChange={this.setUserName}
                            />
                        </FormGroup>
                        <FormGroup controlId="password" className="loginFormGroup">
                            <label>Password</label>
                            <FormControl
                                value={this.state.password}
                                onChange={this.setPassword}
                                type="password"
                            />
                        </FormGroup>
                        <Button block className="loginButton" onClick={this.login} disabled={!this.login} type="submit">
                            Login
                        </Button>
                    </form>
                </div>
            </div>
        )
        return (loginForm);
    }
}

export default withRouter(Login);