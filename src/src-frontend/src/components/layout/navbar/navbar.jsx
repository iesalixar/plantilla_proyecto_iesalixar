import React, { useState, useEffect, useContext } from 'react';
import { Link } from 'react-router-dom';
import { ThemeContext } from '../../../contexts/theme';
import './style.scss'
import { useAuth } from '../../../contexts/authentication';

import { ReactComponent as ProfileIcon } from '../../../assest/icons/svg/sidebar-icons/profile_icon.svg';
import { ReactComponent as HomeIcon } from '../../../assest/icons/svg/sidebar-icons/home_icon.svg';
import { ReactComponent as SearchIcon } from '../../../assest/icons/svg/sidebar-icons/search_icon.svg';
import { ReactComponent as NotificationsIcon } from '../../../assest/icons/svg/sidebar-icons/notification_icon.svg';
import { ReactComponent as CreateIcon } from '../../../assest/icons/svg/sidebar-icons/create_icon.svg';
import { ReactComponent as LogoutIcon } from '../../../assest/icons/svg/sidebar-icons/logout_icon.svg';
import LogotextDark from '../../../assest/icons/components/logo/logotext-dark';
import LogoiconDark from '../../../assest/icons/components/logo/logoIcon-dark';
import { ToggleButton } from '../buttons/buttons';

import './style.scss';

const SidebarComponent = () => {

    const { theme } = useContext(ThemeContext);
    const { logout } = useAuth();

    const [isScreenSmall, setIsScreenSmall] = useState(false);

    const handleResize = () => {
        setIsScreenSmall(window.innerWidth <= 1050);
    };
    useEffect(() => {
        window.addEventListener('resize', handleResize);
        return () => window.removeEventListener('resize', handleResize);
    }, []);

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
        <div className="sidebar-container">
            <div className='logo-container'>
                <Link to="/home">
                    {!isScreenSmall ? <LogotextDark /> : <LogoiconDark />}
                </Link>
            </div>
            {[
                { name: 'Home', component: <HomeIcon /> },
                { name: 'Search', component: <SearchIcon /> },
                { name: 'Notifications', component: <NotificationsIcon /> },
                { name: 'Create', component: <CreateIcon /> },
                { name: 'Profile', component: <ProfileIcon /> },
                { name: 'Color mode', component: <ToggleButton /> }
            ].map((icon, index) => (
                <div className="icon-section" key={index}>
                    <div className='icon-btn' onClick={() => handleIconClick(icon.name)}>
                        {icon.component}
                    </div>
                    {!isScreenSmall && <div className='txt'>{icon.name}</div>}
                </div>
            ))}
            <div className='logout-section'>
                <div className='icon-btn' onClick={() => handleIconClick('Logout')}>
                    <LogoutIcon />
                </div>
                {!isScreenSmall && <div className='txt'>Logout</div>}
            </div>
        </div>
    );
};

const FooterBarComponent = () => {
    const { logout } = useAuth();

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
        <div className="footerbar-container">
            {[
                { name: 'Home', component: <HomeIcon /> },
                { name: 'Search', component: <SearchIcon /> },
                { name: 'Notifications', component: <NotificationsIcon /> },
                { name: 'Create', component: <CreateIcon /> },
                { name: 'Profile', component: <ProfileIcon /> },
            ].map((icon, index) => (
                <div className="icon-section" key={index}>
                    <div className='icon-btn' onClick={() => handleIconClick(icon.name)}>
                        {icon.component}
                    </div>
                </div>
            ))}
        </div>
    );
};
export { SidebarComponent, FooterBarComponent };
