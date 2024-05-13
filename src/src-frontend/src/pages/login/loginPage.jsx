import React from 'react';
import LoginForm from '../../components/login/login';
import { RegisterHeader } from '../../components/header/headers';
import FooterComponent from '../../components/footer/footer';

const LoginPage = () => {

    return (
        <body>
            <RegisterHeader></RegisterHeader>
            <LoginForm></LoginForm>
            <FooterComponent></FooterComponent>
        </body>
    );
};
export default LoginPage;

