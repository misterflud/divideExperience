import React from "react";
import {LinkContainer} from "react-router-bootstrap/lib/ReactRouterBootstrap";
import {Nav} from "react-bootstrap";

function Article(props) {
    return (
        <div className="card mx-auto">
            <div className="card-header">
                <div>
                <LinkContainer to="/article">
                    <Nav.Link>Статья № {props.article.id}</Nav.Link>
                </LinkContainer>
                </div>
                <div>{props.article.date}</div>
            </div>
            <div className="card-body">
                <h4 className="card-title">{props.article.title}</h4>
                <div>
                    <div dangerouslySetInnerHTML={{ __html: props.article.body }} />
                </div>
            </div>
        </div>
    );
}

export default Article;