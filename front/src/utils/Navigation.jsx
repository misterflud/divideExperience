import React from "react";
import {Button, Container, Form, FormControl, Nav, Navbar} from "react-bootstrap";
import {LinkContainer} from "react-router-bootstrap/lib/ReactRouterBootstrap";
import UserProfile from "./UserProfile.jsx";

var registrationBarPoint = "";

class Navigation extends React.Component {
    render() {
        if (UserProfile.isAuthorized()) {
            registrationBarPoint =
                <LinkContainer to="/registration">
                    <Nav.Link>Регистрация нового пользователя</Nav.Link>
                </LinkContainer>;
        }
        return (
            <div>
                <Container className="p-5">
                    <Navbar bg="light" variant="light" fixed="top">
                        <Navbar.Brand href="/">DivideExperience</Navbar.Brand>
                        <Nav className="mr-auto">
                            <LinkContainer to="/login">
                                <Nav.Link>Вход</Nav.Link>
                            </LinkContainer>
                            {registrationBarPoint}
                        </Nav>
                        <Form inline>
                            <FormControl type="text" placeholder="Search" className="mr-sm-2"/>
                            <Button variant="outline-primary">Search</Button>
                        </Form>
                    </Navbar>
                </Container>
            </div>
        );
    }
}

export default Navigation;