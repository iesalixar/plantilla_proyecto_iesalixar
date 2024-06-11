import React, { useContext, useState } from 'react';
import { Link } from 'react-router-dom';
import { ThemeContext } from '../../../../contexts/ThemeProvider';

import './style.scss';

import { SunIcon, MoonIcon } from '../../../../assest/icon/themeIcons';

const LoginButton = () => {
    const { theme } = useContext(ThemeContext);

    return (
        <Link to="/login">
            <button className='header-btn' style={{ background: theme.teal11, color: theme.gray7, borderColor: theme.gray6 }}><p style={{ color: theme.gray7 }}>Login</p></button>
        </Link>
    );
};

const RegisterButton = () => {
    const { theme } = useContext(ThemeContext);

    return (
        <Link to="/register">
            <button className='header-btn' style={{ background: theme.teal11, color: theme.gray7, borderColor: theme.gray6 }}><p style={{ color: theme.gray7 }}>Register</p></button>
        </Link>
    );
};

const ToggleButton = () => {
    const { theme, isDark, toggleTheme } = useContext(ThemeContext);
    const [isHovered, setIsHovered] = useState(false);
    const handleMouseEnter = () => {
        setIsHovered(true);
    };

    const handleMouseLeave = () => {
        setIsHovered(false);
    };

    return (
        <div
            onClick={toggleTheme}
            className={`toggle-container ${isHovered ? 'hovered' : ''}`}
            onMouseEnter={handleMouseEnter}
            onMouseLeave={handleMouseLeave}
        >
            {isDark ? <SunIcon className='icon' style={{ color: theme.teal11 }} /> : <MoonIcon className='icon' style={{ color: theme.teal11 }} />}
        </div>
    );
};

export { LoginButton, RegisterButton, ToggleButton };
