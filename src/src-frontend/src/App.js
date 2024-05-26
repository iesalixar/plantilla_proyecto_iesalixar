import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider } from './authContext/autContext';
import HomePage from './pages/home/homePage';
import AuthPage from './pages/auth/authPage.jsx';
import ProtectedRoute from './components/protectedRouter.js';
import "@fontsource/roboto"; // Defaults to weight 400
import "@fontsource/roboto/400.css"; // Specify weight
import "@fontsource/roboto/400-italic.css"; // Specify weight and styl
import './App.css';

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
    <App />
  </AuthProvider>
);

export default DefaultExport;
