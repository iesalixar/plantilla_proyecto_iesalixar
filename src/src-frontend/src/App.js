import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Navigate } from 'react-router-dom';
import { AuthProvider } from './contexts/AuthProvider.js';
import { ThemeProvider } from './contexts/ThemeProvider.js';
import { ScreenProvider } from './contexts/ScreenProvider.js';
import { ModalProvider } from './contexts/ModalProvider.js';

import ProtectedRoute from './service/ProtectedRouterService.js';

import HomePage from './pages/home/homePage';
import DashboardPage from './pages/admin/dashboardPage.jsx';
import UserTable from './components/common/table/userTable.jsx';
import AuthPage from './pages/auth/authPage.jsx';

import "@fontsource/roboto";
import './app.scss'

function App() {
  document.title = 'FitConnet';
  const isLogged = window.sessionStorage.getItem("isLogged");
  const userRol = window.sessionStorage.getItem("userRol");
  const isAdmin = () => {
    if (userRol && userRol == ('ROLE_ADMIN')) {
      return true;
    } else {
      return false;
    }
  }

  return (
    <Router>
      <Routes>
        {/* unauthorized route */}
        {!isLogged && (
          <>
            <Route path="/login" element={<AuthPage />} />
            <Route path="/register" element={<AuthPage />} />
            <Route path="/" element={<Navigate to="/login" />} />
          </>
        )}
        {/* ProtectedRoutes */}
        <Route element={<ProtectedRoute />}>
          <Route path="/login" element={<Navigate to="/" />} />
          <Route path="/register" element={<Navigate to="/" />} />
          {isAdmin() ? (
            <>
              <Route path="/" element={<Navigate to="/dashboard" />} />
              <Route path="/dashboard" element={< DashboardPage />} />
              <Route path="/pruebas" element={< UserTable />} />


            </>
          ) : (
            <>
              <Route path="/" element={<HomePage />} />
              <Route path="/home" element={<Navigate to="/" />} />
            </>
          )}
        </Route>

        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </Router>
  );
}

const DefaultExport = () => (
  <AuthProvider>
    <ThemeProvider>
      <ScreenProvider>
        <ModalProvider>
          <App />
        </ModalProvider>
      </ScreenProvider>
    </ThemeProvider>
  </AuthProvider>
);

export default DefaultExport;