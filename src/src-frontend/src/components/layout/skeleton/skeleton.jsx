import React, { useContext } from 'react';
import { ThemeContext } from '../../../contexts/theme';

const Skeleton = ({ mainContent, footerContent }) => {
    const { theme } = useContext(ThemeContext);
    return (
        <div className="body-container" style={{ background: theme.backgroundGradient }}>
            <main >{mainContent}</main>
            <footer>{footerContent}</footer>
        </div>
    );
};

export default Skeleton;
