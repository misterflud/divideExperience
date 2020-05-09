import React from "react";

import URIUtil from "../utils/URIUtil.jsx";
import {Button, Container, Form} from "react-bootstrap";
import UserProfile from "../utils/UserProfile.jsx";

const staticURI = "article/static/p";
const staticProtectURI = "article/static/p";
const getStaticsURI = "article/static/s/get/sources/";

class StaticSourceBlock extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            articleId: props.articleId,
            staticUrls: [],
            file: ''
        };
        this.addStatic = this.addStatic.bind(this);
        this.getStaticList = this.getStaticList.bind(this);
        this._handleImageChange = this._handleImageChange.bind(this);
        this.deleteStatic = this.deleteStatic.bind(this);
    }

    componentDidMount() {
        this.getStaticList();
    }

    _handleImageChange(e) {
        let file = e.target.files[0];
        this.setState({
            file: file,
        });
    }

    addStatic(source) {
        let formData = new FormData();
        formData.append("file", this.state.file);
        fetch(URIUtil.getSiteURI(staticURI + "/" + this.state.articleId),
            {
                method: "POST",
                headers: {
                    // "Accept": "multipart/form-data",
                    // "Content-Type": "multipart/form-data",
                    "Authorization": "Bearer " + UserProfile.getAuthToken()
                },
                body: formData
            }
        ).then(
            (response) => {
                if (response.ok) {
                    this.getStaticList(); //запрашиваем список с сервера
                }
            }
        )
            .catch(function (error) {
                console.log('error', error);
            });
    }

    deleteStatic(item) {
        console.log(item);
        if (this.state.articleId != null && this.state.articleId !== "") {
            fetch(URIUtil.getSiteURI(staticProtectURI),
                {
                    method: "DELETE",
                    headers: {
                        "staticSource" : encodeURIComponent(item),
                        "Authorization": "Bearer " + UserProfile.getAuthToken()
                    }
                }
            ).then(
                (response) => {
                    if (response.ok) {
                        this.getStaticList();
                    }
                }
            )
                .catch(function (error) {
                    console.log('error', error);
                });
        }
    };

    getStaticList() {
        if (this.state.articleId != null && this.state.articleId !== "") {
            fetch(URIUtil.getSiteURI(getStaticsURI + this.state.articleId),
                {
                    method: "GET",
                    headers: {
                        "Authorization": "Bearer " + UserProfile.getAuthToken()
                    }
                }
            ).then(
                (response) => {
                    response.json().then(
                        (data) => {
                            if (response.ok && data != null) {
                                let array = [];
                                data.map(s => array.push(URIUtil.getSiteURI(s.uri)));
                                this.setState({
                                    staticUrls: array
                                });
                            }
                        }
                    );
                }
            )
                .catch(function (error) {
                    console.log('error', error);
                });
        }
    }

    render() {
        let items = this.state.staticUrls.map((item, key) =>
            <div key={key}>
                <ul>
                    <li key={key}>
                        {item}
                        <Button variant="dark" type="button" size="sm" onClick={() => this.deleteStatic(item)}>
                            Удалить
                        </Button>
                    </li>
                </ul>
            </div>
        );

        return (
            <div className="StaticSourceBlock">

                <h5> Статические ресурсы. </h5>
                На данный момент можно добавить 6 картинок с размером не более 6 Мб для каждой статьи.<br/>
                <br/>
                1) Загрузите картинку на сервер. <br/>
                2) Скопируйте нужную ссылку в текстовый редактор. <br/>

                <Container className="p-5">
                    <Form noValidate onSubmit={e => {
                        e.preventDefault();
                        this.addStatic(e)
                    }}>
                        <input className="fileInput"
                               type="file"
                               onChange={(e) => this._handleImageChange(e)}/>
                        <Button variant="primary" type="submit">
                            Загрузить картинку на сервер
                        </Button>
                    </Form>
                </Container>
                <div>
                    {items}
                </div>
            </div>
        );
    }
}

export default StaticSourceBlock;