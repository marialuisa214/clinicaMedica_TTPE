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
  EventNote as EventIcon,
  Person as PersonIcon
} from '@mui/icons-material';
import { useForm, Controller } from 'react-hook-form';
import { LocalizationProvider, DateTimePicker } from '@mui/x-date-pickers';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { format } from 'date-fns';
import { ptBR } from 'date-fns/locale';
import { apiService } from '../../services/api';
import { ConsultaResponse, ConsultaCreate, ConsultaUpdate, PacienteResponse, FuncionarioResponse, StatusConsulta, TipoConsulta } from '../../types';

interface ConsultaFormData extends Omit<ConsultaCreate, 'data_hora'> {
  data_hora: Date;
}

const ConsultasPage: React.FC = () => {
  const [consultas, setConsultas] = useState<ConsultaResponse[]>([]);
  const [pacientes, setPacientes] = useState<PacienteResponse[]>([]);
  const [medicos, setMedicos] = useState<FuncionarioResponse[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [openDialog, setOpenDialog] = useState(false);
  const [editingConsulta, setEditingConsulta] = useState<ConsultaResponse | null>(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [filtroStatus, setFiltroStatus] = useState('');
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [snackbar, setSnackbar] = useState({ open: false, message: '', severity: 'success' as 'success' | 'error' });

  const { control, handleSubmit, reset, formState: { errors } } = useForm<ConsultaFormData>();

  // Carregar dados iniciais
  useEffect(() => {
    const loadInitialData = async () => {
      try {
        const [pacientesData, medicosData] = await Promise.all([
          apiService.getPacientes({ limit: 1000 }),
          apiService.getMedicos()
        ]);
        
        setPacientes(pacientesData);
        setMedicos(medicosData);
      } catch (err: any) {
        console.error('Erro ao carregar dados iniciais:', err);
        setSnackbar({ 
          open: true, 
          message: 'Erro ao carregar pacientes e médicos', 
          severity: 'error' 
        });
      }
    };

    loadInitialData();
  }, []);

  // Carregar consultas
  const loadConsultas = async () => {
    try {
      setLoading(true);
      setError(null);
      
      const response = await apiService.getConsultas({
        skip: (page - 1) * 10,
        limit: 10,
        status: filtroStatus || undefined
      });
      
      setConsultas(response.consultas);
      setTotalPages(Math.ceil(response.total / 10));
    } catch (err: any) {
      console.error('Erro ao carregar consultas:', err);
      setError(err.message || 'Erro ao carregar consultas');
      setSnackbar({ 
        open: true, 
        message: 'Erro ao carregar consultas', 
        severity: 'error' 
      });
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadConsultas();
  }, [page, filtroStatus]);

  // Criar consulta
  const handleCreate = async (data: ConsultaFormData) => {
    try {
      const createData: ConsultaCreate = {
        paciente_id: data.paciente_id,
        medico_id: data.medico_id,
        data_hora: data.data_hora.toISOString(),
        tipo: data.tipo,
        motivo: data.motivo || undefined,
        observacoes: data.observacoes || undefined
      };

      await apiService.createConsulta(createData);
      
      setSnackbar({ 
        open: true, 
        message: 'Consulta agendada com sucesso!', 
        severity: 'success' 
      });
      
      setOpenDialog(false);
      reset();
      loadConsultas();
    } catch (err: any) {
      console.error('Erro ao criar consulta:', err);
      setSnackbar({ 
        open: true, 
        message: err.message || 'Erro ao agendar consulta', 
        severity: 'error' 
      });
    }
  };

  // Atualizar consulta
  const handleUpdate = async (data: ConsultaFormData) => {
    if (!editingConsulta) return;

    try {
      const updateData: ConsultaUpdate = {
        data_hora: data.data_hora.toISOString(),
        tipo: data.tipo !== editingConsulta.tipo ? data.tipo : undefined,
        motivo: data.motivo !== editingConsulta.motivo ? data.motivo : undefined,
        observacoes: data.observacoes !== editingConsulta.observacoes ? data.observacoes : undefined
      };

      await apiService.updateConsulta(editingConsulta.id, updateData);
      
      setSnackbar({ 
        open: true, 
        message: 'Consulta atualizada com sucesso!', 
        severity: 'success' 
      });
      
      setOpenDialog(false);
      setEditingConsulta(null);
      reset();
      loadConsultas();
    } catch (err: any) {
      console.error('Erro ao atualizar consulta:', err);
      setSnackbar({ 
        open: true, 
        message: err.message || 'Erro ao atualizar consulta', 
        severity: 'error' 
      });
    }
  };

  // Cancelar consulta
  const handleDelete = async (consulta: ConsultaResponse) => {
    if (!window.confirm(`Tem certeza que deseja cancelar a consulta de ${consulta.paciente_nome}?`)) {
      return;
    }

    try {
      await apiService.deleteConsulta(consulta.id);
      
      setSnackbar({ 
        open: true, 
        message: 'Consulta cancelada com sucesso!', 
        severity: 'success' 
      });
      
      loadConsultas();
    } catch (err: any) {
      console.error('Erro ao cancelar consulta:', err);
      setSnackbar({ 
        open: true, 
        message: err.message || 'Erro ao cancelar consulta', 
        severity: 'error' 
      });
    }
  };

  const openCreateDialog = () => {
    setEditingConsulta(null);
    reset({
      paciente_id: 0,
      medico_id: 0,
      data_hora: new Date(),
      tipo: 'consulta_normal',
      motivo: '',
      observacoes: ''
    });
    setOpenDialog(true);
  };

  const openEditDialog = (consulta: ConsultaResponse) => {
    setEditingConsulta(consulta);
    reset({
      paciente_id: consulta.paciente_id,
      medico_id: consulta.medico_id,
      data_hora: new Date(consulta.data_hora),
      tipo: consulta.tipo,
      motivo: consulta.motivo || '',
      observacoes: consulta.observacoes || ''
    });
    setOpenDialog(true);
  };

  const getStatusChipColor = (status: StatusConsulta) => {
    switch (status) {
      case 'agendada': return 'default';
      case 'confirmada': return 'info';
      case 'em_andamento': return 'warning';
      case 'finalizada': return 'success';
      case 'cancelada': return 'error';
      default: return 'default';
    }
  };

  const getStatusLabel = (status: StatusConsulta) => {
    switch (status) {
      case 'agendada': return 'Agendada';
      case 'confirmada': return 'Confirmada';
      case 'em_andamento': return 'Em Andamento';
      case 'finalizada': return 'Finalizada';
      case 'cancelada': return 'Cancelada';
      default: return status;
    }
  };

  const onSubmit = editingConsulta ? handleUpdate : handleCreate;

  if (loading && consultas.length === 0) {
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
          <EventIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
          Gerenciamento de Consultas
        </Typography>

        <Card sx={{ mb: 3 }}>
          <CardContent>
            <Grid container spacing={2} alignItems="center">
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Buscar consultas"
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                  InputProps={{
                    startAdornment: <SearchIcon sx={{ color: 'action.active', mr: 1 }} />
                  }}
                />
              </Grid>
              <Grid item xs={12} sm={3}>
                <FormControl fullWidth>
                  <InputLabel>Filtrar por status</InputLabel>
                  <Select
                    value={filtroStatus}
                    label="Filtrar por status"
                    onChange={(e) => setFiltroStatus(e.target.value)}
                  >
                    <MenuItem value="">Todos</MenuItem>
                    <MenuItem value="agendada">Agendada</MenuItem>
                    <MenuItem value="confirmada">Confirmada</MenuItem>
                    <MenuItem value="em_andamento">Em Andamento</MenuItem>
                    <MenuItem value="finalizada">Finalizada</MenuItem>
                    <MenuItem value="cancelada">Cancelada</MenuItem>
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
                  Agendar Consulta
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
                <TableCell>Data/Hora</TableCell>
                <TableCell>Paciente</TableCell>
                <TableCell>Médico</TableCell>
                <TableCell>Tipo</TableCell>
                <TableCell>Status</TableCell>
                <TableCell>Motivo</TableCell>
                <TableCell>Ações</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {consultas.map((consulta) => (
                <TableRow key={consulta.id}>
                  <TableCell>
                    <Typography variant="body2">
                      {format(new Date(consulta.data_hora), 'dd/MM/yyyy')}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                      {format(new Date(consulta.data_hora), 'HH:mm')}
                    </Typography>
                  </TableCell>
                  <TableCell>
                    <Box sx={{ display: 'flex', alignItems: 'center' }}>
                      <PersonIcon sx={{ mr: 1, color: 'text.secondary' }} />
                      {consulta.paciente_nome || `Paciente #${consulta.paciente_id}`}
                    </Box>
                  </TableCell>
                  <TableCell>
                    {consulta.medico_nome || `Dr. #${consulta.medico_id}`}
                  </TableCell>
                  <TableCell>
                    <Chip 
                      label={consulta.tipo} 
                      size="small" 
                      variant="outlined"
                    />
                  </TableCell>
                  <TableCell>
                    <Chip 
                      label={getStatusLabel(consulta.status)} 
                      color={getStatusChipColor(consulta.status)}
                      size="small"
                    />
                  </TableCell>
                  <TableCell>
                    <Typography variant="body2" sx={{ maxWidth: 200 }}>
                      {consulta.motivo || '-'}
                    </Typography>
                  </TableCell>
                  <TableCell>
                    <IconButton 
                      onClick={() => openEditDialog(consulta)}
                      color="primary"
                      size="small"
                      disabled={consulta.status === 'finalizada'}
                    >
                      <EditIcon />
                    </IconButton>
                    <IconButton 
                      onClick={() => handleDelete(consulta)}
                      color="error"
                      size="small"
                      disabled={consulta.status === 'finalizada' || consulta.status === 'cancelada'}
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

        {/* Dialog para criar/editar consulta */}
        <Dialog open={openDialog} onClose={() => setOpenDialog(false)} maxWidth="md" fullWidth>
          <DialogTitle>
            {editingConsulta ? 'Editar Consulta' : 'Agendar Nova Consulta'}
          </DialogTitle>
          <form onSubmit={handleSubmit(onSubmit)}>
            <DialogContent>
              <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                  <Controller
                    name="paciente_id"
                    control={control}
                    rules={{ required: 'Paciente é obrigatório' }}
                    render={({ field }) => (
                      <FormControl fullWidth error={!!errors.paciente_id}>
                        <InputLabel>Paciente</InputLabel>
                        <Select {...field} label="Paciente" disabled={!!editingConsulta}>
                          {pacientes.map((paciente) => (
                            <MenuItem key={paciente.id} value={paciente.id}>
                              {paciente.nome} - {paciente.cpf}
                            </MenuItem>
                          ))}
                        </Select>
                        {errors.paciente_id && (
                          <Typography variant="caption" color="error">
                            {errors.paciente_id.message}
                          </Typography>
                        )}
                      </FormControl>
                    )}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <Controller
                    name="medico_id"
                    control={control}
                    rules={{ required: 'Médico é obrigatório' }}
                    render={({ field }) => (
                      <FormControl fullWidth error={!!errors.medico_id}>
                        <InputLabel>Médico</InputLabel>
                        <Select {...field} label="Médico" disabled={!!editingConsulta}>
                          {medicos.map((medico) => (
                            <MenuItem key={medico.id} value={medico.id}>
                              {medico.nome} - {medico.especialidade}
                            </MenuItem>
                          ))}
                        </Select>
                        {errors.medico_id && (
                          <Typography variant="caption" color="error">
                            {errors.medico_id.message}
                          </Typography>
                        )}
                      </FormControl>
                    )}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <Controller
                    name="data_hora"
                    control={control}
                    rules={{ required: 'Data e hora são obrigatórias' }}
                    render={({ field }) => (
                      <DateTimePicker
                        {...field}
                        label="Data e horário"
                        slotProps={{
                          textField: {
                            fullWidth: true,
                            error: !!errors.data_hora,
                            helperText: errors.data_hora?.message
                          }
                        }}
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
                        <InputLabel>Tipo da consulta</InputLabel>
                        <Select {...field} label="Tipo da consulta">
                          <MenuItem value="consulta_normal">Consulta Normal</MenuItem>
                          <MenuItem value="retorno">Retorno</MenuItem>
                          <MenuItem value="emergencia">Emergência</MenuItem>
                          <MenuItem value="exame">Exame</MenuItem>
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
                <Grid item xs={12}>
                  <Controller
                    name="motivo"
                    control={control}
                    render={({ field }) => (
                      <TextField
                        {...field}
                        label="Motivo da consulta"
                        fullWidth
                        multiline
                        rows={2}
                        placeholder="Descreva o motivo da consulta..."
                      />
                    )}
                  />
                </Grid>
                <Grid item xs={12}>
                  <Controller
                    name="observacoes"
                    control={control}
                    render={({ field }) => (
                      <TextField
                        {...field}
                        label="Observações"
                        fullWidth
                        multiline
                        rows={3}
                        placeholder="Observações adicionais..."
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
                {editingConsulta ? 'Atualizar' : 'Agendar'}
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

export default ConsultasPage; 