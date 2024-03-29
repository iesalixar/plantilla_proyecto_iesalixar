"use client"; 
import React from 'react';
import LoginForm from '@/components/forms/loginForm/loginForm';
import LoginNav from '@/components/navbar/loginNav/loginNav';
function LoginPage() {
    return(
        <>
            <header>
            <LoginNav />
        </header><main>
                <LoginForm />
            </main>
        </>
    );
}

export default LoginPage;