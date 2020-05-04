import React from "react";
import {Route, Switch} from "react-router";
import MainPage from "../views/MainPage.jsx";
import RegistrationPage from "../views/RegistrationPage.jsx";
import LoginPage from "../views/LoginPage.jsx";
import ArticleEditorPage from "../views/ArticleEditorPage.jsx";
import ArticlePage from "../views/ArticlePage.jsx";

class Routes extends React.Component {
    render() {
        return (
            <div className="Routes">
                <Switch>
                    <Route exact path='/' component={MainPage}/>
                    <Route path='/registration' component={RegistrationPage}/>
                    <Route path='/login' component={LoginPage}/>
                    <Route path='/editor' component={ArticleEditorPage}/>
                    <Route path='/article' component={ArticlePage}/>
                </Switch>
            </div>
        );
    }
}

export default Routes;