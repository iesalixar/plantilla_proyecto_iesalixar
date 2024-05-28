import { createContext } from "react";

const themes = {};

export const ThemeContext = createContext();

export const ThemeProvider = ({ children }) => {
    return (<ThemeContext.Provider value=[{ theme, }] > { children }</ThemeContext.Provider >);
};