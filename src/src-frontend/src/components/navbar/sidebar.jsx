import React, { useState, useEffect } from 'react';
import './style.scss';
import { ReactComponent as ProfileIcon } from '../../assest/icons/sidebar-icons/profile_icon.svg';
import { ReactComponent as HomeIcon } from '../../assest/icons/sidebar-icons/home_icon.svg';
import { ReactComponent as SearchIcon } from '../../assest/icons/sidebar-icons/search_icon.svg';
import { ReactComponent as NotificationsIcon } from '../../assest/icons/sidebar-icons/notification_icon.svg';
import { ReactComponent as CreateIcon } from '../../assest/icons/sidebar-icons/create_icon.svg';
import { ReactComponent as LogoutIcon } from '../../assest/icons/sidebar-icons/logout_icon.svg';
import { useAuth } from '../../authContext/autContext';
import Logotext from '../../components/logo/logotext';
import { Link } from 'react-router-dom';
import Logoicon from '../logo/logoIcon';

const SidebarComponent = () => {
    const { logout } = useAuth();
    const [isScreenSmall, setIsScreenSmall] = useState(window.innerWidth <= 1250);
    const handleResize = () => {
        setIsScreenSmall(window.innerWidth <= 1250);
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
        <>

            <div className="sidebar-container">

                <div className='logo-container'> <Link to="/home">{!isScreenSmall ? (<Logotext />) : <Logoicon />}</Link></div>
                {[
                    { name: 'Home', component: <HomeIcon /> },
                    { name: 'Search', component: <SearchIcon /> },
                    { name: 'Notifications', component: <NotificationsIcon /> },
                    { name: 'Create', component: <CreateIcon /> },
                    { name: 'Profile', component: <ProfileIcon /> },
                ].map((icon, index) => (
                    <div className="icon-section" key={index}>
                        <div className='icon-btn' onClick={() => handleIconClick(icon.name)}>{icon.component}</div>
                        {!isScreenSmall && <div className='txt'>{icon.name}</div>}
                    </div>
                ))}
                <div className='logout-section'>
                    <div className='icon-btn' onClick={() => handleIconClick('Logout')}><LogoutIcon /></div>
                    {!isScreenSmall && <div className='txt'>Logout</div>}
                </div>
            </div>
        </>
    );
};

export default SidebarComponent;
