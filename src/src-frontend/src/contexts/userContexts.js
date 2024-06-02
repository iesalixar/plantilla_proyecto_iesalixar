import React, { createContext, useContext, useState, useEffect } from 'react';

// Crear el contexto de autenticación
const AuthContext = createContext();

// Proveedor del contexto de autenticación
export const AuthProvider = ({ children }) => {
  const [userData, setUserData] = useState(() => {
    const storedUserData = sessionStorage.getItem('userData');
    return storedUserData ? JSON.parse(storedUserData) : null;
  });

  // Efecto para almacenar los datos del usuario en sessionStorage
  useEffect(() => {
    if (userData) {
      sessionStorage.setItem('userData', JSON.stringify(userData));
    } else {
      sessionStorage.removeItem('userData');
    }
  }, [userData]);

  // Función de login para establecer los datos del usuario
  const login = (data) => {
    setUserData(data);
  };

  // Función de logout para limpiar los datos del usuario
  const logout = () => {
    setUserData(null);
    sessionStorage.removeItem('userData');
  };

  // Función para actualizar los datos del usuario
  const updateUser = (data) => {
    setUserData(data);
  };

  return (
    <AuthContext.Provider value={{ userData, login, logout, updateUser }}>
      {children}
    </AuthContext.Provider>
  );
};

// Hook para usar el contexto de autenticación
export const useAuth = () => {
  return useContext(AuthContext);
};
