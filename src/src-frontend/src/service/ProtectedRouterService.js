import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';

const ProtectedRoute = ({ element, roles }) => {
    const isAuht = window.sessionStorage.getItem("isAuht");
    return isAuht === "true" ? <Outlet /> : <Navigate to="/login" />;

};

export default ProtectedRoute;
