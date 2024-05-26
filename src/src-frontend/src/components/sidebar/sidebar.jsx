import React, { useState, useEffect } from 'react';
import './style.scss';
import { ReactComponent as ProfileIcon } from '../../assest/sidebar-icons/profile_icon.svg';
import { ReactComponent as HomeIcon } from '../../assest/sidebar-icons/home_icon.svg';
import { ReactComponent as SearchIcon } from '../../assest/sidebar-icons/search_icon.svg';
import { ReactComponent as NotificationsIcon } from '../../assest/sidebar-icons/notification_icon.svg';
import { ReactComponent as CreateIcon } from '../../assest/sidebar-icons/create_icon.svg';
import { ReactComponent as LogoutIcon } from '../../assest/sidebar-icons/logout_icon.svg';
import { useAuth } from '../../authContext/autContext';


const SidebarComponent = () => {
    const { logout } = useAuth();
    const [isScreenSmall, setIsScreenSmall] = useState(window.innerWidth <= 500);
    const handleResize = () => {
        setIsScreenSmall(window.innerWidth <= 500);
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
        <aside>
            <div className="sidebar-container">
                {[
                    { name: 'Home', component: <HomeIcon /> },
                    { name: 'Search', component: <SearchIcon /> },
                    { name: 'Notifications', component: <NotificationsIcon /> },
                    { name: 'Create', component: <CreateIcon /> },
                    { name: 'Profile', component: <ProfileIcon /> },
                    { name: 'Logout', component: <LogoutIcon /> }
                ].map((icon, index) => (
                    <div className="icon-section" key={index}>
                        <div className='icon-btn' onClick={() => handleIconClick(icon.name)}>{icon.component}</div>
                        {!isScreenSmall && <div className='txt'>{icon.name}</div>}
                    </div>
                ))}
            </div>
        </aside>
    );
};

export default SidebarComponent;
