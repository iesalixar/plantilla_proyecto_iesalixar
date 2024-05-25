import React from 'react';
import LoginForm from '../../components/login/login';
import { RegisterHeader } from '../../components/header/headers';
import FooterComponent from '../../components/footer/footer';
import Skeleton from '../../components/skeleton/skeleton';

const LoginPage = () => {
    return (
        <Skeleton
            mainContent={
                <>
                    <RegisterHeader />
                    <LoginForm />
                </>
            }
            footerContent={<FooterComponent />}
        />
    );
};
export default LoginPage;
