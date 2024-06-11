import React, { createContext, useEffect, useState } from "react";
import useTheme from "../hooks/useTheme";


export const ThemeContext = createContext();

export const ThemeProvider = ({ children }) => {
    const { theme, isDark, toggleTheme } = useTheme();

    return (
        <ThemeContext.Provider value={{ theme, isDark, toggleTheme }}>
            {children}
        </ThemeContext.Provider>
    );
};