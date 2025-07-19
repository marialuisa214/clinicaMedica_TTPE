import React, { createContext, useContext, useState, useEffect, ReactNode } from 'react';
import { LoginRequest, UserInfo, AuthState } from '../types';
import { apiService } from '../services/api';

interface AuthContextType extends AuthState {
  login: (credentials: LoginRequest) => Promise<boolean>;
  logout: () => void;
  loading: boolean;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [authState, setAuthState] = useState<AuthState>({
    isAuthenticated: false,
    user: null,
    token: null,
  });
  const [loading, setLoading] = useState(true);

  // Verificar se há token salvo ao inicializar
  useEffect(() => {
    const checkAuth = async () => {
      const token = localStorage.getItem('access_token');
      const userInfo = localStorage.getItem('user_info');

      if (token && userInfo) {
        try {
          // Verificar se o token ainda é válido
          const currentUser = await apiService.getCurrentUser();
          setAuthState({
            isAuthenticated: true,
            user: currentUser,
            token,
          });
        } catch (error) {
          // Token inválido
          localStorage.removeItem('access_token');
          localStorage.removeItem('user_info');
        }
      }
      setLoading(false);
    };

    checkAuth();
  }, []);

  const login = async (credentials: LoginRequest): Promise<boolean> => {
    try {
      const tokenResponse = await apiService.login(credentials);
      const userInfo = await apiService.getCurrentUser();

      // Salvar no localStorage
      localStorage.setItem('access_token', tokenResponse.access_token);
      localStorage.setItem('user_info', JSON.stringify(userInfo));

      setAuthState({
        isAuthenticated: true,
        user: userInfo,
        token: tokenResponse.access_token,
      });

      return true;
    } catch (error) {
      console.error('Erro no login:', error);
      return false;
    }
  };

  const logout = async () => {
    try {
      await apiService.logout();
    } catch (error) {
      console.error('Erro no logout:', error);
    } finally {
      // Limpar estado local
      localStorage.removeItem('access_token');
      localStorage.removeItem('user_info');
      setAuthState({
        isAuthenticated: false,
        user: null,
        token: null,
      });
    }
  };

  const value: AuthContextType = {
    ...authState,
    login,
    logout,
    loading,
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = (): AuthContextType => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth deve ser usado dentro de um AuthProvider');
  }
  return context;
}; 