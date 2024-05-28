import React, { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [activeUser, setActiveUser] = useState(() => {
    const storedUser = sessionStorage.getItem('activeUser');
    return storedUser ? JSON.parse(storedUser) : null;
  });

  useEffect(() => {
    if (activeUser) {
      sessionStorage.setItem('activeUser', JSON.stringify(activeUser));
    } else {
      sessionStorage.removeItem('activeUser');
    }
  }, [activeUser]);

  const login = (userData) => {
    setActiveUser(userData);
  };

  const logout = () => {
    setActiveUser(null);
    sessionStorage.removeItem('activeUser');
  };

  const updateUser = (userData) => {
    setActiveUser(userData);
  };

  return (
    <AuthContext.Provider value={{ activeUser, login, logout, updateUser }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  return useContext(AuthContext);
};
