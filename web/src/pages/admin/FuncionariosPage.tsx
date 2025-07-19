import React, { useState, useEffect } from 'react';
import {
  Box,
  Card,
  CardContent,
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
  CircularProgress,
  Alert,
  Pagination,
  Snackbar
} from '@mui/material';
import {
  Add as AddIcon,
  Edit as EditIcon,
  Delete as DeleteIcon,
  Search as SearchIcon
} from '@mui/icons-material';
import { useForm, Controller } from 'react-hook-form';
import { apiService } from '../../services/api';
import { FuncionarioResponse, FuncionarioCreate, FuncionarioUpdate } from '../../types';

interface FuncionarioFormData extends Omit<FuncionarioCreate, 'senha'> {
  senha?: string;
}

const FuncionariosPage: React.FC = () => {
  const [funcionarios, setFuncionarios] = useState<FuncionarioResponse[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [openDialog, setOpenDialog] = useState(false);
  const [editingFuncionario, setEditingFuncionario] = useState<FuncionarioResponse | null>(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [filtroTipo, setFiltroTipo] = useState('');
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [snackbar, setSnackbar] = useState({ open: false, message: '', severity: 'success' as 'success' | 'error' });

  const { control, handleSubmit, reset, formState: { errors } } = useForm<FuncionarioFormData>();

  // Carregar funcionários da API
  const loadFuncionarios = async () => {
    try {
      setLoading(true);
      setError(null);
      
      const response = await apiService.getFuncionarios({
        skip: (page - 1) * 10,
        limit: 10,
        search: searchTerm || undefined,
        tipo: filtroTipo || undefined
      });
      
      setFuncionarios(response.funcionarios);
      setTotalPages(Math.ceil(response.total / 10));
    } catch (err: any) {
      console.error('Erro ao carregar funcionários:', err);
      setError(err.message || 'Erro ao carregar funcionários');
      setSnackbar({ 
        open: true, 
        message: 'Erro ao carregar funcionários', 
        severity: 'error' 
      });
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadFuncionarios();
  }, [page, searchTerm, filtroTipo]);

  // Criar funcionário
  const handleCreate = async (data: FuncionarioFormData) => {
    try {
      if (!data.senha) {
        setSnackbar({ 
          open: true, 
          message: 'Senha é obrigatória para criar funcionário', 
          severity: 'error' 
        });
        return;
      }

      const createData: FuncionarioCreate = {
        nome: data.nome,
        usuario: data.usuario,
        email: data.email,
        telefone: data.telefone || undefined,
        tipo: data.tipo,
        senha: data.senha,
        crm: data.crm || undefined,
        especialidade: data.especialidade || undefined,
        coren: data.coren || undefined,
        crf: data.crf || undefined,
        setor: data.setor || undefined
      };

      await apiService.createFuncionario(createData);
      
      setSnackbar({ 
        open: true, 
        message: 'Funcionário criado com sucesso!', 
        severity: 'success' 
      });
      
      setOpenDialog(false);
      reset();
      loadFuncionarios();
    } catch (err: any) {
      console.error('Erro ao criar funcionário:', err);
      setSnackbar({ 
        open: true, 
        message: err.message || 'Erro ao criar funcionário', 
        severity: 'error' 
      });
    }
  };

  // Atualizar funcionário
  const handleUpdate = async (data: FuncionarioFormData) => {
    if (!editingFuncionario) return;

    try {
      const updateData: FuncionarioUpdate = {
        nome: data.nome !== editingFuncionario.nome ? data.nome : undefined,
        email: data.email !== editingFuncionario.email ? data.email : undefined,
        telefone: data.telefone !== editingFuncionario.telefone ? data.telefone : undefined,
        crm: data.crm !== editingFuncionario.crm ? data.crm : undefined,
        especialidade: data.especialidade !== editingFuncionario.especialidade ? data.especialidade : undefined,
        coren: data.coren !== editingFuncionario.coren ? data.coren : undefined,
        crf: data.crf !== editingFuncionario.crf ? data.crf : undefined,
        setor: data.setor !== editingFuncionario.setor ? data.setor : undefined,
        senha: data.senha || undefined
      };

      await apiService.updateFuncionario(editingFuncionario.id, updateData);
      
      setSnackbar({ 
        open: true, 
        message: 'Funcionário atualizado com sucesso!', 
        severity: 'success' 
      });
      
      setOpenDialog(false);
      setEditingFuncionario(null);
      reset();
      loadFuncionarios();
    } catch (err: any) {
      console.error('Erro ao atualizar funcionário:', err);
      setSnackbar({ 
        open: true, 
        message: err.message || 'Erro ao atualizar funcionário', 
        severity: 'error' 
      });
    }
  };

  // Deletar funcionário
  const handleDelete = async (funcionario: FuncionarioResponse) => {
    if (!window.confirm(`Tem certeza que deseja deletar o funcionário ${funcionario.nome}?`)) {
      return;
    }

    try {
      await apiService.deleteFuncionario(funcionario.id);
      
      setSnackbar({ 
        open: true, 
        message: 'Funcionário deletado com sucesso!', 
        severity: 'success' 
      });
      
      loadFuncionarios();
    } catch (err: any) {
      console.error('Erro ao deletar funcionário:', err);
      setSnackbar({ 
        open: true, 
        message: err.message || 'Erro ao deletar funcionário', 
        severity: 'error' 
      });
    }
  };

  const openCreateDialog = () => {
    setEditingFuncionario(null);
    reset({
      nome: '',
      usuario: '',
      email: '',
      telefone: '',
      tipo: '',
      senha: '',
      crm: '',
      especialidade: '',
      coren: '',
      crf: '',
      setor: ''
    });
    setOpenDialog(true);
  };

  const openEditDialog = (funcionario: FuncionarioResponse) => {
    setEditingFuncionario(funcionario);
    reset({
      nome: funcionario.nome,
      usuario: funcionario.usuario,
      email: funcionario.email,
      telefone: funcionario.telefone || '',
      tipo: funcionario.tipo,
      senha: '', // Não preencher senha ao editar
      crm: funcionario.crm || '',
      especialidade: funcionario.especialidade || '',
      coren: funcionario.coren || '',
      crf: funcionario.crf || '',
      setor: funcionario.setor || ''
    });
    setOpenDialog(true);
  };

  const getTipoChipColor = (tipo: string) => {
    switch (tipo) {
      case 'administrador': return 'error';
      case 'medico': return 'primary';
      case 'enfermeiro': return 'success';
      case 'atendente': return 'warning';
      case 'farmaceutico': return 'info';
      default: return 'default';
    }
  };

  const onSubmit = editingFuncionario ? handleUpdate : handleCreate;

  if (loading && funcionarios.length === 0) {
    return (
      <Box display="flex" justifyContent="center" alignItems="center" minHeight="400px">
        <CircularProgress />
      </Box>
    );
  }

  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Gerenciamento de Funcionários
      </Typography>

      <Card sx={{ mb: 3 }}>
        <CardContent>
          <Grid container spacing={2} alignItems="center">
            <Grid item xs={12} sm={4}>
              <TextField
                fullWidth
                label="Buscar funcionários"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                InputProps={{
                  startAdornment: <SearchIcon sx={{ color: 'action.active', mr: 1 }} />
                }}
              />
            </Grid>
            <Grid item xs={12} sm={3}>
              <FormControl fullWidth>
                <InputLabel>Filtrar por tipo</InputLabel>
                <Select
                  value={filtroTipo}
                  label="Filtrar por tipo"
                  onChange={(e) => setFiltroTipo(e.target.value)}
                >
                  <MenuItem value="">Todos</MenuItem>
                  <MenuItem value="administrador">Administrador</MenuItem>
                  <MenuItem value="medico">Médico</MenuItem>
                  <MenuItem value="enfermeiro">Enfermeiro</MenuItem>
                  <MenuItem value="atendente">Atendente</MenuItem>
                  <MenuItem value="farmaceutico">Farmacêutico</MenuItem>
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={12} sm={3}>
              <Button
                variant="contained"
                startIcon={<AddIcon />}
                onClick={openCreateDialog}
                fullWidth
              >
                Novo Funcionário
              </Button>
            </Grid>
          </Grid>
        </CardContent>
      </Card>

      {error && (
        <Alert severity="error" sx={{ mb: 2 }}>
          {error}
        </Alert>
      )}

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Nome</TableCell>
              <TableCell>Usuário</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Tipo</TableCell>
              <TableCell>Detalhes</TableCell>
              <TableCell>Ações</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {funcionarios.map((funcionario) => (
              <TableRow key={funcionario.id}>
                <TableCell>{funcionario.nome}</TableCell>
                <TableCell>{funcionario.usuario}</TableCell>
                <TableCell>{funcionario.email}</TableCell>
                <TableCell>
                  <Chip 
                    label={funcionario.tipo} 
                    color={getTipoChipColor(funcionario.tipo)}
                    size="small"
                  />
                </TableCell>
                <TableCell>
                  {funcionario.crm && <Typography variant="caption" display="block">CRM: {funcionario.crm}</Typography>}
                  {funcionario.especialidade && <Typography variant="caption" display="block">Esp: {funcionario.especialidade}</Typography>}
                  {funcionario.coren && <Typography variant="caption" display="block">COREN: {funcionario.coren}</Typography>}
                  {funcionario.crf && <Typography variant="caption" display="block">CRF: {funcionario.crf}</Typography>}
                  {funcionario.setor && <Typography variant="caption" display="block">Setor: {funcionario.setor}</Typography>}
                </TableCell>
                <TableCell>
                  <IconButton 
                    onClick={() => openEditDialog(funcionario)}
                    color="primary"
                    size="small"
                  >
                    <EditIcon />
                  </IconButton>
                  <IconButton 
                    onClick={() => handleDelete(funcionario)}
                    color="error"
                    size="small"
                  >
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {totalPages > 1 && (
        <Box display="flex" justifyContent="center" mt={3}>
          <Pagination
            count={totalPages}
            page={page}
            onChange={(_, newPage) => setPage(newPage)}
            color="primary"
          />
        </Box>
      )}

      {/* Dialog para criar/editar funcionário */}
      <Dialog open={openDialog} onClose={() => setOpenDialog(false)} maxWidth="md" fullWidth>
        <DialogTitle>
          {editingFuncionario ? 'Editar Funcionário' : 'Novo Funcionário'}
        </DialogTitle>
        <form onSubmit={handleSubmit(onSubmit)}>
          <DialogContent>
            <Grid container spacing={2}>
              <Grid item xs={12} sm={6}>
                <Controller
                  name="nome"
                  control={control}
                  rules={{ required: 'Nome é obrigatório' }}
                  render={({ field }) => (
                    <TextField
                      {...field}
                      label="Nome"
                      fullWidth
                      error={!!errors.nome}
                      helperText={errors.nome?.message}
                    />
                  )}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <Controller
                  name="usuario"
                  control={control}
                  rules={{ required: 'Usuário é obrigatório' }}
                  render={({ field }) => (
                    <TextField
                      {...field}
                      label="Usuário"
                      fullWidth
                      disabled={!!editingFuncionario}
                      error={!!errors.usuario}
                      helperText={errors.usuario?.message}
                    />
                  )}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <Controller
                  name="email"
                  control={control}
                  rules={{ 
                    required: 'Email é obrigatório',
                    pattern: {
                      value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                      message: 'Email inválido'
                    }
                  }}
                  render={({ field }) => (
                    <TextField
                      {...field}
                      label="Email"
                      type="email"
                      fullWidth
                      error={!!errors.email}
                      helperText={errors.email?.message}
                    />
                  )}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <Controller
                  name="telefone"
                  control={control}
                  render={({ field }) => (
                    <TextField
                      {...field}
                      label="Telefone"
                      fullWidth
                    />
                  )}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <Controller
                  name="tipo"
                  control={control}
                  rules={{ required: 'Tipo é obrigatório' }}
                  render={({ field }) => (
                    <FormControl fullWidth error={!!errors.tipo}>
                      <InputLabel>Tipo</InputLabel>
                      <Select {...field} label="Tipo" disabled={!!editingFuncionario}>
                        <MenuItem value="administrador">Administrador</MenuItem>
                        <MenuItem value="medico">Médico</MenuItem>
                        <MenuItem value="enfermeiro">Enfermeiro</MenuItem>
                        <MenuItem value="atendente">Atendente</MenuItem>
                        <MenuItem value="farmaceutico">Farmacêutico</MenuItem>
                      </Select>
                      {errors.tipo && (
                        <Typography variant="caption" color="error">
                          {errors.tipo.message}
                        </Typography>
                      )}
                    </FormControl>
                  )}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <Controller
                  name="senha"
                  control={control}
                  rules={!editingFuncionario ? { required: 'Senha é obrigatória' } : {}}
                  render={({ field }) => (
                    <TextField
                      {...field}
                      label={editingFuncionario ? "Nova Senha (opcional)" : "Senha"}
                      type="password"
                      fullWidth
                      error={!!errors.senha}
                      helperText={errors.senha?.message}
                    />
                  )}
                />
              </Grid>

              {/* Campos específicos por tipo */}
              <Controller
                name="tipo"
                control={control}
                render={({ field: { value } }) => (
                  <>
                    {(value === 'medico') && (
                      <>
                        <Grid item xs={12} sm={6}>
                          <Controller
                            name="crm"
                            control={control}
                            render={({ field }) => (
                              <TextField
                                {...field}
                                label="CRM"
                                fullWidth
                              />
                            )}
                          />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                          <Controller
                            name="especialidade"
                            control={control}
                            render={({ field }) => (
                              <TextField
                                {...field}
                                label="Especialidade"
                                fullWidth
                              />
                            )}
                          />
                        </Grid>
                      </>
                    )}
                    {value === 'enfermeiro' && (
                      <Grid item xs={12} sm={6}>
                        <Controller
                          name="coren"
                          control={control}
                          render={({ field }) => (
                            <TextField
                              {...field}
                              label="COREN"
                              fullWidth
                            />
                          )}
                        />
                      </Grid>
                    )}
                    {value === 'farmaceutico' && (
                      <Grid item xs={12} sm={6}>
                        <Controller
                          name="crf"
                          control={control}
                          render={({ field }) => (
                            <TextField
                              {...field}
                              label="CRF"
                              fullWidth
                            />
                          )}
                        />
                      </Grid>
                    )}
                    {(value === 'administrador' || value === 'atendente') && (
                      <Grid item xs={12} sm={6}>
                        <Controller
                          name="setor"
                          control={control}
                          render={({ field }) => (
                            <TextField
                              {...field}
                              label="Setor"
                              fullWidth
                            />
                          )}
                        />
                      </Grid>
                    )}
                  </>
                )}
              />
            </Grid>
          </DialogContent>
          <DialogActions>
            <Button onClick={() => setOpenDialog(false)}>
              Cancelar
            </Button>
            <Button type="submit" variant="contained">
              {editingFuncionario ? 'Atualizar' : 'Criar'}
            </Button>
          </DialogActions>
        </form>
      </Dialog>

      {/* Snackbar para notificações */}
      <Snackbar
        open={snackbar.open}
        autoHideDuration={6000}
        onClose={() => setSnackbar({ ...snackbar, open: false })}
      >
        <Alert 
          onClose={() => setSnackbar({ ...snackbar, open: false })} 
          severity={snackbar.severity}
        >
          {snackbar.message}
        </Alert>
      </Snackbar>
    </Box>
  );
};

export default FuncionariosPage; 