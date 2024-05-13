import React, { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [activeUser, setActiveUser] = useState(() => {
    const storedUser = localStorage.getItem('activeUser');
    return storedUser ? JSON.parse(storedUser) : null;

  });
  useEffect(() => {
    // Almacenar el usuario en el localStorage cuando cambie  // ESTO ME GUSTARIA CAMBIARLO PARA NO MOSTRAR DATOS SENSIBLES EN LAS COOKIES; SIN EMBARGO NO LO HE CONSEGUIDO, PIERDO EL CONTEXTO DE LA APP AL ACTUALIZAR
    localStorage.setItem('activeUser', JSON.stringify(activeUser));
  }, [activeUser]);

  const login = (userData) => {
    setActiveUser(userData);
  };

  const logout = () => {
    setActiveUser(null);
    localStorage.removeItem('activeUser');
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
