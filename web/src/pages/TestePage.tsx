import React, { useState } from 'react';
import {
  Box,
  Typography,
  Button,
  Paper,
  Grid,
  Alert,
  CircularProgress,
  List,
  ListItem,
  ListItemText,
  Divider,
  TextField
} from '@mui/material';
import { apiService } from '../services/api';

const TestePage: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [results, setResults] = useState<any[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [newFuncionario, setNewFuncionario] = useState({
    nome: 'Teste Funcionário',
    usuario: 'teste' + Date.now(),
    email: 'teste@example.com',
    tipo: 'administrador',
    senha: 'teste123'
  });

  const addResult = (test: string, result: any, success: boolean = true) => {
    setResults(prev => [...prev, { 
      test, 
      result: typeof result === 'object' ? JSON.stringify(result, null, 2) : result,
      success,
      timestamp: new Date().toLocaleTimeString()
    }]);
  };

  const testLogin = async () => {
    try {
      setLoading(true);
      const result = await apiService.login({ usuario: 'admin', senha: 'admin123' });
      addResult('Login', `Token gerado com sucesso: ${result.access_token.substring(0, 30)}...`);
    } catch (err: any) {
      addResult('Login', `Erro: ${err.message}`, false);
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const testGetFuncionarios = async () => {
    try {
      setLoading(true);
      const result = await apiService.getFuncionarios({ limit: 5 });
      addResult('Listar Funcionários', `${result.funcionarios.length} funcionários carregados`);
    } catch (err: any) {
      addResult('Listar Funcionários', `Erro: ${err.message}`, false);
    } finally {
      setLoading(false);
    }
  };

  const testCreateFuncionario = async () => {
    try {
      setLoading(true);
      const result = await apiService.createFuncionario(newFuncionario);
      addResult('Criar Funcionário', `Funcionário criado: ID ${result.id} - ${result.nome}`);
    } catch (err: any) {
      addResult('Criar Funcionário', `Erro: ${err.message}`, false);
    } finally {
      setLoading(false);
    }
  };

  const testGetMedicos = async () => {
    try {
      setLoading(true);
      const result = await apiService.getMedicos();
      addResult('Listar Médicos', `${result.length} médicos carregados`);
    } catch (err: any) {
      addResult('Listar Médicos', `Erro: ${err.message}`, false);
    } finally {
      setLoading(false);
    }
  };

  const testGetConsultas = async () => {
    try {
      setLoading(true);
      const result = await apiService.getConsultas({ limit: 5 });
      addResult('Listar Consultas', `${result.consultas.length} consultas carregadas, total: ${result.total}`);
    } catch (err: any) {
      addResult('Listar Consultas', `Erro: ${err.message}`, false);
    } finally {
      setLoading(false);
    }
  };

  const testGetPacientes = async () => {
    try {
      setLoading(true);
      const result = await apiService.getPacientes({ limit: 5 });
      addResult('Listar Pacientes', `${result.length} pacientes carregados`);
    } catch (err: any) {
      addResult('Listar Pacientes', `Erro: ${err.message}`, false);
    } finally {
      setLoading(false);
    }
  };

  const runAllTests = async () => {
    setResults([]);
    setError(null);
    
    await testLogin();
    await new Promise(resolve => setTimeout(resolve, 500)); // Aguardar 500ms entre testes
    
    await testGetFuncionarios();
    await new Promise(resolve => setTimeout(resolve, 500));
    
    await testGetMedicos();
    await new Promise(resolve => setTimeout(resolve, 500));
    
    await testGetConsultas();
    await new Promise(resolve => setTimeout(resolve, 500));
    
    await testGetPacientes();
  };

  const clearResults = () => {
    setResults([]);
    setError(null);
  };

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" gutterBottom>
        🧪 Página de Teste - Frontend ↔ Backend
      </Typography>
      
      <Typography variant="body1" color="text.secondary" sx={{ mb: 3 }}>
        Esta página testa todas as integrações entre o frontend e backend.
      </Typography>

      {error && (
        <Alert severity="error" sx={{ mb: 2 }}>
          Erro geral: {error}
        </Alert>
      )}

      <Grid container spacing={3}>
        <Grid item xs={12} md={6}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6" gutterBottom>
              Testes Individuais
            </Typography>
            
            <Box sx={{ display: 'flex', flexDirection: 'column', gap: 1 }}>
              <Button 
                variant="outlined" 
                onClick={testLogin}
                disabled={loading}
              >
                🔐 Testar Login
              </Button>
              
              <Button 
                variant="outlined" 
                onClick={testGetFuncionarios}
                disabled={loading}
              >
                👥 Listar Funcionários
              </Button>
              
              <Button 
                variant="outlined" 
                onClick={testGetMedicos}
                disabled={loading}
              >
                👨‍⚕️ Listar Médicos
              </Button>
              
              <Button 
                variant="outlined" 
                onClick={testGetConsultas}
                disabled={loading}
              >
                📅 Listar Consultas
              </Button>
              
              <Button 
                variant="outlined" 
                onClick={testGetPacientes}
                disabled={loading}
              >
                🏥 Listar Pacientes
              </Button>
              
              <Divider sx={{ my: 1 }} />
              
              <Box>
                <Typography variant="subtitle2" gutterBottom>
                  Teste de Criação de Funcionário:
                </Typography>
                <TextField
                  fullWidth
                  size="small"
                  label="Nome"
                  value={newFuncionario.nome}
                  onChange={(e) => setNewFuncionario({...newFuncionario, nome: e.target.value})}
                  sx={{ mb: 1 }}
                />
                <TextField
                  fullWidth
                  size="small"
                  label="E-mail"
                  value={newFuncionario.email}
                  onChange={(e) => setNewFuncionario({...newFuncionario, email: e.target.value})}
                  sx={{ mb: 1 }}
                />
                <Button 
                  variant="outlined" 
                  onClick={testCreateFuncionario}
                  disabled={loading}
                  fullWidth
                >
                  ➕ Criar Funcionário de Teste
                </Button>
              </Box>
            </Box>

            <Divider sx={{ my: 2 }} />
            
            <Button 
              variant="contained" 
              onClick={runAllTests}
              disabled={loading}
              fullWidth
              sx={{ mb: 1 }}
            >
              {loading ? <CircularProgress size={24} /> : '🚀 Executar Todos os Testes'}
            </Button>
            
            <Button 
              variant="text" 
              onClick={clearResults}
              fullWidth
            >
              🗑️ Limpar Resultados
            </Button>
          </Paper>
        </Grid>

        <Grid item xs={12} md={6}>
          <Paper sx={{ p: 2, maxHeight: '600px', overflow: 'auto' }}>
            <Typography variant="h6" gutterBottom>
              Resultados dos Testes
            </Typography>
            
            {results.length === 0 ? (
              <Typography color="text.secondary" sx={{ textAlign: 'center', py: 3 }}>
                Nenhum teste executado ainda
              </Typography>
            ) : (
              <List dense>
                {results.map((result, index) => (
                  <React.Fragment key={index}>
                    <ListItem>
                      <ListItemText
                        primary={
                          <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                            <span>{result.success ? '✅' : '❌'}</span>
                            <strong>{result.test}</strong>
                            <span style={{ fontSize: '0.8em', color: '#666' }}>
                              {result.timestamp}
                            </span>
                          </Box>
                        }
                        secondary={
                          <Box sx={{ 
                            mt: 0.5, 
                            p: 1, 
                            backgroundColor: result.success ? '#e8f5e8' : '#ffeaea',
                            borderRadius: 1,
                            fontFamily: 'monospace',
                            fontSize: '0.85em',
                            whiteSpace: 'pre-wrap'
                          }}>
                            {result.result}
                          </Box>
                        }
                      />
                    </ListItem>
                    {index < results.length - 1 && <Divider />}
                  </React.Fragment>
                ))}
              </List>
            )}
          </Paper>
        </Grid>
      </Grid>
    </Box>
  );
};

export default TestePage; 