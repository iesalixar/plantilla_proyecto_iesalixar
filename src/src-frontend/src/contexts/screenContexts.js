import React, { createContext, useContext, useEffect, useState } from "react";

const ScreenContext = createContext();

export const ScreenProvider = ({ children }) => {
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

    return (
        <ScreenContext.Provider value={{ isScrolling, screenWidth }}>
            {children}
        </ScreenContext.Provider>
    );
};

export const useScreen = () => useContext(ScreenContext);
