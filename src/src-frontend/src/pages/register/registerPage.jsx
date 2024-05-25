import React from 'react';
import { RegisterHeader } from '../../components/header/headers';
import SignupForm from '../../components/signup/signup';
import FooterComponent from '../../components/footer/footer';
import Skeleton from '../../components/skeleton/skeleton';

const RegisterPage = () => {
    return (
        <Skeleton
            mainContent={
                <>
                    <RegisterHeader />
                    <SignupForm />
                </>
            }
            footerContent={<FooterComponent />}
        />
    );
};
export default RegisterPage;
