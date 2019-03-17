import React from 'react';
import ArticlePage from "./ArticlePage.jsx";

const ReactDOM = require('react-dom');

class App extends React.Component {
    render() {
        return(
            <div className="App">
                <ArticlePage/>
            </div>
        );
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('app')
)
