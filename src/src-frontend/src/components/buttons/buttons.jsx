import React from 'react';
import { Link } from 'react-router-dom';

const LogingButton = () => {
    return (
        <Link to="/login">
            <button>Login</button>
        </Link>
    );
};

const RegisterButton = () => {
    return (
        <Link to="/register">
            <button>Register</button>
        </Link>
    );
};

export { LogingButton, RegisterButton };
