import React from 'react';
import { RegisterHeader } from '../../components/header/headers';
import SignupForm from '../../components/signup/signup';
import FooterComponent from '../../components/footer/footer';


const RegisterPage = () => {

    return (
        <body>
            <RegisterHeader></RegisterHeader>
            <SignupForm></SignupForm>
            <FooterComponent></FooterComponent>
        </body>
    );
};
export default RegisterPage;

