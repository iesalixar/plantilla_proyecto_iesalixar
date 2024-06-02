import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../../contexts/userContexts';

const ProtectedRoute = ({ children }) => {
    const { userData } = useAuth();
    return userData ? children : <Navigate to="/login" />;

};

export default ProtectedRoute;
