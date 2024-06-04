import React, { useState, useEffect, useContext } from 'react';
import { Link } from 'react-router-dom';


import { ThemeContext } from '../../../contexts/ThemeProvider';
import { useAuthContext } from '../../../contexts/AuthProvider';
import { useScreenContext } from '../../../contexts/ScreenProvider';
import { useModalContext } from '../../../contexts/ModalProvider';

import ProfilePictureComponent from './components/profilePicture/profilePicture';

import { LogoiconDark, LogotextDark } from './components/icon//logo-dark';
import { LogoiconClear, LogotextClear } from './components/icon/logo-clear';

import { ToggleButton } from './components/buttons/buttons';

import { CreateClear, HomeClear, LogoutClear, NotificationClear, ProfileClear, SearchClear } from './components/icon/sidebarIcons-clear';
import { CreateDark, HomeDark, LogoutDark, NotificationDark, ProfileDark, SearchDark } from './components/icon/sidebarIcons-dark';

import './style.scss';

const SidebarComponent = ({ }) => {

    const { theme, isDark } = useContext(ThemeContext);
    const { logout, userData } = useAuthContext();
    const { openModal } = useModalContext();


    const { screenWidth } = useScreenContext();
    const [isScreenSmall, setIsScreenSmall] = useState();

    useEffect(() => {
        setIsScreenSmall(screenWidth <= 1050);
    }, [screenWidth]);

    const profileImage = userData.user.image;


    //#region ICON PROPS
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
    };

    const clearIcons = {
        Home: HomeClear,
        Search: SearchClear,
        Create: CreateClear,
        Notification: NotificationClear,
    };

    const icons = isDark ? darkIcons : clearIcons;
    //#endregion

    const handleIconClick = (name) => {
        switch (name) {
            case 'Search':
                break;
            case 'Notifications':
                break;
            case 'Create':
                openModal();
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
            <div className="sidebar-container" style={{ borderColor: theme.gray8 }}>
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
                    <div className='icon-btn'><ProfilePictureComponent source={profileImage} size={35} /></div>
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
        <div className="sidebar-container" style={{ borderColor: theme.gray8 }}>
            <div className='logo-container'>
                <Link to="/home">
                    {isDark ? <LogotextDark {...svgLogoTextProps} /> : <LogotextClear {...svgLogoTextProps} />}
                </Link>
            </div>
            <div className="icon-section">
                {Object.keys(icons).map((key) => {
                    const IconComponent = icons[key];
                    return (
                        <div className='icon-wrapper' key={key}>
                            <div className='icon-btn' onClick={() => handleIconClick(key)}>
                                <IconComponent />
                            </div>
                            <div className='icon-text' style={{ color: theme.gray11 }}>{key}</div>
                        </div>
                    );
                })}
                <div className='icon-wrapper'>
                    <div className='icon-btn'><ProfilePictureComponent source={profileImage} size={35} /></div>
                    <div className='icon-text' style={{ color: theme.gray11 }}>Profile</div>
                </div>

            </div>
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
    const { userData } = useAuthContext();
    const { theme, isDark } = useContext(ThemeContext);
    const { openModal } = useModalContext();

    const profileImage = userData.user.image;

    const darkIcons = {
        Home: HomeDark,
        Search: SearchDark,
        Create: CreateDark,
        Notification: NotificationDark,
    };

    const clearIcons = {
        Home: HomeClear,
        Search: SearchClear,
        Create: CreateClear,
        Notification: NotificationClear,
    };

    const icons = isDark ? darkIcons : clearIcons;

    const handleIconClick = (name) => {
        switch (name) {
            case 'Search':
                break;
            case 'Notifications':
                break;
            case 'Create':
                openModal();
                break;
            case 'Profile':
                window.location.href = '/perfil';
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
                <div className='icon-btn'><ProfilePictureComponent source={profileImage} size={30} /></div>
            </div>
        </div>
    );
};
export { SidebarComponent, FooterBarComponent };
