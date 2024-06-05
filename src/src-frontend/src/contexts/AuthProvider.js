import React, { createContext, useContext } from 'react';
import useAuth from '../hooks/useAuth';
import useInterval from '../hooks/useInterval';
import { fetchAllUsers, fetchUserData } from '../service/userService';
const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const auth = useAuth();
  const { userData } = auth;
  const token = userData ? userData.token : null;

  // Hook para actualizar información periódicamente
  useInterval(() => {
    if (token) {
      fetchUserData(token, userData.id, auth.updateUser);
      fetchAllUsers(token);
    }
  }, 60000); // Llamadas cada 60 segundos

  return (
    <AuthContext.Provider value={auth}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuthContext = () => {
  return useContext(AuthContext);
};