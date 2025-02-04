import React from 'react';

const Header = ({ username, onLogout }) => (
    <div className="header">
        <h1 style={{width:"100%", display:"flex", justifyContent: "center"}}>|^^|_{username}_|^^|</h1>
        <button onClick={onLogout} className="logout-button">
            Logout
        </button>

    </div>
);

export default Header;

