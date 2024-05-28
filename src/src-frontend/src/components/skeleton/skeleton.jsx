import React, { useState, useEffect } from 'react';

const Skeleton = ({ mainContent, footerContent }) => {
    const [showSidebar, setShowSidebar] = useState(true);
    const checkScreenSize = () => {
        const isSmallHeader = window.innerWidth <= 500;
        setShowSidebar(window.innerWidth <= 770);
    };
    useEffect(() => {
        checkScreenSize();
        window.addEventListener('resize', checkScreenSize);
        return () => {
            window.removeEventListener('resize', checkScreenSize);
        };
    }, []);
    return (
        <>
            <main>{mainContent}</main>
            {!showSidebar && <footer>{footerContent}</footer>}
        </>
    );
}

export default Skeleton;