import React, { useContext } from 'react';
import { ThemeContext } from '../../../contexts/ThemeProvider';
import { useLocation } from 'react-router-dom';

const Skeleton = ({ mainContent, footerContent, currentPath }) => {
    const { theme } = useContext(ThemeContext);

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
        <div className="body-container" style={{ background: getBackgroundColor(currentPath) }}>
            <main>{mainContent}</main>
            <footer>{footerContent}</footer>
        </div>
    );
};

export default Skeleton;