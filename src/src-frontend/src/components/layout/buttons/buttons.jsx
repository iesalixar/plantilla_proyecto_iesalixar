import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import { ThemeContext } from '../../../contexts/theme';
import './style.scss';

import { SunIcon, MoonIcon } from '../../../assest/icons/components/themeIcons';
const LoginButton = () => {
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

const ToggleButton = () => {
    const { theme, isDark, toggleTheme } = useContext(ThemeContext);
    return (
        <div onClick={toggleTheme} className='toggle-container'>{isDark ? <SunIcon className='icon' /> : <MoonIcon className='icon' />}</div>
    );
};

export { LoginButton, RegisterButton, ToggleButton };
