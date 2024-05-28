import React from 'react';

const Skeleton = ({ mainContent, footerContent }) => {

    return (
        <div className="body-container">
            <main >{mainContent}</main>
            <footer>{footerContent}</footer>
        </div>
    );
};

export default Skeleton;
