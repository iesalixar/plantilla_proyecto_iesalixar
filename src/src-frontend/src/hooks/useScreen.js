import { useState, useEffect } from 'react';

const useScreen = () => {

    const [isScrolling, setIsScrolling] = useState(false);
    const [screenWidth, setScreenWidth] = useState(window.innerWidth);

    useEffect(() => {
        const handleScroll = () => {
            setIsScrolling(window.scrollY > 0);
        };

        const handleResize = () => {
            setScreenWidth(window.innerWidth);
        };

        window.addEventListener('scroll', handleScroll);
        window.addEventListener('resize', handleResize);

        return () => {
            window.removeEventListener('scroll', handleScroll);
            window.removeEventListener('resize', handleResize);
        };
    }, []);

    return {
        isScrolling,
        screenWidth
    };
};
export default useScreen;
