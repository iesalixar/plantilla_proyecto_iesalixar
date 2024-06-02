import React, { useContext } from 'react';
import { ThemeContext } from '../../../contexts/themeContexts';
import { useLocation } from 'react-router-dom';

const Skeleton = ({ style, mainContent, footerContent }) => {
    const { theme } = useContext(ThemeContext);
    const location = useLocation();

    const getBackgroundColor = (path) => {
        switch (path) {
            case '/login':
                return theme.backgroundGradient;
            case '/register':
                return theme.backgroundGradient;
            default:
                return theme.colorBackground;
        }
    };
    return (
        <div className="body-container" style={{ background: getBackgroundColor(location.pathname) }}>
            <main >{mainContent}</main>
            <footer>{footerContent}</footer>
        </div>
    );
};

export default Skeleton;
