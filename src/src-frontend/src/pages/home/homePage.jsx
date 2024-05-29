import React, { useState, useEffect } from 'react';

import Skeleton from '../../components/layout/skeleton/skeleton.jsx';
import { IconHeader } from '../../components/layout/header/headers.jsx';
import { FooterBarComponent, SidebarComponent } from '../../components/layout/navbar/navbar.jsx';
import PublicationComponent from '../../components/publication/publication';
import FooterComponent from '../../components/layout/footer/footer.jsx';

import { ReactComponent as LogoutIcon } from '../../assest/icons/svg/sidebar-icons/logout_icon.svg';
import Logoicon from '../../assest/icons/components/logo/logoIcon.jsx';
import { ToggleButton } from '../../components/layout/buttons/buttons.jsx';

const HomePage = () => {
    const [isSmallScreen, setIsSmallScreen] = useState(false);

    const checkScreenSize = () => {
        setIsSmallScreen(window.innerWidth <= 770);
    };

    useEffect(() => {
        checkScreenSize();
        window.addEventListener('resize', checkScreenSize);
        return () => {
            window.removeEventListener('resize', checkScreenSize);
        };
    }, []);

    return (
        <Skeleton
            mainContent={
                <>
                    <header className="header-container">
                        {isSmallScreen ? (
                            <IconHeader leftContent={<Logoicon />} rightContent={<><ToggleButton /><LogoutIcon className='icon' /></>} />
                        ) : (
                            <SidebarComponent />
                        )}
                    </header>
                    <div className="main-content">
                        <PublicationComponent />
                    </div>
                </>
            }
            footerContent={isSmallScreen ? (<FooterBarComponent />) : <FooterComponent />}
        />
    );
};

export default HomePage;
