import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { Provider } from 'react-redux';
import store from './redux/store.js';
import { AuthProvider, useAuth } from './contexts/userContexts.js';
import { ThemeProvider } from './contexts/themeContexts.js';
import { ScreenProvider } from './contexts/screenContexts.js';
import HomePage from './pages/home/homePage';
import AuthPage from './pages/auth/authPage.jsx';
import ProtectedRoute from './service/navigation/routerProtectionService.js';
import "@fontsource/roboto";
import './app.scss';

function App() {
  return (
    <Router>
      <Routes>
        {/* Redirige la raíz al componente de inicio protegido */}
        <Route path="/" element={<ProtectedRoute><HomePage /></ProtectedRoute>} />
        <Route path="/login" element={<AuthPage />} />
        <Route path="/register" element={<AuthPage />} />
        <Route path="/home" element={<ProtectedRoute><HomePage /></ProtectedRoute>} />
      </Routes>
    </Router>
  );
}

const DefaultExport = () => (
  <Provider store={store}>
    <AuthProvider>
      <ThemeProvider>
        <ScreenProvider>
          <App />
        </ScreenProvider>
      </ThemeProvider>
    </AuthProvider>
  </Provider>
);

export default DefaultExport;
