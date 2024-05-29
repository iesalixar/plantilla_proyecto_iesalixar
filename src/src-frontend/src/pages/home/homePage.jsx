import React, { useState, useEffect, useContext } from 'react';

import Skeleton from '../../components/layout/skeleton/skeleton.jsx';
import { Header } from '../../components/layout/header/header.jsx';
import { FooterBarComponent, SidebarComponent } from '../../components/layout/navbar/navbar.jsx';
import PublicationComponent from '../../components/publication/publication';
import FooterComponent from '../../components/layout/footer/footer.jsx';

import { ReactComponent as LogoutIcon } from '../../assest/icons/svg/sidebar-icons/logout_icon.svg';
import LogoiconDark from '../../assest/icons/components/logo/logoIcon-dark.jsx';
import LogoiconClear from '../../assest/icons/components/logo/logoicon-clear';
import LogotextDark from '../../assest/icons/components/logo/logotext-dark.jsx';
import { ThemeContext } from '../../contexts/theme';

import { ToggleButton } from '../../components/layout/buttons/buttons.jsx';

const HomePage = () => {

    const [isMobile, setIsMobileScreen] = useState(false);

    const { theme, isDark } = useContext(ThemeContext);

    const checkScreenSize = () => {
        setIsMobileScreen(window.innerWidth <= 550);
    };

    useEffect(() => {
        checkScreenSize();
        window.addEventListener('resize', checkScreenSize);
        return () => {
            window.removeEventListener('resize', checkScreenSize);
        };
    }, []);

    const headerLeftContent = isDark ? <LogoiconDark /> : <LogoiconClear />;
    if (isMobile) {
        <Skeleton
            mainContent={
                <>
                    <Header
                        leftContent={headerLeftContent}
                        rightContent={
                            <>
                                <ToggleButton />
                                <LogoutIcon />
                            </>}
                    />
                    <div className="main-content">
                        <PublicationComponent />
                    </div>
                </>
            }
            footerContent={<FooterBarComponent />}
        />
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
