import React, { useState, useEffect, useContext } from 'react';
import './style.scss';
import { Link } from 'react-router-dom';
import { ThemeContext } from '../../../contexts/ThemeProvider';
import { useScreenContext } from '../../../contexts/ScreenProvider';

const FooterComponent = () => {
    const { theme } = useContext(ThemeContext);
    const { screenWidth } = useScreenContext();

    //#region SCREEN STATE
    const [isScreenSmall, setIsScreenSmall] = useState(false);

    useEffect(() => {
        setIsScreenSmall(screenWidth <= 770);
    }, [screenWidth]);
    //#endregion

    if (isScreenSmall) {
        return null;
    }

    return (
        <div className="footer-container" style={{ color: theme.gray11 }}>
            <div className="row-1">
                <Link to="#"><span>About </span></Link>
                <Link to="#"><span>Blog </span></Link>
                <Link to=""><span>jobs </span></Link>
                <Link to="#"><span>Help </span></Link>
                <Link to="#"><span>API </span></Link>
                <Link to="#"><span>Privacy </span></Link>
                <Link to="#"><span>Terms </span></Link>
                <Link to="#"><span>Top Accounts </span></Link>
                <Link to="#"><span>locations </span></Link>
            </div>
            <div className="row-2">
                <Link to="#"><span>2024 FitConnet</span></Link>
            </div>
        </div>
    );
};

export default FooterComponent;
