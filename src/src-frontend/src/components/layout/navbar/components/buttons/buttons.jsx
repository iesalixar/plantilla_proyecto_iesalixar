import React, { useContext, useState } from 'react';
import { ThemeContext } from '../../../../../contexts/ThemeProvider';

import './style.scss';

import { SunIcon, MoonIcon } from '../../../../../assest/icon/themeIcons';

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

export { ToggleButton };
