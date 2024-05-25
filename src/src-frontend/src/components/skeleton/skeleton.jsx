import React from 'react';

const Skeleton = ({ mainContent, footerContent }) => {
    return (
        <>
            <main>{mainContent}</main>
            <footer>{footerContent}</footer>
        </>
    );
}

export default Skeleton;