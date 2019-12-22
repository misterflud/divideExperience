import React from "react";
import {Route, Switch} from "react-router";
import ArticlePage from "../views/ArticlePage.jsx";
import RegistrationPage from "../views/RegistrationPage.jsx";
import LoginPage from "../views/LoginPage.jsx";

class Routes extends React.Component {
    render() {
        return(
            <div className="Routes">
                <Switch>
                    <Route exact path='/' component={ArticlePage} />
                    <Route path='/registration' component={RegistrationPage} />
                    <Route path='/login' component={LoginPage} />
                </Switch>
            </div>
        );
    }
}

export default Routes;