"use client"; 
import React from 'react';
import Form from '@/app/_components/forms/login/login';
import Nav from '@/app/_components/nav/login/nav';

function LoginPage() {
    return(
        <>
            <header>
            <Nav />
        </header><main>
                <Form />
            </main>
        </>
    );
}

export default LoginPage;