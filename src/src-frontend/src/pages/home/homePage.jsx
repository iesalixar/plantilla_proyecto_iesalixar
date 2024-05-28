import React, { useState, useEffect } from 'react';
import FooterComponent from '../../components/footer/footer';
import { IconHeader } from '../../components/header/headers';
import SidebarComponent from '../../components/navbar/sidebar';
import Skeleton from '../../components/skeleton/skeleton';
import Logoicon from '../../components/logo/logoIcon';
import PublicationComponent from '../../components/publication/publication';
import FooterBarComponent from '../../components/navbar/footerBar';
import { ReactComponent as LogoutIcon } from '../../assest/icons/sidebar-icons/logout_icon.svg';

const HomePage = () => {
    const [isSmallScreen, setIsSmallScreen] = useState(false);
    const [showSidebar, setShowSidebar] = useState(true);

    const checkScreenSize = () => {
        const isSmallHeader = window.innerWidth <= 500;
        const isShowSidebar = window.innerWidth > 500;
        setIsSmallScreen(isSmallHeader);
        setShowSidebar(isShowSidebar);
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
                    {isSmallScreen ? (
                        <IconHeader
                            leftContent={<Logoicon />}
                            rightContent={<LogoutIcon />}
                        />
                    ) : (
                        <SidebarComponent />
                    )}
                    {!showSidebar && <FooterBarComponent />}
                    <PublicationComponent />
                </>
            }
            footerContent={<FooterComponent />}
        />
    );
};

export default HomePage;
