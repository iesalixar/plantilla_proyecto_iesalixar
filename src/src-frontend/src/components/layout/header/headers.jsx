import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import { ThemeContext } from '../../../contexts/theme';

const RegisterHeader = ({ leftContent, rightContent }) => {
    const { theme } = useContext(ThemeContext);

    return (
        <div className="header-container" >
            <div className='logo-container'>
                {leftContent}
            </div>
            <div className='option-container'>
                {rightContent}
            </div>
        </div>

    );
};

const IconHeader = ({ leftContent, rightContent }) => {
    const { theme } = useContext(ThemeContext);

    return (
        <div className="header-container" >
            <div className='logo-container'>
                <Link to="/home">{leftContent}</Link>
            </div>
            <div className='option-container'>{rightContent}</div>
        </div>

    );
};
export { RegisterHeader, IconHeader };