import React, { useState, useEffect, useContext } from 'react';

import { ThemeContext } from '../../contexts/ThemeProvider.js';
import { useScreenContext } from '../../contexts/ScreenProvider.js';
import { useAuthContext } from '../../contexts/AuthProvider.js';
import { useModalContext } from '../../contexts/ModalProvider.js';

import Skeleton from '../../components/layout/skeleton/skeleton.jsx';
import { Header } from '../../components/layout/header/header.jsx';
import { FooterBarComponent, SidebarComponent } from '../../components/layout/navbar/navbar.jsx';
import ActivityPostComponent from '../../components/common/activity/activityPost.jsx';
import FooterComponent from '../../components/layout/footer/footer.jsx';
import { ToggleButton } from './components/buttons/buttons.jsx';
import AddActivityForm from '../../components/common/addActivity/addActivityCard.jsx';
import { LogoiconDark } from '../../assest/icon/logo-dark';
import { LogoiconClear } from '../../assest/icon/logo-clear';
import { LogoutClear } from '../../assest/icon/sidebarIcons-clear.jsx';
import { LogoutDark } from '../../assest/icon/sidebarIcons-dark.jsx';


const DashboardPage = () => {
    // CONTEXTS
    const { isDark } = useContext(ThemeContext);
    const { screenWidth } = useScreenContext();
    const { logout, userData } = useAuthContext();
    const { isModalOpen } = useModalContext();
    const isAddModalOpen = isModalOpen('addModal');

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
                            <ActivityPostComponent />
                            {isAddModalOpen && <AddActivityForm />}
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
                        <ActivityPostComponent />
                        {isAddModalOpen && <AddActivityForm />}

                    </div>
                </>
            }
            footerContent={<></>}
        />
    );
};

export default DashboardPage