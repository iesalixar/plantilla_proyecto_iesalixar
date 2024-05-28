import React, { useState, useEffect } from 'react';

const Skeleton = ({ mainContent, footerContent }) => {
    const [showSidebar, setShowSidebar] = useState(true);

    const checkScreenSize = () => {
        setShowSidebar(window.innerWidth > 770);
    };

    useEffect(() => {
        checkScreenSize();
        window.addEventListener('resize', checkScreenSize);
        return () => {
            window.removeEventListener('resize', checkScreenSize);
        };
    }, []);

    return (
        <div className="body-container">
            <main>{mainContent}</main>
            {!showSidebar && <footer>{footerContent}</footer>}
        </div>
    );
};

export default Skeleton;
