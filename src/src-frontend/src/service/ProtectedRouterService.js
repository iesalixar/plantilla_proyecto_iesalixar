import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';

const ProtectedRoute = ({ element, roles }) => {
    const isLoggedIn = window.sessionStorage.getItem("loggedIn");
    return isLoggedIn === "true" ? <Outlet /> : <Navigate to="login" />;

};

export default ProtectedRoute;
