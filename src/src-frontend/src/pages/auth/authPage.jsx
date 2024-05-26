import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import LoginForm from '../../components/login/login';
import { RegisterHeader, IconHeader } from '../../components/header/headers';
import FooterComponent from '../../components/footer/footer';
import Skeleton from '../../components/skeleton/skeleton';
import { LoginButton, RegisterButton } from '../../components/buttons/buttons';
import LogoFull from '../../components/logo/logofull';
import SignupForm from '../../components/signup/signup';
import Logoicon from '../../components/logo/logoIcon';

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
