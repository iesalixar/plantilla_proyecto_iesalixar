import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import { AuthProvider } from './contexts/AuthProvider.js';
import { ThemeProvider } from './contexts/ThemeProvider.js';
import { ScreenProvider } from './contexts/ScreenProvider.js';
import { ModalProvider } from './contexts/ModalProvider.js';

import HomePage from './pages/home/homePage';
import AuthPage from './pages/auth/authPage.jsx';
import ProtectedRoute from './service/routerProtectionService.js';
import "@fontsource/roboto";
import './app.scss'

function App() {
  document.title = 'FitConnet';

  return (
    <Router>
      <Routes>
        <Route path="/" element={<ProtectedRoute><HomePage /></ProtectedRoute>} />
        <Route path="/login" element={<AuthPage />} />
        <Route path="/register" element={<AuthPage />} />
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