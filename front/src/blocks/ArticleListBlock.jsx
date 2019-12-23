import React from "react";

import Article from "../blocks/Article.jsx";

function ArticleListBlock(props) {
    if (props.articles != null && props.articles.length > 0) {
        return (
            <div className="container">
                {props.articles.map(a =>
                    <Article key={a.id} article={a}/>
                )}
            </div>
        );
    } else {
        return (
            <div className="container">
            </div>
        );
    }
}

export default ArticleListBlock;