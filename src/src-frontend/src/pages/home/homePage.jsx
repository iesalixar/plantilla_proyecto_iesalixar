import React from 'react';
import FooterComponent from '../../components/footer/footer';
import { DefaultHeader } from '../../components/header/headers';
import SidebarComponent from '../../components/sidebar/sidebar';
import Skeleton from '../../components/skeleton/skeleton';

const HomePage = () => {
    return (
        <Skeleton
            mainContent={
                <>
                    <DefaultHeader />
                    <SidebarComponent />
                </>
            }
            footerContent={<FooterComponent />}
        />
    );
};

export default HomePage;