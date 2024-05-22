import React from 'react';
import { Link } from 'react-router-dom';
import './style.scss';
const LogingButton = () => {
    return (
        <Link to="/login">
            <button className='header-btn'><p>Login</p></button>
        </Link>
    );
};

const RegisterButton = () => {
    return (
        <Link to="/register">
            <button className='header-btn'><p>Register</p></button>
        </Link>
    );
};

export { LogingButton, RegisterButton };
