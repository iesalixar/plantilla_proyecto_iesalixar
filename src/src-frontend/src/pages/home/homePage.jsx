import React, { useState, useEffect, useContext } from 'react';

import { ThemeContext } from '../../contexts/theme';
import { useScreen } from '../../contexts/screen.js';
import { useAuth } from '../../contexts/authentication.js';

import Skeleton from '../../components/layout/skeleton/skeleton.jsx';
import { Header } from '../../components/layout/header/header.jsx';
import { FooterBarComponent, SidebarComponent } from '../../components/layout/navbar/navbar.jsx';
import PublicationComponent from '../../components/publication/publication';
import FooterComponent from '../../components/layout/footer/footer.jsx';
import { ToggleButton } from '../../components/layout/buttons/buttons.jsx';

import LogoiconDark from '../../assest/icons/components/logo/logoIcon-dark.jsx';
import LogoiconClear from '../../assest/icons/components/logo/logoicon-clear';

import { LogoutClear } from '../../assest/icons/components/sidebar/sidebarIcons-clear.jsx';
import { LogoutDark } from '../../assest/icons/components/sidebar/sidebarIcons-dark.jsx';

const HomePage = () => {
    // CONTEXTS
    const { theme, isDark } = useContext(ThemeContext);
    const { screenWidth } = useScreen();
    const { logout } = useAuth();

    //#region SCREEN STATE
    const [isScreenSmall, setIsScreenSmall] = useState(false);

    const handleResize = () => {
        setIsScreenSmall(screenWidth <= 770);
    };

    useEffect(() => {
        handleResize();
        window.addEventListener('resize', handleResize);
        return () => window.removeEventListener('resize', handleResize);
    }, [screenWidth]);
    //#endregion

    const handleLogoutClick = () => {
        logout();
        window.location.href = '/';
    };

    const svgLogoIconProps = {
        width: 35,
        height: 35,
        className: "logo-icon-header",
        viewBox: "0 0 100 100",
    };

    const Logout = isDark ? <LogoutDark /> : <LogoutClear />
    const headerLeftContent = isDark ? <LogoiconDark {...svgLogoIconProps} /> : <LogoiconClear  {...svgLogoIconProps} />;


    if (isScreenSmall) {
        return (
            <Skeleton
                mainContent={
                    <>
                        <Header
                            leftContent={headerLeftContent}
                            rightContent={
                                <>
                                    <ToggleButton />
                                    <div className='icon-btn' onClick={handleLogoutClick}>{Logout}</div>
                                </>}
                        />
                        <div className="main-content">
                            <PublicationComponent />
                        </div>
                    </>
                }
                footerContent={<FooterBarComponent />}
            />
        );
    }

    return (
        <Skeleton
            mainContent={
                <>
                    <SidebarComponent />
                    <div className="main-content">
                        <PublicationComponent />
                    </div>
                </>
            }
            footerContent={<FooterComponent />}
        />
    );
};

export default HomePage;
