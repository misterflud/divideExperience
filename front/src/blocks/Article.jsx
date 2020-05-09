import React from "react";
import {LinkContainer} from "react-router-bootstrap/lib/ReactRouterBootstrap";
import {Col, Container, Nav, Row} from "react-bootstrap";
import './Article.css';

function Article(props) {
    return (
        <div className="card mx-auto">
            <Container className="card mx-auto">
                <Row className="card-header">
                    <Col md={4}>
                        <LinkContainer to="/article">
                            <Nav.Link>Статья № {props.article.id}</Nav.Link>
                        </LinkContainer>
                    </Col>
                    <Col md={{span: 4, offset: 4}} class="align-content-xl-center">
                        <div className="nav-link">
                            <div className="text-right">
                                {props.article.date}
                            </div>
                        </div>
                    </Col>
                </Row>
                <Row className="card-body">
                    <div>
                        <h4 className="card-title">{props.article.title}</h4>
                        <div className="divide_hidden" dangerouslySetInnerHTML={{__html: props.article.body}}/>
                    </div>
                </Row>
            </Container>
        </div>

    );
}

export default Article;