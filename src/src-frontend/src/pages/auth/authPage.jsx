import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';

import LoginForm from '../../components/forms/login';
import { RegisterHeader, IconHeader } from '../../components/layout/header/headers';
import FooterComponent from '../../components/layout/footer/footer';
import Skeleton from '../../components/layout/skeleton/skeleton';
import { LoginButton, RegisterButton } from '../../components/layout/buttons/buttons';
import SignupForm from '../../components/forms/signup';

import Logoicon from '../../assest/icons/components/logo/logoIcon';
import LogoFull from '../../assest/icons/components/logo/logofull';

const AuthPage = () => {

    const location = useLocation();
    const pathname = location.pathname;

    const [isSmallScreen, setIsSmallScreen] = useState(false);

    const checkScreenSize = () => {
        const isSmall = window.innerWidth <= 500 || window.innerHeight <= 500;
        setIsSmallScreen(isSmall);
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
                            rightContent={pathname === '/register' ? <LoginButton /> : <RegisterButton />}
                        />
                    ) : (
                        <RegisterHeader
                            leftContent={<LogoFull />}
                            rightContent={pathname === '/register' ? <LoginButton /> : <RegisterButton />}
                        />
                    )}
                    {pathname === '/login' ? <LoginForm /> : <SignupForm />}
                </>
            }
            footerContent={<FooterComponent />}
        />
    );
};

export default AuthPage;
