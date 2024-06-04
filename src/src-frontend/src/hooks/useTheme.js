import { useState, useEffect } from 'react';
const themes = {
    dark: {
        teal1: '#0a1313',
        teal2: '#0f1c1d',
        teal3: '#072d2f',
        teal4: '#003b3e',
        teal5: '#00484c',
        teal6: '#00565b',
        teal7: '#02696e',
        teal8: '#007e84',
        teal9: '#30a0a7',
        teal10: '#1d949b',
        teal11: '#64cdd4',
        teal12: '#a0f0f5',

        tealA1: '#00bbbb03',
        tealA2: '#00e9fd0d',
        tealA3: '#00eaf921',
        tealA4: '#00ecfc31',
        tealA5: '#00edfd40',
        tealA6: '#00edfd50',
        tealA7: '#00f2ff64',
        tealA8: '#00f1fd7c',
        tealA9: '#43f4ffa1',
        tealA10: '#26f3ff94',
        tealA11: '#76f7ffd1',
        tealA12: '#a6f9fef5',

        tealContrast: '#fff',
        tealSurface: '#0e272980',
        tealIndicator: '#30a0a7',
        tealTrack: '#30a0a7',

        gray1: '#111113',
        gray2: '#19191b',
        gray3: '#222325',
        gray4: '#292a2e',
        gray5: '#303136',
        gray6: '#393a40',
        gray7: '#46484f',
        gray8: '#5f606a',
        gray9: '#6c6e79',
        gray10: '#797b86',
        gray11: '#b2b3bd',
        gray12: '#eeeef0',

        grayA1: '#1111bb03',
        grayA2: '#cbcbf90b',
        grayA3: '#d6e2f916',
        grayA4: '#d1d9f920',
        grayA5: '#d7ddfd28',
        grayA6: '#d9defc33',
        grayA7: '#dae2fd43',
        grayA8: '#e0e3fd60',
        grayA9: '#e0e4fd70',
        grayA10: '#e3e7fd7e',
        grayA11: '#eff0feb9',
        grayA12: '#fdfdffef',

        grayContrast: '#FFFFFF',
        graySurface: 'rgba(0, 0, 0, 0.05)',
        grayIndicator: '#6c6e79',
        grayTrack: '#6c6e79',
        colorBackground: "#111",
        backgroundGradient: "radial-gradient(circle, #00474b, #0e3843, #172a35, #181d24, #111113)"
    },
    clear: {
        teal1: '#f9fefe',
        teal2: '#f3fbfb',
        teal3: '#dff7f8',
        teal4: '#ccf1f3',
        teal5: '#b9e9ec',
        teal6: '#a5dee2',
        teal7: '#8acfd3',
        teal8: '#60bbc1',
        teal9: '#00666b',
        teal10: '#00575c',
        teal11: '#1b7378',
        teal12: '#133a3c',

        tealA1: '#00d5d506',
        tealA2: '#00aaaa0c',
        tealA3: '#00c0c820',
        tealA4: '#00b9c333',
        tealA5: '#00afba46',
        tealA6: '#01a2ad5a',
        tealA7: '#0097a075',
        tealA8: '#00929c9f',
        tealA9: '#00666b',
        tealA10: '#00575c',
        tealA11: '#006268e4',
        tealA12: '#002a2cec',

        tealContrast: '#fff',
        tealSurface: '#f0fafacc',
        tealIndicator: '#00666b',
        tealTrack: '#00666b',

        gray1: '#fcfcfd',
        gray2: '#f9f9fb',
        gray3: '#eff0f3',
        gray4: '#e7e8ec',
        gray5: '#e0e1e6',
        gray6: '#d8d9e0',
        gray7: '#cdced7',
        gray8: '#b9bbc6',
        gray9: '#8b8d98',
        gray10: '#80828d',
        gray11: '#62636c',
        gray12: '#1e1f24',

        grayA1: '#00005503',
        grayA2: '#00005506',
        grayA3: '#00104010',
        grayA4: '#000b3618',
        grayA5: '#0009321f',
        grayA6: '#00073527',
        grayA7: '#00063332',
        grayA8: '#00083046',
        grayA9: '#00051d74',
        grayA10: '#00051b7f',
        grayA11: '#0002119d',
        grayA12: '#000107e1',

        grayContrast: '#FFFFFF',
        graySurface: '#ffffffcc',
        grayIndicator: '#8b8d98',
        grayTrack: '#8b8d98',
        colorBackground: "#fff",
        backgroundGradient: "linear-gradient(to bottom, #ccf1f3, #d6f0f8, #e0eef8, #e9edf5, #eeeef0)"
    },
};

const useTheme = () => {
    const [isDark, setIsDark] = useState(false);

    useEffect(() => {
        const storedIsDark = localStorage.getItem('isDark') === "true";
        setIsDark(storedIsDark);
    }, []);

    const toggleTheme = () => {
        const newIsDark = !isDark;
        localStorage.setItem('isDark', JSON.stringify(newIsDark));
        setIsDark(newIsDark);
    };

    const theme = isDark ? themes.dark : themes.clear;

    return { theme, isDark, toggleTheme };
};

export default useTheme;