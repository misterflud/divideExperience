import React from 'react';
import TopBar from "./blocks/TopBar.jsx";
import Routes from "./utils/Routes.jsx";
import {BrowserRouter} from "react-router-dom";

class App extends React.Component {
    render() {
        return(
            <div className="App">
                <BrowserRouter>
                    <Routes/>
                    <TopBar/>
                </BrowserRouter>
            </div>
        );
    }
}

export default App;
