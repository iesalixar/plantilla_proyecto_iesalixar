import React, { useState, useEffect, useContext } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';


import { ThemeContext } from '../../../contexts/ThemeProvider';
import { useAuthContext } from '../../../contexts/AuthProvider';
import { useScreenContext } from '../../../contexts/ScreenProvider';
import { useModalContext } from '../../../contexts/ModalProvider';

import ProfilePictureComponent from './components/profilePicture/profilePicture';

import { LogoiconDark, LogotextDark } from '../../../assest/icon/logo-dark';
import { LogoiconClear, LogotextClear } from '../../../assest/icon/logo-clear';

import { ToggleButton } from './components/buttons/buttons';

import { CreateClear, HomeClear, LogoutClear, NotificationClear, ProfileClear, SearchClear } from '../../../assest/icon/sidebarIcons-clear';
import { CreateDark, HomeDark, LogoutDark, NotificationDark, ProfileDark, SearchDark } from '../../../assest/icon/sidebarIcons-dark';

import './style.scss';

const SidebarComponent = ({ }) => {

    const { theme, isDark } = useContext(ThemeContext);
    const { logout, userData } = useAuthContext();
    const { openModal } = useModalContext();
    const handleOpenAddModal = () => openModal('addModal');

    const location = useLocation();
    const navigate = useNavigate();

    const profileImage = userData.user.image;

    const { screenWidth } = useScreenContext();
    const [isScreenSmall, setIsScreenSmall] = useState();

    useEffect(() => {
        setIsScreenSmall(screenWidth <= 1050);
    }, [screenWidth]);

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
            case 'home':
                location.pathname = '/' ? window.location.reload : navigate('/');
                break;
            case 'Search':
                break;
            case 'Notifications':
                break;
            case 'Create':
                handleOpenAddModal();
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
                            <div className='icon-btn' onClick={() => handleIconClick(key)} key={index} style={{ borderColor: theme.gray8, backgroundColor: theme.grayA2 }}>
                                <IconComponent />
                            </div>
                        );
                    })}
                    <div className='icon-btn' style={{ borderColor: theme.gray8, backgroundColor: theme.grayA2 }}><ProfilePictureComponent source={profileImage} size={35} /></div>
                </div>
                <div className='logout-section'>
                    <div className='toggle-section'>
                        <ToggleButton />
                    </div>
                    <div className='logout-icon' onClick={() => handleIconClick('Logout')} style={{ borderColor: theme.gray8 }}>
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
                        <div className='icon-wrapper' key={key} style={{ borderColor: theme.gray8, backgroundColor: theme.grayA2 }}>
                            <div className='icon-btn' onClick={() => handleIconClick(key)}>
                                <IconComponent />
                            </div>
                            <div className='icon-text' style={{ color: theme.gray11 }}>{key}</div>
                        </div>
                    );
                })}
                <div className='icon-wrapper' style={{ borderColor: theme.gray8, backgroundColor: theme.grayA2 }}>
                    <div className='icon-btn'><ProfilePictureComponent source={profileImage} size={35} /></div>
                    <div className='icon-text' style={{ color: theme.gray11 }}>{userData.user.name}</div>
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
    const handleOpenAddModal = () => openModal('addModal');
    const location = useLocation();
    const navigate = useNavigate();
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
            case 'home':
                location.pathname = '/' ? window.location.reload : navigate('/');
                break;
            case 'Search':
                break;
            case 'Notifications':
                break;
            case 'Create':
                handleOpenAddModal();
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
