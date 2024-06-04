import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuthContext } from '../contexts/AuthProvider';

const ProtectedRoute = ({ children }) => {
    const { userData } = useAuthContext();
    return userData ? children : <Navigate to="/login" />;

};

export default ProtectedRoute;
