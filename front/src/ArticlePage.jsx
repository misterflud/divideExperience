import React from 'react';
import {Button, Table} from 'react-bootstrap';
import {Form, Container } from 'react-bootstrap'

const articleUrl = "http://localhost:6002/article/get";

var getArticleUrl;
var articleBlock = "";

class ArticlePage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            value: ''
            , article: []
            , loadingArticle: false
            , hits: null
        };
        this.getArticle = this.getArticle.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.onFormSubmit = this.onFormSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    onFormSubmit(event) {
        this.formUrl(this.state.value);
        this.getArticle();
        this.printArticle();
        event.preventDefault();
    };

    formUrl(id) {
        var url = new URL(articleUrl), params = {articleId:id};
        Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
        getArticleUrl = url;
    };

    getArticle() {
        fetch(getArticleUrl).then(
            (response) => {
                response.json().then(
                    (data) => {
                        this.setState({
                            loadingArticle: true
                        });
                        this.setState({
                            article: data
                        });
                    }
                );
            }
        )
        .catch(function (error) {
            console.log('error', error)
        });
    };

    printArticle() {
        if (this.state.loadingArticle) {
            articleBlock =
                <Table>
                    <thead>
                        <tr>
                            <th>
                                {this.state.article.title}
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                        <tr>
                            <td>
                                {this.state.article.body}
                            </td>
                        </tr>
                    }
                    </tbody>
                </Table>
        } else {
            articleBlock = ""
        }
    }

    render() {
        return(
            <div>
                <div>
                    <Container className="p-5">
                        <h1>Print Article from server</h1>
                        <Form noValidate onSubmit={e => this.onFormSubmit(e)}>
                            <Form.Group controlId="formBasicEmail">
                                <Form.Control placeholder = "id article" value={this.state.value} onChange={this.handleChange}/>
                            </Form.Group>
                            <Button variant="primary" type="submit">
                                Get article by id
                            </Button>
                        </Form>
                    </Container>
                </div>
                <div>
                    <Container className="p-5">
                        {articleBlock}
                    </Container>

                </div>
            </div>
        );
    }
}
export default ArticlePage;