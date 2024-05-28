import React, { useState, useEffect } from 'react';
import FooterComponent from '../../components/footer/footer';
import { IconHeader } from '../../components/header/headers';
import Skeleton from '../../components/skeleton/skeleton';
import Logoicon from '../../components/logo/logoIcon';
import PublicationComponent from '../../components/publication/publication';
import { FooterBarComponent, SidebarComponent } from '../../components/navbar/navbar.jsx';
import { ReactComponent as LogoutIcon } from '../../assest/icons/sidebar-icons/logout_icon.svg';

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
                            <IconHeader leftContent={<Logoicon />} rightContent={<LogoutIcon />} />
                        ) : (
                            <SidebarComponent />
                        )}
                    </header>
                    <div className="main-content">
                        <PublicationComponent />
                    </div>
                </>
            }
            footerContent={<FooterBarComponent />}
        />
    );
};

export default HomePage;
