import React, { useState, useEffect, useContext, useCallback } from 'react';
import { useLocation } from 'react-router-dom';


import { ThemeContext } from '../../contexts/ThemeProvider';
import { useScreenContext } from '../../contexts/ScreenProvider';

import Skeleton from '../../components/layout/skeleton/skeleton';
import { Header } from '../../components/layout/header/header';
import FooterComponent from '../../components/layout/footer/footer';
import LoginForm from './components/form/login';
import SignupForm from './components/form/signup';
import { LoginButton, RegisterButton, ToggleButton } from './components/buttons/buttons';
import { LogoFullDark, LogoiconDark, LogotextDark } from '../../assest/icon/logo-dark';
import { LogoFullClear, LogoiconClear, LogotextClear } from '../../assest/icon/logo-clear';


const AuthPage = () => {

    // CONTEXTS
    const { isDark } = useContext(ThemeContext);
    const location = useLocation();
    const pathname = location.pathname;
    const { screenWidth } = useScreenContext();

    let logoIcon;

    // METADATA TITLE
    setMetaDataTitle();

    //#region SCREEN STATE
    const [isScreenSmall, setIsScreenSmall] = useState(false);
    const [isScreenMedium, setIsScreenMedium] = useState(false);

    const handleResize = useCallback(() => {
        setIsScreenSmall(screenWidth <= 500);
        setIsScreenMedium(screenWidth <= 770 && screenWidth > 500);
    }, [screenWidth]);

    useEffect(() => {
        handleResize();
    }, [handleResize]);
    //#endregion

    //#region SVG PROPS
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
    //#endregion

    // SETTEAR EL LOGO SEGUN TAMAÑO Y TEMA

    setIconFunction();

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
                currentPath={location.pathname}
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
            currentPath={location.pathname}
        />
    );

    function setMetaDataTitle() {
        if (pathname === '/login') {
            document.title = 'FitConnet - Login';

        } else {
            document.title = 'FitConnet - Sign Up';
        }
    }

    function setIconFunction() {
        if (isScreenMedium && !isScreenSmall) {
            logoIcon = isDark ? <LogotextDark {...svgLogoTextProps} /> : <LogotextClear {...svgLogoTextProps} />;
        } else {
            logoIcon = isDark ? <LogoFullDark {...svgLogoProps} /> : <LogoFullClear {...svgLogoProps} />;
        }
    }
};

export default AuthPage;
