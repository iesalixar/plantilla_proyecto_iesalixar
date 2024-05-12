"use client";
import React from "react";
import Form from '@/app/_components/forms/signup/form';
import Nav from '@/app/_components/navs/signup/nav';
import Layout from './layout';
import Footer from '@/app/_components/footer/footer';

function SignupPage() {
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

export default SignupPage;