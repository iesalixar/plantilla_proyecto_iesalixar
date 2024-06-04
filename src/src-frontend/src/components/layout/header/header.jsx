import React, { useContext } from 'react';
import { useLocation } from 'react-router-dom';

import { ThemeContext } from '../../../contexts/ThemeProvider';
import { useScreenContext } from '../../../contexts/ScreenProvider';
import './style.scss';

const Header = ({ style, leftContent, rightContent }) => {
    const { theme } = useContext(ThemeContext);
    const { isScrolling } = useScreenContext();
    const location = useLocation();

    const getBackgroundColor = (path) => {
        switch (path) {
            case '/login':
            case '/register':
                return {
                    background: isScrolling ? theme.gray2 : 'transparent',
                    borderColor: theme.gray8,
                };
            default:
                return {
                    background: theme.colorBackground,
                    borderColor: theme.gray8,

                };
        }
    };

    const headerStyle = getBackgroundColor(location.pathname);

    return (
        <div className="header-container" style={{ ...style, ...headerStyle }}>
            <div className='logo-container'>
                {leftContent}
            </div>
            <div className='option-container'>
                {rightContent}
            </div>
        </div>
    );
};

export { Header };