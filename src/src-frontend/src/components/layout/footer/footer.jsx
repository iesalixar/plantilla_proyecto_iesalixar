import React, { useState, useEffect, useContext } from 'react';
import './style.scss';
import { Link } from 'react-router-dom';
import { ThemeContext } from '../../../contexts/theme';
const FooterComponent = () => {
    const { theme } = useContext(ThemeContext);

    const [isScreenSmall, setIsScreenSmall] = useState(window.innerWidth <= 770); // Cambiado a 770px
    const handleResize = () => {
        setIsScreenSmall(window.innerWidth <= 770); // Cambiado a 770px
    };
    useEffect(() => {
        window.addEventListener('resize', handleResize);
        return () => window.removeEventListener('resize', handleResize);
    }, []);

    // Condición para renderizar el componente Footer solo si la pantalla es mayor o igual a 770px
    if (isScreenSmall) {
        return null; // Retorna null si la pantalla es menor de 770px
    }

    return (
        <div className="footer-container" style={{ color: ThemeContext.gray11 }}>
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
