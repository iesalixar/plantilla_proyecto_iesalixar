import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';

import { AuthProvider } from './contexts/authentication.js';
import { ThemeProvider } from './contexts/theme.js';
import { ScreenProvider } from './contexts/screen.js';

import HomePage from './pages/home/homePage';
import AuthPage from './pages/auth/authPage.jsx';
import ProtectedRoute from './service/navigation/routerProtectionService.js';

import "@fontsource/roboto";
import './app.scss'

function App() {

  document.title = 'FitConnet';

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/home" />} />
        <Route path="/login" element={<AuthPage />} />
        <Route path="/register" element={<AuthPage />} />
        <Route path="/home" element={
          <ProtectedRoute>
            <HomePage />
          </ProtectedRoute>
        } />
      </Routes>
    </Router>
  );
}

const DefaultExport = () => (
  <AuthProvider>
    <ThemeProvider>
      <ScreenProvider>
        <App />
      </ScreenProvider>
    </ThemeProvider>
  </AuthProvider>
);

export default DefaultExport;
