import React from 'react';
import Pagination from 'react-bootstrap/Pagination';
import ArticleListBlock from "../blocks/ArticleListBlock.jsx";
import URIUtil from "../utils/URIUtil.jsx";

const articlesUrl = "article/all";
const startPageSize = 5;

class ArticlesBlock extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            articles: [],
            currentPage: 0
        };

        this.nextButton = this.nextButton.bind(this);
        this.previousButton = this.previousButton.bind(this);
        this.startButton = this.startButton.bind(this);
    }
    componentDidMount() {
        this.startButton();
    }

    nextButton(event) {
        this.state.currentPage += 5;
        this.getArticles(this.state.currentPage);
    }

    previousButton() {
        if (this.state.currentPage > 0) {
            this.state.currentPage -= 5;
        }
        this.getArticles(this.state.currentPage);
    }

    startButton() {
        this.getArticles(this.state.currentPage);
    }

    getArticles(currentPage) {
        fetch(URIUtil.getSiteURI(articlesUrl + "?pageSize=" + startPageSize + "&" + "currentPage=" + currentPage)
        ).then(
            (response) => {
                response.json().then(
                    (data) => {
                        this.setState({
                            articles: data
                        });
                    }
                );
            }
        )
            .catch(function (error) {
                console.log('error', error)
            });
    };

    render() {
        return (
            <div>
                <div>
                    <ArticleListBlock articles={this.state.articles}/>
                </div>
                <div className="row justify-content-md-center">
                    <Pagination size="lg">
                        <Pagination.First onClick={this.startButton}/>
                        <Pagination.Prev onClick={this.previousButton}/>
                        <Pagination.Next onClick={this.nextButton}/>
                    </Pagination>
                </div>
            </div>
        );
    }
}

export default ArticlesBlock;