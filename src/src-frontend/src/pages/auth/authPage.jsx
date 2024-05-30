import React, { useState, useEffect, useContext } from 'react';
import { useLocation } from 'react-router-dom';

import LoginForm from '../../components/forms/login';
import { Header } from '../../components/layout/header/header';
import FooterComponent from '../../components/layout/footer/footer';
import Skeleton from '../../components/layout/skeleton/skeleton';
import { LoginButton, RegisterButton } from '../../components/layout/buttons/buttons';
import SignupForm from '../../components/forms/signup';

import LogoiconDark from '../../assest/icons/components/logo/logoIcon-dark';
import LogoFullDark from '../../assest/icons/components/logo/logofull-dark';
import LogoFullClear from '../../assest/icons/components/logo/logofull-clear';
import LogoiconClear from '../../assest/icons/components/logo/logoicon-clear';
import LogotextDark from '../../assest/icons/components/logo/logotext-dark';
import LogotextClear from '../../assest/icons/components/logo/logotext-clear';

import { ToggleButton } from '../../components/layout/buttons/buttons';

import { ThemeContext } from '../../contexts/theme';

import { useScreen } from '../../contexts/screen';

const AuthPage = () => {

    const { theme, isDark } = useContext(ThemeContext);
    const location = useLocation();
    const pathname = location.pathname;
    const { screenWidth } = useScreen();

    const [isScreenSmall, setIsScreenSmall] = useState(false);
    const [isScreenMedium, setisScreenMedium] = useState(false);


    const handleResize = () => {
        setIsScreenSmall(screenWidth <= 500);
        setisScreenMedium(screenWidth <= 770 && screenWidth >= 500);

    };
    useEffect(() => {
        handleResize();
        window.addEventListener('resize', handleResize);
        return () => window.removeEventListener('resize', handleResize);
    }, [screenWidth]);

    const svgLogoProps = {
        width: 340.99,
        height: 80.981,
        className: "logo-full",
        viewBox: "140 0 100 100",
    };
    const svgLogoTextProps = {
        width: 243.493,
        height: 60,
        className: "logo-text",
        viewBox: "138 0 243.493 60",
    };
    const svgLogoIconProps = {
        width: 50,
        height: 50,
        className: "logo-icon",
        viewBox: "0 0 100 100",
    };

    let logoIcon;

    if (isScreenMedium && !isScreenSmall) {
        logoIcon = isDark ? <LogotextDark {...svgLogoTextProps} /> : <LogotextClear {...svgLogoTextProps} />;
    } else {
        logoIcon = isDark ? <LogoFullDark {...svgLogoProps} /> : <LogoFullClear {...svgLogoProps} />;
    }

    const leftContent = {
        logoIcon: logoIcon
    };



    if (isScreenSmall) {
        return (
            <Skeleton
                mainContent={
                    <>

                        <Header
                            leftContent={isDark ? <LogoiconDark {...svgLogoIconProps} /> : <LogoiconClear {...svgLogoIconProps} />}
                            rightContent={
                                <>
                                    <ToggleButton />
                                </>}
                        />
                        {pathname === '/login' ? <LoginForm /> : <SignupForm />}
                    </>
                }
                footerContent={<FooterComponent />}
            />
        );
    }
    return (
        <Skeleton
            mainContent={
                <>
                    <Header
                        leftContent={leftContent.logoIcon}
                        rightContent={
                            <>
                                <ToggleButton />
                                {pathname === '/register' ? <LoginButton /> : <RegisterButton />}

                            </>
                        } />
                    {pathname === '/login' ? <LoginForm /> : <SignupForm />}

                </>
            }
            footerContent={<FooterComponent />}
        />
    );
};

export default AuthPage;
