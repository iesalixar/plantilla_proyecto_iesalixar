import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider } from './authContext/autContext';
import HomePage from './pages/home/homePage';
import AuthPage from './pages/auth/authPage.jsx';
import ProtectedRoute from './components/protectedRouter.js';
import "@fontsource/roboto"; // Defaults to weight 400
import PublicationComponent from './components/publication/publication.jsx';
import ImageUploadComponent from './components/imageUpload/imageUpload.jsx';
import './app.scss'

function App() {

  document.title = 'FitConnet';

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/home" />} />
        <Route path="/login" element={<AuthPage />} />
        <Route path="/register" element={<AuthPage />} />
        <Route path="/pruebas" element={<PublicationComponent />} />
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
