import React from "react";
import {Button, Container, Form} from "react-bootstrap";


const registrationUrl = "http://localhost:7002/auth/registration";

var registeredBlock = "";

class RegistrationPage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            email: ''
            , password: ''
            , firstName: ''
            , nickName: ''
            , thirdName: ''
            , secondName: ''
            , registered: false
        };

        this.handleChange = this.handleChange.bind(this);
        this.registrationButtonSubmit = this.registrationButtonSubmit.bind(this);
        this.printResult = this.printResult.bind(this);
    }


    handleChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    registrationButtonSubmit(event) {
        this.sendInformationToServer();
        this.printResult();
    }

    sendInformationToServer() {
        fetch(registrationUrl).then(
            (response) => {
                if (response.ok) {
                    this.setState({
                        registered: true
                    });
                }
            }
        ).catch(function (error) {
            console.log('error', error)
        });
    }

    printResult() {
        if (this.state.registered) {
            registeredBlock =
                <h2>Registration is successful!</h2>
        }
    }

    render() {
        return (
            <div>
                <Container className="p-5">
                    <h3>Registration</h3>
                    <h5>Hello anon! If you want to stay comments then type some information about yourself.</h5>
                    <Form noValidate onSubmit={e => {
                        e.preventDefault();
                        this.registrationButtonSubmit(e)
                    }}>
                        <Form.Group controlId="formBasicEmail">
                            <Form.Label>Email address</Form.Label>
                            <Form.Control placeholder="name@example.com" name="email" type="email"
                                          value={this.state.email} onChange={this.handleChange}/>
                        </Form.Group>
                        <Form.Group controlId="formBasicPassword">
                            <Form.Label>Password</Form.Label>
                            <Form.Control placeholder="Password" name="password" type="password"
                                          value={this.state.password} onChange={this.handleChange}/>
                        </Form.Group>
                        <Form.Group controlId="formBasicInformation">
                            <Form.Label>Nickname</Form.Label>
                            <Form.Control type="text" name="nickName" value={this.state.nickName}
                                          onChange={this.handleChange}/>
                            <Form.Label>First name</Form.Label>
                            <Form.Control type="text" name="firstName" value={this.state.firstName}
                                          onChange={this.handleChange}/>
                            <Form.Label>Second name</Form.Label>
                            <Form.Control type="text" name="secondName" value={this.state.secondName}
                                          onChange={this.handleChange}/>
                            <Form.Label>Third name</Form.Label>
                            <Form.Control type="text" name="thirdName" value={this.state.thirdName}
                                          onChange={this.handleChange}/>
                        </Form.Group>
                        <Button variant="primary" type="submit">
                            Registration
                        </Button>
                    </Form>
                </Container>
                <Container className="p-5">
                    {registeredBlock}
                </Container>
            </div>
        );
    }
}

export default RegistrationPage;