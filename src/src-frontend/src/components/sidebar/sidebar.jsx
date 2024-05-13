
import './style.scss';
import { ReactComponent as ProfileIcon } from '../../assest/sidebar-icons/profile_icon.svg';
import { ReactComponent as HomeIcon } from '../../assest/sidebar-icons/home_icon.svg';
import { ReactComponent as SearchIcon } from '../../assest/sidebar-icons/search_icon.svg';
import { ReactComponent as NotificationsIcon } from '../../assest/sidebar-icons/notification_icon.svg';
import { ReactComponent as CreateIcon } from '../../assest/sidebar-icons/create_icon.svg';


const SidebarComponent = () => {
    const icons = [
        { name: 'Home', component: <HomeIcon /> },
        { name: 'Search', component: <SearchIcon /> },
        { name: 'Notifications', component: <NotificationsIcon /> },
        { name: 'Create', component: <CreateIcon /> },
        { name: 'Profile', component: <ProfileIcon /> }
    ];

    return (
        <aside>
            <div className="sidebar-container">
                {icons.map((icon, index) => (
                    <div className="icon-section" key={index}>
                        <div className='icon-btn'>{icon.component}</div>
                        <div className='txt'> {icon.name}</div>
                    </div>
                ))}
            </div>
        </aside>
    );
};

export default SidebarComponent;