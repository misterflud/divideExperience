import React from 'react';
import CKEditor from 'ckeditor4-react';
import {Button, Container, Form} from "react-bootstrap";
import URIUtil from "../utils/URIUtil.jsx";
import UserProfile from "../utils/UserProfile.jsx";
import StaticSourceBlock from "../blocks/StaticSourceBlock.jsx";

const startNewArticle = "article/p/write_article";
const addArticleUrl = "article/p/add";
const saveArticleUrl = "article/p/save";

class ArticleEditorPage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            articleId: "",
            articleTitle: "",
            articleBody: "",
            resultAdding: ""
        };
        this.handleChange = this.handleChange.bind(this);
        this.onEditorChange = this.onEditorChange.bind(this);
        this.addSubmit = this.addSubmit.bind(this);
        this.saveSubmit = this.saveSubmit.bind(this);
        this.createNewOrGetOldArticle = this.createNewOrGetOldArticle.bind(this);
    }

    componentDidMount() {
        this.createNewOrGetOldArticle();
    }

    onEditorChange(evt) {
        this.setState({
            articleBody: evt.editor.getData()
        });
    }

    handleChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    createNewOrGetOldArticle() {
        fetch(URIUtil.getSiteURI(startNewArticle),
            {
                method: "GET",
                headers: {
                    "Authorization": "Bearer " + UserProfile.getAuthToken()
                }
            }
        ).then(response => response.json()
        ).then((result) => {
                this.setState({
                    articleId: result.id,
                    articleTitle: result.title != null ? result.title : "",
                    articleBody: result.body != null ? result.body : ""
                });
                console.log(this.state.articleBody);
            }
        ).catch(function (error) {
            console.log(error);
        });
    }

    addSubmit() {
        fetch(URIUtil.getSiteURI(addArticleUrl),
            {
                method: "POST",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + UserProfile.getAuthToken()
                },
                body: JSON.stringify({
                    id: this.state.articleId,
                    title: this.state.articleTitle,
                    body: this.state.articleBody,
                    authorItem: {}
                })
            }
        ).then(
            (response) => {
                if (response.ok) {
                    this.setState({
                        resultAdding: "Adding article is successful!"
                    });
                    window.location.reload();
                }
            }
        ).catch(function (error) {
            console.log(error);
            this.setState({
                resultAdding: "Alert! Adding article is NOT successful!"
            });
        });
    };

    saveSubmit() {
        fetch(URIUtil.getSiteURI(saveArticleUrl),
            {
                method: "POST",
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + UserProfile.getAuthToken()
                },
                body: JSON.stringify({
                    id: this.state.articleId,
                    title: this.state.articleTitle,
                    body: this.state.articleBody,
                    authorItem: {}
                })
            }
        ).then(
            (response) => {
                if (response.ok) {
                    this.setState({
                        resultAdding: "Saving article is successful!"
                    });
                }
            }
        ).catch(function (error) {
            console.log('error', error);
            this.setState({
                resultAdding: "Alert! Saving article is NOT successful!"
            });
        });
    };

    render() {
        let resultBlock = <h2>{this.state.resultAdding}</h2>;
        return (
            <Container className="p-8">
                <div className="ArticleEditor">
                    <div>
                        {
                            this.state.articleId ?
                                <div className="StaticLoaderBlock">
                                    <StaticSourceBlock articleId={this.state.articleId}/>
                                </div>
                                :
                                <div>Loading</div>
                        }
                    </div>
                    <h2>CKEditor 4</h2>
                    <div>
                        <Form.Control type="text" name="articleTitle" value={this.state.articleTitle}
                                      onChange={this.handleChange}/>
                        <CKEditor
                            onBeforeLoad={(CKEDITOR) => (CKEDITOR.disableAutoInline = true)}
                            onChange={this.onEditorChange}
                            data={this.state.articleBody}
                            // config={{
                            //     extraPlugins: 'easyimage'
                            // }}
                        />
                    </div>
                    <div>
                        <Button variant="primary" type="submit" onClick={() => this.addSubmit()}>
                            Добавить статью
                        </Button>
                        <Button variant="primary" type="submit" onClick={() => this.saveSubmit()}>
                            Сохранить, чтобы можно было вернуться к редактированию позже.
                        </Button>
                    </div>
                    <div>
                        {resultBlock}
                    </div>
                </div>
            </Container>
        );
    }
}

export default ArticleEditorPage;