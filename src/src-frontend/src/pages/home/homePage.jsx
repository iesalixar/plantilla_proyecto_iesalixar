import React, { useState, useEffect, useContext } from 'react';

import { ThemeContext } from '../../contexts/themeContexts.js';
import { useScreen } from '../../contexts/screenContexts.js';
import { useAuth } from '../../contexts/userContexts.js';

import Skeleton from '../../components/layout/skeleton/skeleton.jsx';
import { Header } from '../../components/layout/header/header.jsx';
import { FooterBarComponent, SidebarComponent } from '../../components/layout/navbar/navbar.jsx';
import PublicationComponent from '../../components/common/publication/publication.jsx';
import FooterComponent from '../../components/layout/footer/footer.jsx';
import { ToggleButton } from '../../components/layout/buttons/buttons.jsx';

import { LogoiconDark } from '../../components/common/icon/logo/logo-dark.jsx';
import { LogoiconClear } from '../../components/common/icon/logo/logo-clear.jsx';

import { LogoutClear } from '../../components/common/icon/sidebar/sidebarIcons-clear.jsx';
import { LogoutDark } from '../../components/common/icon/sidebar/sidebarIcons-dark.jsx';

const HomePage = () => {
    // CONTEXTS
    const { theme, isDark } = useContext(ThemeContext);
    const { screenWidth } = useScreen();
    const { logout } = useAuth();

    //#region SCREEN STATE
    const [isScreenSmall, setIsScreenSmall] = useState(false);

    useEffect(() => {
        setIsScreenSmall(screenWidth <= 770);
    }, [screenWidth]);
    //#endregion

    const handleLogoutClick = () => {
        logout();
        window.location.href = '/';
    };

    const svgLogoIconProps = {
        width: 45,
        height: 45,
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
