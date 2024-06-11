import { useState, useEffect } from 'react';

const useAuth = () => {

    const [userData, setUserData] = useState(() => {
        const storedUserData = sessionStorage.getItem('userData');
        return storedUserData ? JSON.parse(storedUserData) : null;
    });

    useEffect(() => {
        if (userData) {
            sessionStorage.setItem('userData', JSON.stringify(userData));
        } else {
            sessionStorage.removeItem('userData');
        }
    }, [userData]);

    const login = (data) => {
        setUserData(data);
    };

    const logout = () => {
        setUserData(null);
        sessionStorage.removeItem('userData');
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('userType');
        sessionStorage.removeItem('loggedIn');
    };

    const updateUser = (data) => {
        setUserData(data);
    };

    return {
        userData,
        login,
        logout,
        updateUser,
    };
};

export default useAuth;
