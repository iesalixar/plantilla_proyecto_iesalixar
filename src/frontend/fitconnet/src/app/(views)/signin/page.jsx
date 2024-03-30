"use client";
import React from "react";
import Form from '@/app/_components/forms/signin/signin';
import Nav from '@/app/_components/navs/singup/nav';
import Layout from './layout';
import Footer from '@/app/_components/footer/footer';

function SigninPage() {
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

export default SigninPage;