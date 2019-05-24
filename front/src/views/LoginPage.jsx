import React from "react";
import UserProfile from "../utils/UserProfile.jsx";
import URIUtil from "../utils/URIUtil.jsx";
import base64 from 'react-native-base64'
import {Button, Container, Form} from "react-bootstrap";

const loginUrl = "auth/login";

var authorizedBlock = "";
var loggedBlock = "";

class LoginPage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            email: ''
            , password: ''
        };
        this.handleChange = this.handleChange.bind(this);
        this.loginButtonSubmit = this.loginButtonSubmit.bind(this);
        this.sendInformationToServer = this.sendInformationToServer.bind(this);
    }

    handleChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    loginButtonSubmit(event) {
        this.sendInformationToServer(this.state.email, this.state.password);
        this.printResult();
    }

    sendInformationToServer(email, password) {
        fetch(URIUtil.getSiteURI(loginUrl), {
            method: "GET",
            headers: {
                "Authorization": "Basic " + base64.encode(email + ":" + password)
            }
        }).then(
            (response) => {
                if (response.ok) {
                    UserProfile.authorizationProcess(response.headers.get("Authorization").replace("Bearer ", ""));
                } else {
                    UserProfile.deleteUserFromSession();
                }
            }
        ).catch(function (error) {
            UserProfile.deleteUserFromSession();
            console.log('error', error);
        });
    }

    printResult() {
        if (UserProfile.isAuthorized()) {
            loggedBlock =
                <Container className="p-5">
                    <h4>You are already logged!</h4>
                </Container>;
            authorizedBlock = ""
        } else {
            loggedBlock = "";
            authorizedBlock =
                <div>
                    <Container className="p-5">
                        <h3>Login</h3>
                        <Form noValidate onSubmit={e => {
                            e.preventDefault();
                            this.loginButtonSubmit(e)
                        }}>
                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>Email address</Form.Label>
                                <Form.Control placeholder="name@example.com" type="email" name="email"
                                              value={this.state.email} onChange={this.handleChange}/>
                            </Form.Group>
                            <Form.Group controlId="formBasicPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control placeholder="Password" type="password" name="password"
                                              value={this.state.password} onChange={this.handleChange}/>
                            </Form.Group>
                            <Button variant="primary" type="submit">
                                Login
                            </Button>
                        </Form>
                    </Container>
                </div>
        }
    }

    render() {
        if (UserProfile.isAuthorized()) {
            loggedBlock =
                <Container className="p-5">
                    <h4>You are already logged!</h4>
                </Container>;
            authorizedBlock = "";
        } else {
            authorizedBlock =
                <div>
                    <Container className="p-5">
                        <h3>Login</h3>
                        <Form noValidate onSubmit={e => {
                            e.preventDefault();
                            this.loginButtonSubmit(e)
                        }}>
                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>Email address</Form.Label>
                                <Form.Control placeholder="name@example.com" type="email" name="email"
                                              value={this.state.email} onChange={this.handleChange}/>
                            </Form.Group>
                            <Form.Group controlId="formBasicPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control placeholder="Password" type="password" name="password"
                                              value={this.state.password} onChange={this.handleChange}/>
                            </Form.Group>
                            <Button variant="primary" type="submit">
                                Login
                            </Button>
                        </Form>
                    </Container>
                </div>;
            loggedBlock = "";
        }
        return (
            <div>
                {authorizedBlock}
                {loggedBlock}
            </div>
        )
    }
}

export default LoginPage;