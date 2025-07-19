import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider } from './contexts/AuthContext';
import Layout from './components/Layout';
import ProtectedRoute from './components/ProtectedRoute';

// Pages
import LoginPage from './pages/LoginPage';
import DashboardPage from './pages/DashboardPage';
import TestePage from './pages/TestePage';

// Admin pages
import FuncionariosPage from './pages/admin/FuncionariosPage';

// Medico pages  
import AgendaPage from './pages/medico/AgendaPage';

// Atendente pages
import ConsultasPage from './pages/atendente/ConsultasPage';
import PacientesPage from './pages/atendente/PacientesPage';

function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/login" element={<LoginPage />} />
          <Route path="/teste" element={<TestePage />} />
          <Route path="/" element={<Navigate to="/dashboard" replace />} />
          
          {/* Rotas protegidas */}
          <Route element={<Layout />}>
            <Route 
              path="/dashboard" 
              element={
                <ProtectedRoute>
                  <DashboardPage />
                </ProtectedRoute>
              } 
            />
            
            {/* Admin routes */}
            <Route 
              path="/admin/funcionarios" 
              element={
                <ProtectedRoute allowedRoles={['administrador']}>
                  <FuncionariosPage />
                </ProtectedRoute>
              } 
            />
            
            {/* Medico routes */}
            <Route 
              path="/medico/agenda" 
              element={
                <ProtectedRoute allowedRoles={['medico']}>
                  <AgendaPage />
                </ProtectedRoute>
              } 
            />
            
            {/* Atendente routes */}
            <Route 
              path="/atendente/consultas" 
              element={
                <ProtectedRoute allowedRoles={['atendente', 'administrador']}>
                  <ConsultasPage />
                </ProtectedRoute>
              } 
            />
            <Route 
              path="/atendente/pacientes" 
              element={
                <ProtectedRoute allowedRoles={['atendente', 'administrador']}>
                  <PacientesPage />
                </ProtectedRoute>
              } 
            />
          </Route>
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App; 