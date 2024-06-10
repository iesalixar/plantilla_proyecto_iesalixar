import React, { createContext, useContext, useState } from 'react';
import useAuth from '../hooks/useAuth';
import useInterval from '../hooks/useInterval';
import { fetchAllUsers, fetchUserData } from '../service/userService';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const auth = useAuth();
  const { userData, updateUser } = auth;
  const token = userData ? userData.token : null;
  const [allUsers, setAllUsers] = useState([]);

  useInterval(async () => {
    if (token && userData) {
      await fetchUserData(token, userData.user.id, updateUser);
      const users = await fetchAllUsers(token);
      setAllUsers(users);
    }
  }, 60000);

  return (
    <AuthContext.Provider value={{ ...auth, allUsers }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuthContext = () => {
  return useContext(AuthContext);
};
