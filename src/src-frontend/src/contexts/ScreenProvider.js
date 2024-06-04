import React, { createContext, useContext } from 'react';
import useScreen from "../hooks/useScreen";

const ScreenContext = createContext();

export const ScreenProvider = ({ children }) => {
    const screen = useScreen();

    return (
        <ScreenContext.Provider value={screen}>
            {children}
        </ScreenContext.Provider>
    );
};

export const useScreenContext = () => useContext(ScreenContext);
