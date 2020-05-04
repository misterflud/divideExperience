import React from "react";
import {Button, Container, Form, FormControl, Nav, Navbar} from "react-bootstrap";
import {LinkContainer} from "react-router-bootstrap/lib/ReactRouterBootstrap";
import UserProfile from "./UserProfile.jsx";

class Navigation extends React.Component {

    constructor(props) {
        super(props);
        this.logout = this.logout.bind(this);
    }

    logout() {
        UserProfile.deleteUserFromSession();
        window.location.reload();
    }

    render() {
        let registrationBarPoint;
        let editArticleBarPoint;
        let logOutButton;
        let loginPage;
        if (UserProfile.isAuthorized()) {
            registrationBarPoint =
                <LinkContainer to="/registration">
                    <Nav.Link>Регистрация нового пользователя</Nav.Link>
                </LinkContainer>;
            editArticleBarPoint =
                <LinkContainer to="/editor">
                    <Nav.Link>Редактор статей</Nav.Link>
                </LinkContainer>;
            logOutButton =
                <Button variant="primary" type="button" onClick={() => this.logout()}>
                    Выйти
                </Button>
        } else {
            loginPage =
                <LinkContainer to="/login">
                    <Nav.Link>Вход</Nav.Link>
                </LinkContainer>
        }
        return (
            <div>
                <Container className="p-5">
                    <Navbar bg="light" variant="light" fixed="top">
                        <Navbar.Brand href="/">DivideExperience</Navbar.Brand>
                        <Nav className="mr-auto">
                            {loginPage}
                            {registrationBarPoint}
                            {editArticleBarPoint}
                        </Nav>
                        <Form inline>
                            <FormControl type="text" placeholder="Search" className="mr-sm-2"/>
                            <Button variant="outline-primary">Search</Button>
                        </Form>
                        {logOutButton}
                    </Navbar>
                </Container>
            </div>
        );
    }
}

export default Navigation;