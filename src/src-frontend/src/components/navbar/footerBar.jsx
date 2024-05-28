import React from 'react';
import './style.scss';
import { ReactComponent as ProfileIcon } from '../../assest/icons/sidebar-icons/profile_icon.svg';
import { ReactComponent as HomeIcon } from '../../assest/icons/sidebar-icons/home_icon.svg';
import { ReactComponent as SearchIcon } from '../../assest/icons/sidebar-icons/search_icon.svg';
import { ReactComponent as NotificationsIcon } from '../../assest/icons/sidebar-icons/notification_icon.svg';
import { ReactComponent as CreateIcon } from '../../assest/icons/sidebar-icons/create_icon.svg';
//import { ReactComponent as LogoutIcon } from '../../assest/icons/sidebar-icons/logout_icon.svg';
import { useAuth } from '../../authContext/autContext';


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
        <>
            <div className="footerbar-container">
                {[
                    { name: 'Home', component: <HomeIcon /> },
                    { name: 'Search', component: <SearchIcon /> },
                    { name: 'Notifications', component: <NotificationsIcon /> },
                    { name: 'Create', component: <CreateIcon /> },
                    { name: 'Profile', component: <ProfileIcon /> },
                ].map((icon, index) => (
                    <div className="icon-section" key={index}>
                        <div className='icon-btn' onClick={() => handleIconClick(icon.name)}>{icon.component}</div>
                    </div>
                ))}
            </div>
        </>
    );
};

export default FooterBarComponent;
