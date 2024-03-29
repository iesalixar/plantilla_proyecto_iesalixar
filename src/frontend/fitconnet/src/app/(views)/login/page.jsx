"use client";
import React from 'react';
import Form from '@/app/_components/forms/login/login';
import Nav from '@/app/_components/navs/login/nav';
import Layout from './layout';
import Footer from '@/app/_components/footer/footer';
function LoginPage() {
    return (
        <>
            <Layout>
                <header>
                    <Nav />
                </header><main>
                    <Form />
                </main>
                <Footer />
            </Layout>
        </>
    );
}

export default LoginPage;