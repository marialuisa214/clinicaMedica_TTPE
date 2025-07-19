import React, { useState, useEffect } from 'react';
import {
  Box,
  Typography,
  Button,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Chip,
  IconButton,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Grid,
  Card,
  CardContent,
  Fab,
} from '@mui/material';
import {
  Add,
  Edit,
  Delete,
  Visibility,
  PersonAdd,
  People,
  LocalHospital,
  Assignment,
  Security,
} from '@mui/icons-material';

interface Funcionario {
  id: number;
  nome: string;
  email: string;
  usuario: string;
  tipo: string;
  registro: string; // CRM, COREM, etc.
  especialidade?: string;
  status: 'ativo' | 'inativo';
  created_at: string;
}

export const GerenciarFuncionarios: React.FC = () => {
  const [funcionarios, setFuncionarios] = useState<Funcionario[]>([]);
  const [openDialog, setOpenDialog] = useState(false);
  const [selectedFuncionario, setSelectedFuncionario] = useState<Funcionario | null>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [formData, setFormData] = useState({
    nome: '',
    email: '',
    usuario: '',
    senha: '',
    tipo: '',
    registro: '',
    especialidade: '',
  });

  // Mock data para demonstração
  useEffect(() => {
    const mockFuncionarios: Funcionario[] = [
      {
        id: 1,
        nome: 'Dr. João Silva',
        email: 'joao.silva@clinica.com',
        usuario: 'joao.silva',
        tipo: 'medico',
        registro: 'CRM 12345',
        especialidade: 'Cardiologia',
        status: 'ativo',
        created_at: '2023-01-15T10:00:00Z',
      },
      {
        id: 2,
        nome: 'Enf. Maria Santos',
        email: 'maria.santos@clinica.com',
        usuario: 'maria.santos',
        tipo: 'enfermeiro',
        registro: 'COREN 54321',
        status: 'ativo',
        created_at: '2023-02-10T14:30:00Z',
      },
      {
        id: 3,
        nome: 'Ana Costa',
        email: 'ana.costa@clinica.com',
        usuario: 'ana.costa',
        tipo: 'atendente',
        registro: 'ATD 98765',
        status: 'ativo',
        created_at: '2023-03-05T09:15:00Z',
      },
      {
        id: 4,
        nome: 'Carlos Admin',
        email: 'carlos.admin@clinica.com',
        usuario: 'carlos.admin',
        tipo: 'administrador',
        registro: 'ADM 11111',
        status: 'ativo',
        created_at: '2023-01-01T08:00:00Z',
      },
    ];
    setFuncionarios(mockFuncionarios);
  }, []);

  const handleAddFuncionario = () => {
    setSelectedFuncionario(null);
    setIsEditing(false);
    setFormData({
      nome: '',
      email: '',
      usuario: '',
      senha: '',
      tipo: '',
      registro: '',
      especialidade: '',
    });
    setOpenDialog(true);
  };

  const handleEditFuncionario = (funcionario: Funcionario) => {
    setSelectedFuncionario(funcionario);
    setIsEditing(true);
    setFormData({
      nome: funcionario.nome,
      email: funcionario.email,
      usuario: funcionario.usuario,
      senha: '',
      tipo: funcionario.tipo,
      registro: funcionario.registro,
      especialidade: funcionario.especialidade || '',
    });
    setOpenDialog(true);
  };

  const handleDeleteFuncionario = (id: number) => {
    if (window.confirm('Tem certeza que deseja excluir este funcionário?')) {
      setFuncionarios(prev => prev.filter(f => f.id !== id));
    }
  };

  const handleSave = () => {
    if (isEditing && selectedFuncionario) {
      // Atualizar funcionário existente
      setFuncionarios(prev =>
        prev.map(f =>
          f.id === selectedFuncionario.id
            ? { ...f, ...formData }
            : f
        )
      );
    } else {
      // Adicionar novo funcionário
      const newFuncionario: Funcionario = {
        id: Math.max(...funcionarios.map(f => f.id)) + 1,
        ...formData,
        status: 'ativo',
        created_at: new Date().toISOString(),
      };
      setFuncionarios(prev => [...prev, newFuncionario]);
    }
    setOpenDialog(false);
  };

  const getTipoIcon = (tipo: string) => {
    switch (tipo) {
      case 'medico':
        return <LocalHospital />;
      case 'enfermeiro':
        return <Assignment />;
      case 'administrador':
        return <Security />;
      default:
        return <People />;
    }
  };

  const getTipoColor = (tipo: string) => {
    switch (tipo) {
      case 'medico':
        return 'primary';
      case 'enfermeiro':
        return 'success';
      case 'administrador':
        return 'error';
      case 'atendente':
        return 'info';
      default:
        return 'default';
    }
  };

  const countByTipo = (tipo: string) => {
    return funcionarios.filter(f => f.tipo === tipo && f.status === 'ativo').length;
  };

  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Gerenciar Funcionários
      </Typography>

      {/* Cards de resumo */}
      <Grid container spacing={3} sx={{ mb: 3 }}>
        <Grid item xs={12} sm={6} md={3}>
          <Card>
            <CardContent>
              <Box display="flex" alignItems="center" gap={2}>
                <LocalHospital color="primary" />
                <Box>
                  <Typography variant="h5">{countByTipo('medico')}</Typography>
                  <Typography variant="body2" color="textSecondary">
                    Médicos
                  </Typography>
                </Box>
              </Box>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Card>
            <CardContent>
              <Box display="flex" alignItems="center" gap={2}>
                <Assignment color="success" />
                <Box>
                  <Typography variant="h5">{countByTipo('enfermeiro')}</Typography>
                  <Typography variant="body2" color="textSecondary">
                    Enfermeiros
                  </Typography>
                </Box>
              </Box>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Card>
            <CardContent>
              <Box display="flex" alignItems="center" gap={2}>
                <People color="info" />
                <Box>
                  <Typography variant="h5">{countByTipo('atendente')}</Typography>
                  <Typography variant="body2" color="textSecondary">
                    Atendentes
                  </Typography>
                </Box>
              </Box>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} sm={6} md={3}>
          <Card>
            <CardContent>
              <Box display="flex" alignItems="center" gap={2}>
                <Security color="error" />
                <Box>
                  <Typography variant="h5">{countByTipo('administrador')}</Typography>
                  <Typography variant="body2" color="textSecondary">
                    Administradores
                  </Typography>
                </Box>
              </Box>
            </CardContent>
          </Card>
        </Grid>
      </Grid>

      {/* Tabela de funcionários */}
      <Paper>
        <Box display="flex" justifyContent="space-between" alignItems="center" p={2}>
          <Typography variant="h6">Lista de Funcionários</Typography>
          <Button
            variant="contained"
            startIcon={<PersonAdd />}
            onClick={handleAddFuncionario}
          >
            Novo Funcionário
          </Button>
        </Box>
        <TableContainer>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Nome</TableCell>
                <TableCell>Email</TableCell>
                <TableCell>Tipo</TableCell>
                <TableCell>Registro</TableCell>
                <TableCell>Especialidade</TableCell>
                <TableCell>Status</TableCell>
                <TableCell>Ações</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {funcionarios.map((funcionario) => (
                <TableRow key={funcionario.id}>
                  <TableCell>
                    <Box display="flex" alignItems="center" gap={1}>
                      {getTipoIcon(funcionario.tipo)}
                      {funcionario.nome}
                    </Box>
                  </TableCell>
                  <TableCell>{funcionario.email}</TableCell>
                  <TableCell>
                    <Chip
                      label={funcionario.tipo.charAt(0).toUpperCase() + funcionario.tipo.slice(1)}
                      color={getTipoColor(funcionario.tipo) as any}
                      size="small"
                    />
                  </TableCell>
                  <TableCell>{funcionario.registro}</TableCell>
                  <TableCell>{funcionario.especialidade || '-'}</TableCell>
                  <TableCell>
                    <Chip
                      label={funcionario.status.charAt(0).toUpperCase() + funcionario.status.slice(1)}
                      color={funcionario.status === 'ativo' ? 'success' : 'error'}
                      size="small"
                    />
                  </TableCell>
                  <TableCell>
                    <IconButton
                      size="small"
                      onClick={() => handleEditFuncionario(funcionario)}
                    >
                      <Edit />
                    </IconButton>
                    <IconButton
                      size="small"
                      color="error"
                      onClick={() => handleDeleteFuncionario(funcionario.id)}
                    >
                      <Delete />
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Paper>

      {/* Dialog para adicionar/editar funcionário */}
      <Dialog open={openDialog} onClose={() => setOpenDialog(false)} maxWidth="md" fullWidth>
        <DialogTitle>
          {isEditing ? 'Editar Funcionário' : 'Novo Funcionário'}
        </DialogTitle>
        <DialogContent>
          <Box sx={{ pt: 2 }}>
            <Grid container spacing={2}>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  label="Nome"
                  value={formData.nome}
                  onChange={(e) => setFormData({ ...formData, nome: e.target.value })}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  label="Email"
                  type="email"
                  value={formData.email}
                  onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  label="Usuário"
                  value={formData.usuario}
                  onChange={(e) => setFormData({ ...formData, usuario: e.target.value })}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  label="Senha"
                  type="password"
                  value={formData.senha}
                  onChange={(e) => setFormData({ ...formData, senha: e.target.value })}
                  helperText={isEditing ? "Deixe em branco para manter a senha atual" : ""}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <FormControl fullWidth>
                  <InputLabel>Tipo</InputLabel>
                  <Select
                    value={formData.tipo}
                    onChange={(e) => setFormData({ ...formData, tipo: e.target.value })}
                  >
                    <MenuItem value="medico">Médico</MenuItem>
                    <MenuItem value="enfermeiro">Enfermeiro</MenuItem>
                    <MenuItem value="atendente">Atendente</MenuItem>
                    <MenuItem value="administrador">Administrador</MenuItem>
                    <MenuItem value="farmaceutico">Farmacêutico</MenuItem>
                  </Select>
                </FormControl>
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  label="Registro Profissional"
                  value={formData.registro}
                  onChange={(e) => setFormData({ ...formData, registro: e.target.value })}
                  helperText="CRM, COREN, CRF, etc."
                />
              </Grid>
              {(formData.tipo === 'medico') && (
                <Grid item xs={12}>
                  <TextField
                    fullWidth
                    label="Especialidade"
                    value={formData.especialidade}
                    onChange={(e) => setFormData({ ...formData, especialidade: e.target.value })}
                  />
                </Grid>
              )}
            </Grid>
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setOpenDialog(false)}>Cancelar</Button>
          <Button variant="contained" onClick={handleSave}>
            {isEditing ? 'Salvar' : 'Criar'}
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
}; 