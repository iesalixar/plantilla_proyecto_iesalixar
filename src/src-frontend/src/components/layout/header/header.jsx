import React, { useState, useEffect, useContext } from 'react';
import { ThemeContext } from '../../../contexts/theme';
import './style.scss';

const Header = ({ leftContent, rightContent }) => {
    const { theme } = useContext(ThemeContext);
    const [scrolled, setScrolled] = useState(false);

    useEffect(() => {
        const handleScroll = () => {
            const isScrolled = window.scrollY > 0;
            setScrolled(isScrolled);
        };

        window.addEventListener('scroll', handleScroll);

        return () => {
            window.removeEventListener('scroll', handleScroll);
        };
    }, []);

    const headerStyle = {
        background: scrolled ? theme.teal2 : 'transparent',
        border: "1px",
        borderColor: theme.gray8,
    };

    return (
        <div className="header-container" style={headerStyle}>
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