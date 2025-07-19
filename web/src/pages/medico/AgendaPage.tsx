import React, { useState, useEffect } from 'react';
import {
  Box,
  Typography,
  Card,
  CardContent,
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
  Button,
  Snackbar
} from '@mui/material';
import {
  Edit as EditIcon,
  EventNote as EventIcon,
  Schedule as ScheduleIcon,
  Person as PersonIcon,
  CheckCircle as CheckIcon,
  Cancel as CancelIcon,
  PlayArrow as StartIcon
} from '@mui/icons-material';
import { useForm, Controller } from 'react-hook-form';
import { LocalizationProvider, DateTimePicker } from '@mui/x-date-pickers';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFnsV3';
import { format } from 'date-fns';
import { ptBR } from 'date-fns/locale';
import { apiService } from '../../services/api';
import { useAuth } from '../../contexts/AuthContext';
import { ConsultaResponse, ConsultaUpdate, StatusConsulta } from '../../types';

interface ConsultaFormData extends Omit<ConsultaUpdate, 'data_hora'> {
  data_hora?: Date;
}

const AgendaPage: React.FC = () => {
  const [consultas, setConsultas] = useState<ConsultaResponse[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [openDialog, setOpenDialog] = useState(false);
  const [editingConsulta, setEditingConsulta] = useState<ConsultaResponse | null>(null);
  const [snackbar, setSnackbar] = useState({ open: false, message: '', severity: 'success' as 'success' | 'error' });

  const { user } = useAuth();
  const { control, handleSubmit, reset, formState: { errors } } = useForm<ConsultaFormData>();

  // Carregar consultas do médico para hoje
  const loadConsultasHoje = async () => {
    try {
      setLoading(true);
      setError(null);
      
      const response = await apiService.getConsultasMedicoHoje();
      setConsultas(response);
    } catch (err: any) {
      console.error('Erro ao carregar consultas:', err);
      setError(err.message || 'Erro ao carregar consultas');
      setSnackbar({ 
        open: true, 
        message: 'Erro ao carregar consultas do dia', 
        severity: 'error' 
      });
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadConsultasHoje();
  }, []);

  // Atualizar consulta
  const handleUpdate = async (data: ConsultaFormData) => {
    if (!editingConsulta) return;

    try {
      const updateData: ConsultaUpdate = {
        data_hora: data.data_hora ? data.data_hora.toISOString() : undefined,
        status: data.status !== editingConsulta.status ? data.status : undefined,
        motivo: data.motivo !== editingConsulta.motivo ? data.motivo : undefined,
        observacoes: data.observacoes !== editingConsulta.observacoes ? data.observacoes : undefined,
        diagnostico: data.diagnostico !== editingConsulta.diagnostico ? data.diagnostico : undefined,
        prescricao: data.prescricao !== editingConsulta.prescricao ? data.prescricao : undefined
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
      loadConsultasHoje();
    } catch (err: any) {
      console.error('Erro ao atualizar consulta:', err);
      setSnackbar({ 
        open: true, 
        message: err.message || 'Erro ao atualizar consulta', 
        severity: 'error' 
      });
    }
  };

  // Ações rápidas de status
  const handleQuickStatusChange = async (consulta: ConsultaResponse, novoStatus: StatusConsulta) => {
    try {
      await apiService.updateConsulta(consulta.id, { status: novoStatus });
      
      setSnackbar({ 
        open: true, 
        message: `Consulta marcada como ${novoStatus}`, 
        severity: 'success' 
      });
      
      loadConsultasHoje();
    } catch (err: any) {
      console.error('Erro ao atualizar status:', err);
      setSnackbar({ 
        open: true, 
        message: 'Erro ao atualizar status da consulta', 
        severity: 'error' 
      });
    }
  };

  const openEditDialog = (consulta: ConsultaResponse) => {
    setEditingConsulta(consulta);
    reset({
      data_hora: new Date(consulta.data_hora),
      status: consulta.status,
      motivo: consulta.motivo || '',
      observacoes: consulta.observacoes || '',
      diagnostico: consulta.diagnostico || '',
      prescricao: consulta.prescricao || ''
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

  if (loading) {
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
          <ScheduleIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
          Minha Agenda - {format(new Date(), 'dd/MM/yyyy', { locale: ptBR })}
        </Typography>

        <Card sx={{ mb: 3 }}>
          <CardContent>
            <Grid container spacing={2}>
              <Grid item xs={12} sm={3}>
                <Box textAlign="center">
                  <Typography variant="h3" color="primary">
                    {consultas.length}
                  </Typography>
                  <Typography variant="body2" color="text.secondary">
                    Total de Consultas
                  </Typography>
                </Box>
              </Grid>
              <Grid item xs={12} sm={3}>
                <Box textAlign="center">
                  <Typography variant="h3" color="warning.main">
                    {consultas.filter(c => c.status === 'agendada' || c.status === 'confirmada').length}
                  </Typography>
                  <Typography variant="body2" color="text.secondary">
                    Pendentes
                  </Typography>
                </Box>
              </Grid>
              <Grid item xs={12} sm={3}>
                <Box textAlign="center">
                  <Typography variant="h3" color="success.main">
                    {consultas.filter(c => c.status === 'finalizada').length}
                  </Typography>
                  <Typography variant="body2" color="text.secondary">
                    Finalizadas
                  </Typography>
                </Box>
              </Grid>
              <Grid item xs={12} sm={3}>
                <Box textAlign="center">
                  <Typography variant="h3" color="error.main">
                    {consultas.filter(c => c.status === 'cancelada').length}
                  </Typography>
                  <Typography variant="body2" color="text.secondary">
                    Canceladas
                  </Typography>
                </Box>
              </Grid>
            </Grid>
          </CardContent>
        </Card>

        {error && (
          <Alert severity="error" sx={{ mb: 2 }}>
            {error}
          </Alert>
        )}

        {consultas.length === 0 ? (
          <Card>
            <CardContent sx={{ textAlign: 'center', py: 4 }}>
              <EventIcon sx={{ fontSize: 64, color: 'text.secondary', mb: 2 }} />
              <Typography variant="h6" color="text.secondary">
                Nenhuma consulta agendada para hoje
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Você pode descansar ou verificar sua agenda para outros dias.
              </Typography>
            </CardContent>
          </Card>
        ) : (
          <TableContainer component={Paper}>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Horário</TableCell>
                  <TableCell>Paciente</TableCell>
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
                      <Typography variant="body1" fontWeight="medium">
                        {format(new Date(consulta.data_hora), 'HH:mm')}
                      </Typography>
                      <Typography variant="caption" color="text.secondary">
                        {format(new Date(consulta.data_hora), 'dd/MM/yyyy')}
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Box sx={{ display: 'flex', alignItems: 'center' }}>
                        <PersonIcon sx={{ mr: 1, color: 'text.secondary' }} />
                        <Box>
                          <Typography variant="body1">
                            {consulta.paciente_nome || `Paciente #${consulta.paciente_id}`}
                          </Typography>
                        </Box>
                      </Box>
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
                      <Box sx={{ display: 'flex', gap: 0.5 }}>
                        {/* Ações rápidas de status */}
                        {consulta.status === 'agendada' && (
                          <IconButton 
                            size="small"
                            color="info"
                            title="Marcar como confirmada"
                            onClick={() => handleQuickStatusChange(consulta, StatusConsulta.CONFIRMADA)}
                          >
                            <CheckIcon />
                          </IconButton>
                        )}
                        
                        {(consulta.status === 'confirmada' || consulta.status === 'agendada') && (
                          <IconButton 
                            size="small"
                            color="warning"
                            title="Iniciar consulta"
                            onClick={() => handleQuickStatusChange(consulta, StatusConsulta.EM_ANDAMENTO)}
                          >
                            <StartIcon />
                          </IconButton>
                        )}
                        
                        {consulta.status === 'em_andamento' && (
                          <IconButton 
                            size="small"
                            color="success"
                            title="Finalizar consulta"
                            onClick={() => handleQuickStatusChange(consulta, StatusConsulta.FINALIZADA)}
                          >
                            <CheckIcon />
                          </IconButton>
                        )}
                        
                        {consulta.status !== 'cancelada' && consulta.status !== 'finalizada' && (
                          <IconButton 
                            size="small"
                            color="error"
                            title="Cancelar consulta"
                            onClick={() => handleQuickStatusChange(consulta, StatusConsulta.CANCELADA)}
                          >
                            <CancelIcon />
                          </IconButton>
                        )}
                        
                        {/* Editar consulta */}
                        <IconButton 
                          size="small"
                          color="primary"
                          title="Editar consulta"
                          onClick={() => openEditDialog(consulta)}
                        >
                          <EditIcon />
                        </IconButton>
                      </Box>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        )}

        {/* Dialog para editar consulta */}
        <Dialog open={openDialog} onClose={() => setOpenDialog(false)} maxWidth="md" fullWidth>
          <DialogTitle>
            Editar Consulta - {editingConsulta?.paciente_nome}
          </DialogTitle>
          <form onSubmit={handleSubmit(handleUpdate)}>
            <DialogContent>
              <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                  <Controller
                    name="data_hora"
                    control={control}
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
                    name="status"
                    control={control}
                    render={({ field }) => (
                      <FormControl fullWidth>
                        <InputLabel>Status</InputLabel>
                        <Select {...field} label="Status">
                          <MenuItem value="agendada">Agendada</MenuItem>
                          <MenuItem value="confirmada">Confirmada</MenuItem>
                          <MenuItem value="em_andamento">Em Andamento</MenuItem>
                          <MenuItem value="finalizada">Finalizada</MenuItem>
                          <MenuItem value="cancelada">Cancelada</MenuItem>
                        </Select>
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
                      />
                    )}
                  />
                </Grid>
                <Grid item xs={12}>
                  <Controller
                    name="diagnostico"
                    control={control}
                    render={({ field }) => (
                      <TextField
                        {...field}
                        label="Diagnóstico"
                        fullWidth
                        multiline
                        rows={3}
                        placeholder="Diagnóstico médico..."
                      />
                    )}
                  />
                </Grid>
                <Grid item xs={12}>
                  <Controller
                    name="prescricao"
                    control={control}
                    render={({ field }) => (
                      <TextField
                        {...field}
                        label="Prescrição"
                        fullWidth
                        multiline
                        rows={4}
                        placeholder="Medicamentos e tratamentos prescritos..."
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
                Salvar Alterações
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

export default AgendaPage; 