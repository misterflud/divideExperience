import React from "react";
import UserProfile from "../utils/UserProfile.jsx";
import URIUtil from "../utils/URIUtil.jsx";
import base64 from 'react-native-base64'
import {Button, Container, Form} from "react-bootstrap";

const loginUrl = "auth/login";

class LoginPage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
            isAuthorized: false
        };
        this.handleChange = this.handleChange.bind(this);
        this.authentication = this.authentication.bind(this);
    }

    componentDidMount() {
        this.setState({
            isAuthorized: UserProfile.isAuthorized()
        });
    }

    handleChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    authentication(event) {
        fetch(URIUtil.getSiteURI(loginUrl), {
            method: "GET",
            headers: {
                "Authorization": "Basic " + base64.encode(this.state.email + ":" + this.state.password)
            }
        }).then(
            (response) => {
                if (response.ok) {
                    UserProfile.authorizationProcess(response.headers.get("Authorization").replace("Bearer ", ""));
                    console.log(1);
                    console.log(UserProfile.isAuthorized());
                    this.setState({
                        isAuthorized: UserProfile.isAuthorized()
                    });
                } else {
                    UserProfile.deleteUserFromSession();
                }
            }
        ).catch(function (error) {
            UserProfile.deleteUserFromSession();
            console.log('error', error);
        });
    }

    render() {
        let loggedBlock;
        let authorizedBlock;
        if (this.state.isAuthorized) {
            loggedBlock =
                <Container className="p-5">
                    <h4>You are already logged!</h4>
                </Container>;
        } else {
            authorizedBlock =
                <div>
                    <Container className="p-5">
                        <h3>Login</h3>
                        <Form noValidate onSubmit={e => {
                            e.preventDefault();
                            this.authentication(e)
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