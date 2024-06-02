import React, { useState, useEffect, useContext } from 'react';
import { Link } from 'react-router-dom';

import { ThemeContext } from '../../../contexts/themeContexts';
import { useAuth } from '../../../contexts/userContexts';
import { useScreen } from '../../../contexts/screenContexts';

import LogotextDark from '../../../assest/icons/components/logo/logotext-dark';
import LogotextClear from '../../../assest/icons/components/logo/logotext-clear';

import LogoiconDark from '../../../assest/icons/components/logo/logoIcon-dark';
import LogoiconClear from '../../../assest/icons/components/logo/logoicon-clear';

import { ToggleButton } from '../buttons/buttons';

import { CreateClear, HomeClear, LogoutClear, NotificationClear, ProfileClear, SearchClear } from '../../../assest/icons/components/sidebar/sidebarIcons-clear';
import { CreateDark, HomeDark, LogoutDark, NotificationDark, ProfileDark, SearchDark } from '../../../assest/icons/components/sidebar/sidebarIcons-dark';

import './style.scss';

const SidebarComponent = () => {

    const { theme, isDark } = useContext(ThemeContext);
    const { logout } = useAuth();
    const { screenWidth } = useScreen();

    const [isScreenSmall, setIsScreenSmall] = useState(false);

    useEffect(() => {
        setIsScreenSmall(screenWidth <= 1050);
    }, [screenWidth]);

    const svgLogoIconProps = {
        width: 50,
        height: 50,
        className: "logo-icon",
        viewBox: "0 0 100 100",
    };
    const svgLogoTextProps = {
        width: 190,
        height: 46,
        className: "",
        viewBox: "138 0 260.493 60",
    };
    const darkIcons = {
        Home: HomeDark,
        Search: SearchDark,
        Create: CreateDark,
        Notification: NotificationDark,
        Profile: ProfileDark,
    };

    const clearIcons = {
        Home: HomeClear,
        Search: SearchClear,
        Create: CreateClear,
        Notification: NotificationClear,
        Profile: ProfileClear,
    };


    const icons = isDark ? darkIcons : clearIcons;

    const handleIconClick = (name) => {
        switch (name) {
            case 'Search':
                break;
            case 'Notifications':
                break;
            case 'Create':
                break;
            case 'Profile':
                window.location.href = '/perfil';
                break;
            case 'Logout':
                logout();
                window.location.href = '/';
                break;
            default:
                break;
        }
    };
    if (isScreenSmall) {
        return (
            <div className="sidebar-container">
                <div className='logo-container'>
                    <Link to="/home">
                        {isDark ? <LogoiconDark {...svgLogoIconProps} /> : <LogoiconClear {...svgLogoIconProps} />}
                    </Link>
                </div>
                <div className="icon-section">
                    {Object.keys(icons).map((key, index) => {
                        const IconComponent = icons[key];
                        return (
                            <div className='icon-btn' onClick={() => handleIconClick(key)} key={index}>
                                <IconComponent />
                            </div>
                        );
                    })}
                </div>
                <div className='logout-section'>
                    <div className='toggle-section'>
                        <ToggleButton />
                    </div>
                    <div className='logout-icon' onClick={() => handleIconClick('Logout')}>
                        {isDark ? <LogoutDark /> : <LogoutClear />}
                    </div>
                </div>
            </div>
        );
    }
    return (
        <div className="sidebar-container">
            <div className='logo-container'>
                <Link to="/home">
                    {isDark ? <LogotextDark {...svgLogoTextProps} /> : <LogotextClear {...svgLogoTextProps} />}
                </Link>
            </div>
            {Object.keys(icons).map((key) => {
                const IconComponent = icons[key];
                return (
                    <div className="icon-section" key={key}>
                        <div className='icon-btn' onClick={() => handleIconClick(key)}>
                            <IconComponent />
                        </div>
                        <div className='icon-text' style={{ color: theme.gray11 }}>{key}</div>
                    </div>
                );
            })}
            <div className='logout-section'>
                <div className='toggle-section'>
                    <ToggleButton />
                </div>
                <div className='logout-icon' onClick={() => handleIconClick('Logout')}>
                    {isDark ? <LogoutDark /> : <LogoutClear />}
                </div>
            </div>
        </div >
    );
};
const FooterBarComponent = () => {
    const { logout } = useAuth();
    const { theme, isDark } = useContext(ThemeContext);

    const footerBarStyle = {
        background: theme.colorBackground,
        borderColor: theme.gray8,
    }
    const darkIcons = {
        Home: HomeDark,
        Search: SearchDark,
        Create: CreateDark,
        Notification: NotificationDark,
        Profile: ProfileDark,
    };

    const clearIcons = {
        Home: HomeClear,
        Search: SearchClear,
        Create: CreateClear,
        Notification: NotificationClear,
        Profile: ProfileClear,
    };

    const icons = isDark ? darkIcons : clearIcons;

    const handleIconClick = (name) => {
        switch (name) {
            case 'Search':
                break;
            case 'Notifications':
                break;
            case 'Create':
                break;
            case 'Profile':
                window.location.href = '/perfil';
                break;
            case 'Logout':
                logout();
                window.location.href = '/';
                break;
            default:
                break;
        }
    };

    return (
        <div className="footerbar-container" style={{ background: theme.colorBackground, borderColor: theme.gray8 }}>
            <div className="icon-container">
                {Object.keys(icons).map((key, index) => {
                    const IconComponent = icons[key];
                    return (
                        <div className='icon-item' key={index}>
                            <div className='icon-btn' onClick={() => handleIconClick(key)}>
                                <IconComponent />
                            </div>
                        </div>
                    );
                })}
            </div>
        </div>
    );
};
export { SidebarComponent, FooterBarComponent };
