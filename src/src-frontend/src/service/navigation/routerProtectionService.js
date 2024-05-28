import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../../contexts/authentication';

const ProtectedRoute = ({ children }) => {
    const { activeUser } = useAuth();
    return activeUser ? children : <Navigate to="/login" />;

};

export default ProtectedRoute;
