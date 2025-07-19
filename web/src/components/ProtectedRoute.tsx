import React from 'react';
import { Navigate, useLocation } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import { Box, CircularProgress } from '@mui/material';

interface ProtectedRouteProps {
  children: React.ReactNode;
  allowedRoles?: string[];
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ 
  children, 
  allowedRoles = [] 
}) => {
  const { isAuthenticated, user, loading } = useAuth();
  const location = useLocation();

  if (loading) {
    return (
      <Box 
        display="flex" 
        justifyContent="center" 
        alignItems="center" 
        minHeight="100vh"
      >
        <CircularProgress />
      </Box>
    );
  }

  if (!isAuthenticated) {
    return <Navigate to="/login" state={{ from: location }} replace />;
  }

  // Se não há roles específicas definidas, qualquer usuário autenticado pode acessar
  if (allowedRoles.length === 0) {
    return <>{children}</>;
  }

  // Verificar se o usuário tem uma das roles permitidas
  if (user && allowedRoles.includes(user.tipo)) {
    return <>{children}</>;
  }

  // Se o usuário não tem permissão, redirecionar para o dashboard
  return <Navigate to="/" replace />;
};

export default ProtectedRoute; 