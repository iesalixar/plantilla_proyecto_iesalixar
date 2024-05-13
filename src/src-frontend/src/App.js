import React from 'react';
import './_css/style.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './authContext/autContext';
import LoginPage from './pages/login/loginPage';
import RegisterPage from './pages/register/registerPage';
import HomePage from './pages/home/homePage'
function App() {
  document.title = 'FitConnet'
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/home" element={<HomePage />} />
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
