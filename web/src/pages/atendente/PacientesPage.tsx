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
  Search as SearchIcon,
  Person as PersonIcon
} from '@mui/icons-material';
import { useForm, Controller } from 'react-hook-form';
import { LocalizationProvider, DatePicker } from '@mui/x-date-pickers';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFnsV3';
import { format } from 'date-fns';
import { ptBR } from 'date-fns/locale';
import { apiService } from '../../services/api';
import { PacienteResponse, PacienteCreate, PacienteUpdate } from '../../types';

interface PacienteFormData extends PacienteCreate {
  data_nascimento: Date;
}

const PacientesPage: React.FC = () => {
  const [pacientes, setPacientes] = useState<PacienteResponse[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [openDialog, setOpenDialog] = useState(false);
  const [editingPaciente, setEditingPaciente] = useState<PacienteResponse | null>(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [snackbar, setSnackbar] = useState({ open: false, message: '', severity: 'success' as 'success' | 'error' });

  const { control, handleSubmit, reset, formState: { errors } } = useForm<PacienteFormData>();

  // Carregar pacientes da API
  const loadPacientes = async () => {
    try {
      setLoading(true);
      setError(null);
      
      const response = await apiService.getPacientes({
        skip: (page - 1) * 10,
        limit: 10,
        search: searchTerm || undefined
      });
      
      setPacientes(response);
      setTotalPages(Math.ceil(response.length / 10)); // API ainda não retorna total
    } catch (err: any) {
      console.error('Erro ao carregar pacientes:', err);
      setError(err.message || 'Erro ao carregar pacientes');
      setSnackbar({ 
        open: true, 
        message: 'Erro ao carregar pacientes', 
        severity: 'error' 
      });
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadPacientes();
  }, [page, searchTerm]);

  // Criar paciente
  const handleCreate = async (data: PacienteFormData) => {
    try {
      const createData: PacienteCreate = {
        nome: data.nome,
        rg: data.rg,
        cpf: data.cpf,
        sexo: data.sexo,
        data_nascimento: format(data.data_nascimento, 'yyyy-MM-dd'),
        telefone: data.telefone || undefined,
        email: data.email || undefined,
        cidade_estado: data.cidade_estado || undefined,
        endereco: data.endereco || undefined,
        patologia: data.patologia || undefined
      };

      await apiService.createPaciente(createData);
      
      setSnackbar({ 
        open: true, 
        message: 'Paciente criado com sucesso!', 
        severity: 'success' 
      });
      
      setOpenDialog(false);
      reset();
      loadPacientes();
    } catch (err: any) {
      console.error('Erro ao criar paciente:', err);
      setSnackbar({ 
        open: true, 
        message: err.message || 'Erro ao criar paciente', 
        severity: 'error' 
      });
    }
  };

  // Atualizar paciente
  const handleUpdate = async (data: PacienteFormData) => {
    if (!editingPaciente) return;

    try {
      const updateData: PacienteUpdate = {
        nome: data.nome !== editingPaciente.nome ? data.nome : undefined,
        telefone: data.telefone !== editingPaciente.telefone ? data.telefone : undefined,
        email: data.email !== editingPaciente.email ? data.email : undefined,
        cidade_estado: data.cidade_estado !== editingPaciente.cidade_estado ? data.cidade_estado : undefined,
        endereco: data.endereco !== editingPaciente.endereco ? data.endereco : undefined,
        patologia: data.patologia !== editingPaciente.patologia ? data.patologia : undefined
      };

      await apiService.updatePaciente(editingPaciente.id, updateData);
      
      setSnackbar({ 
        open: true, 
        message: 'Paciente atualizado com sucesso!', 
        severity: 'success' 
      });
      
      setOpenDialog(false);
      setEditingPaciente(null);
      reset();
      loadPacientes();
    } catch (err: any) {
      console.error('Erro ao atualizar paciente:', err);
      setSnackbar({ 
        open: true, 
        message: err.message || 'Erro ao atualizar paciente', 
        severity: 'error' 
      });
    }
  };

  // Deletar paciente
  const handleDelete = async (paciente: PacienteResponse) => {
    if (!window.confirm(`Tem certeza que deseja deletar o paciente ${paciente.nome}?`)) {
      return;
    }

    try {
      await apiService.deletePaciente(paciente.id);
      
      setSnackbar({ 
        open: true, 
        message: 'Paciente deletado com sucesso!', 
        severity: 'success' 
      });
      
      loadPacientes();
    } catch (err: any) {
      console.error('Erro ao deletar paciente:', err);
      setSnackbar({ 
        open: true, 
        message: err.message || 'Erro ao deletar paciente', 
        severity: 'error' 
      });
    }
  };

  const openCreateDialog = () => {
    setEditingPaciente(null);
    reset({
      nome: '',
      rg: '',
      cpf: '',
      sexo: 'M',
      data_nascimento: new Date(),
      telefone: '',
      email: '',
      cidade_estado: '',
      endereco: '',
      patologia: ''
    });
    setOpenDialog(true);
  };

  const openEditDialog = (paciente: PacienteResponse) => {
    setEditingPaciente(paciente);
    reset({
      nome: paciente.nome,
      rg: paciente.rg,
      cpf: paciente.cpf,
      sexo: paciente.sexo,
      data_nascimento: new Date(paciente.data_nascimento),
      telefone: paciente.telefone || '',
      email: paciente.email || '',
      cidade_estado: paciente.cidade_estado || '',
      endereco: paciente.endereco || '',
      patologia: paciente.patologia || ''
    });
    setOpenDialog(true);
  };

  const getSexoChipColor = (sexo: string) => {
    return sexo === 'M' ? 'primary' : 'secondary';
  };

  const onSubmit = editingPaciente ? handleUpdate : handleCreate;

  if (loading && pacientes.length === 0) {
    return (
      <Box display="flex" justifyContent="center" alignItems="center" minHeight="400px">
        <CircularProgress />
      </Box>
    );
  }

  return (
    <LocalizationProvider dateAdapter={AdapterDateFns} adapterLocale={ptBR}>
      <Box>
        <Typography variant="h4" gutterBottom>
          <PersonIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
          Gerenciamento de Pacientes
        </Typography>

        <Card sx={{ mb: 3 }}>
          <CardContent>
            <Grid container spacing={2} alignItems="center">
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  label="Buscar pacientes (nome ou CPF)"
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                  InputProps={{
                    startAdornment: <SearchIcon sx={{ color: 'action.active', mr: 1 }} />
                  }}
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <Button
                  variant="contained"
                  startIcon={<AddIcon />}
                  onClick={openCreateDialog}
                  fullWidth
                >
                  Novo Paciente
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
                <TableCell>CPF</TableCell>
                <TableCell>Idade</TableCell>
                <TableCell>Sexo</TableCell>
                <TableCell>Telefone</TableCell>
                <TableCell>Email</TableCell>
                <TableCell>Ações</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {pacientes.map((paciente) => (
                <TableRow key={paciente.id}>
                  <TableCell>
                    <Typography variant="body1" fontWeight="medium">
                      {paciente.nome}
                    </Typography>
                    {paciente.patologia && (
                      <Typography variant="caption" color="text.secondary" display="block">
                        Patologia: {paciente.patologia}
                      </Typography>
                    )}
                  </TableCell>
                  <TableCell>{paciente.cpf}</TableCell>
                  <TableCell>{paciente.idade} anos</TableCell>
                  <TableCell>
                    <Chip 
                      label={paciente.sexo === 'M' ? 'Masculino' : 'Feminino'} 
                      color={getSexoChipColor(paciente.sexo)}
                      size="small"
                    />
                  </TableCell>
                  <TableCell>{paciente.telefone || '-'}</TableCell>
                  <TableCell>{paciente.email || '-'}</TableCell>
                  <TableCell>
                    <IconButton 
                      onClick={() => openEditDialog(paciente)}
                      color="primary"
                      size="small"
                    >
                      <EditIcon />
                    </IconButton>
                    <IconButton 
                      onClick={() => handleDelete(paciente)}
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

        {/* Dialog para criar/editar paciente */}
        <Dialog open={openDialog} onClose={() => setOpenDialog(false)} maxWidth="md" fullWidth>
          <DialogTitle>
            {editingPaciente ? 'Editar Paciente' : 'Novo Paciente'}
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
                        label="Nome completo"
                        fullWidth
                        error={!!errors.nome}
                        helperText={errors.nome?.message}
                      />
                    )}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <Controller
                    name="rg"
                    control={control}
                    rules={{ required: 'RG é obrigatório' }}
                    render={({ field }) => (
                      <TextField
                        {...field}
                        label="RG"
                        fullWidth
                        disabled={!!editingPaciente}
                        error={!!errors.rg}
                        helperText={errors.rg?.message}
                      />
                    )}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <Controller
                    name="cpf"
                    control={control}
                    rules={{ 
                      required: 'CPF é obrigatório',
                      pattern: {
                        value: /^\d{3}\.\d{3}\.\d{3}-\d{2}$/,
                        message: 'CPF deve estar no formato 000.000.000-00'
                      }
                    }}
                    render={({ field }) => (
                      <TextField
                        {...field}
                        label="CPF"
                        placeholder="000.000.000-00"
                        fullWidth
                        disabled={!!editingPaciente}
                        error={!!errors.cpf}
                        helperText={errors.cpf?.message}
                      />
                    )}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <Controller
                    name="sexo"
                    control={control}
                    rules={{ required: 'Sexo é obrigatório' }}
                    render={({ field }) => (
                      <FormControl fullWidth error={!!errors.sexo}>
                        <InputLabel>Sexo</InputLabel>
                        <Select {...field} label="Sexo" disabled={!!editingPaciente}>
                          <MenuItem value="M">Masculino</MenuItem>
                          <MenuItem value="F">Feminino</MenuItem>
                        </Select>
                        {errors.sexo && (
                          <Typography variant="caption" color="error">
                            {errors.sexo.message}
                          </Typography>
                        )}
                      </FormControl>
                    )}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <Controller
                    name="data_nascimento"
                    control={control}
                    rules={{ required: 'Data de nascimento é obrigatória' }}
                    render={({ field }) => (
                      <DatePicker
                        {...field}
                        label="Data de nascimento"
                        disabled={!!editingPaciente}
                        slotProps={{
                          textField: {
                            fullWidth: true,
                            error: !!errors.data_nascimento,
                            helperText: errors.data_nascimento?.message
                          }
                        }}
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
                        placeholder="(11) 99999-9999"
                        fullWidth
                      />
                    )}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <Controller
                    name="email"
                    control={control}
                    rules={{
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
                    name="cidade_estado"
                    control={control}
                    render={({ field }) => (
                      <TextField
                        {...field}
                        label="Cidade/Estado"
                        placeholder="São Paulo/SP"
                        fullWidth
                      />
                    )}
                  />
                </Grid>
                <Grid item xs={12}>
                  <Controller
                    name="endereco"
                    control={control}
                    render={({ field }) => (
                      <TextField
                        {...field}
                        label="Endereço completo"
                        fullWidth
                        multiline
                        rows={2}
                      />
                    )}
                  />
                </Grid>
                <Grid item xs={12}>
                  <Controller
                    name="patologia"
                    control={control}
                    render={({ field }) => (
                      <TextField
                        {...field}
                        label="Patologias/Observações médicas"
                        fullWidth
                        multiline
                        rows={3}
                        placeholder="Descreva patologias, alergias ou observações importantes..."
                      />
                    )}
                  />
                </Grid>
              </Grid>
            </DialogContent>
            <DialogActions>
              <Button onClick={() => setOpenDialog(false)}>
                Cancelar
              </Button>
              <Button type="submit" variant="contained">
                {editingPaciente ? 'Atualizar' : 'Criar'}
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
    </LocalizationProvider>
  );
};

export default PacientesPage; 