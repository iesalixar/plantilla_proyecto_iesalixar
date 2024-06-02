import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';

import { AuthProvider } from './contexts/userContexts.js';
import { ThemeProvider } from './contexts/themeContexts.js';
import { ScreenProvider } from './contexts/screenContexts.js';

import HomePage from './pages/home/homePage';
import AuthPage from './pages/auth/authPage.jsx';
import ProtectedRoute from './service/navigation/routerProtectionService.js';
import AddActivity from './components/common/form/PublicationForm.jsx';
import "@fontsource/roboto";
import './app.scss'

function App() {

  document.title = 'FitConnet';

  return (
    <Router>
      <Routes>
        <Route path="/" element={<ProtectedRoute>
          <HomePage />
        </ProtectedRoute>} />
        <Route path="/login" element={<AuthPage />} />
        <Route path="/register" element={<AuthPage />} />
        <Route path="/add" element={<AddActivity />} />
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
