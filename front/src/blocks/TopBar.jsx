import React from "react";
import Navigation from "../utils/Navigation.jsx";


class TopBar extends React.Component {
    render() {
        return (
            <div className="TopBar">
                <Navigation/>
            </div>
        );
    }
}

export default TopBar;