import React, { useState, useEffect } from 'react';
import {
  Box,
  Typography,
  Card,
  CardContent,
  Grid,
  Chip,
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Paper,
  List,
  ListItem,
  ListItemText,
  IconButton,
  Divider,
} from '@mui/material';
import {
  CalendarToday,
  AccessTime,
  Person,
  Add,
  Edit,
  Cancel,
  CheckCircle,
} from '@mui/icons-material';
import { useAuth } from '../../contexts/AuthContext';
import { AgendaItem } from '../../types';

export const Agenda: React.FC = () => {
  const { user } = useAuth();
  const [selectedDate, setSelectedDate] = useState(new Date().toISOString().split('T')[0]);
  const [agendaItems, setAgendaItems] = useState<AgendaItem[]>([]);
  const [openDialog, setOpenDialog] = useState(false);
  const [selectedItem, setSelectedItem] = useState<AgendaItem | null>(null);

  // Mock data para demonstração
  useEffect(() => {
    const mockAgenda: AgendaItem[] = [
      {
        id: 1,
        data: selectedDate,
        horario: '08:00',
        paciente: 'Maria Silva',
        tipo: 'consulta',
        status: 'agendado',
      },
      {
        id: 2,
        data: selectedDate,
        horario: '08:30',
        paciente: 'João Santos',
        tipo: 'consulta',
        status: 'em_andamento',
      },
      {
        id: 3,
        data: selectedDate,
        horario: '09:00',
        paciente: 'Ana Costa',
        tipo: 'consulta',
        status: 'agendado',
      },
      {
        id: 4,
        data: selectedDate,
        horario: '09:30',
        paciente: 'Pedro Oliveira',
        tipo: 'consulta',
        status: 'concluido',
      },
      {
        id: 5,
        data: selectedDate,
        horario: '10:00',
        paciente: 'Carlos Lima',
        tipo: 'emergencia',
        status: 'agendado',
      },
    ];
    setAgendaItems(mockAgenda);
  }, [selectedDate]);

  const getStatusColor = (status: string) => {
    switch (status) {
      case 'agendado':
        return 'info';
      case 'em_andamento':
        return 'warning';
      case 'concluido':
        return 'success';
      case 'cancelado':
        return 'error';
      default:
        return 'default';
    }
  };

  const getTipoColor = (tipo: string) => {
    switch (tipo) {
      case 'consulta':
        return '#1976d2';
      case 'exame':
        return '#388e3c';
      case 'emergencia':
        return '#d32f2f';
      default:
        return '#757575';
    }
  };

  const handleItemClick = (item: AgendaItem) => {
    setSelectedItem(item);
    setOpenDialog(true);
  };

  const handleStatusChange = (newStatus: string) => {
    if (selectedItem) {
      setAgendaItems(prev =>
        prev.map(item =>
          item.id === selectedItem.id
            ? { ...item, status: newStatus as any }
            : item
        )
      );
      setSelectedItem({ ...selectedItem, status: newStatus as any });
    }
  };

  const getHorariosSemana = () => {
    const horarios = [];
    for (let i = 8; i <= 17; i++) {
      horarios.push(`${i.toString().padStart(2, '0')}:00`);
      if (i < 17) {
        horarios.push(`${i.toString().padStart(2, '0')}:30`);
      }
    }
    return horarios;
  };

  const proximaConsulta = agendaItems
    .filter(item => item.status === 'agendado')
    .sort((a, b) => a.horario.localeCompare(b.horario))[0];

  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Agenda Médica
      </Typography>
      <Typography variant="subtitle1" color="textSecondary" gutterBottom>
        Dr(a). {user?.nome}
      </Typography>

      {/* Resumo do dia */}
      <Grid container spacing={3} sx={{ mb: 3 }}>
        <Grid item xs={12} md={4}>
          <Card>
            <CardContent>
              <Box display="flex" alignItems="center" gap={1}>
                <CalendarToday color="primary" />
                <Typography variant="h6">
                  {agendaItems.length} Compromissos
                </Typography>
              </Box>
              <Typography variant="body2" color="textSecondary">
                Agendados para hoje
              </Typography>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} md={4}>
          <Card>
            <CardContent>
              <Box display="flex" alignItems="center" gap={1}>
                <AccessTime color="warning" />
                <Typography variant="h6">
                  {proximaConsulta?.horario || 'Nenhuma'}
                </Typography>
              </Box>
              <Typography variant="body2" color="textSecondary">
                Próxima consulta
              </Typography>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} md={4}>
          <Card>
            <CardContent>
              <Box display="flex" alignItems="center" gap={1}>
                <Person color="success" />
                <Typography variant="h6">
                  {agendaItems.filter(item => item.status === 'concluido').length}
                </Typography>
              </Box>
              <Typography variant="body2" color="textSecondary">
                Pacientes atendidos
              </Typography>
            </CardContent>
          </Card>
        </Grid>
      </Grid>

      {/* Seletor de data */}
      <Paper sx={{ p: 2, mb: 3 }}>
        <Box display="flex" alignItems="center" gap={2}>
          <TextField
            type="date"
            label="Data"
            value={selectedDate}
            onChange={(e) => setSelectedDate(e.target.value)}
            sx={{ minWidth: 200 }}
          />
          <Button variant="contained" startIcon={<Add />}>
            Nova Consulta
          </Button>
        </Box>
      </Paper>

      {/* Lista de compromissos */}
      <Paper>
        <List>
          {agendaItems.map((item, index) => (
            <React.Fragment key={item.id}>
              <ListItem
                button
                onClick={() => handleItemClick(item)}
                sx={{
                  borderLeft: `4px solid ${getTipoColor(item.tipo)}`,
                  '&:hover': { backgroundColor: 'rgba(0, 0, 0, 0.04)' },
                }}
              >
                <ListItemText
                  primary={
                    <Box display="flex" alignItems="center" gap={2}>
                      <Typography variant="h6" color="primary">
                        {item.horario}
                      </Typography>
                      <Typography variant="subtitle1">
                        {item.paciente}
                      </Typography>
                      <Chip
                        label={item.tipo.toUpperCase()}
                        size="small"
                        style={{ backgroundColor: getTipoColor(item.tipo), color: 'white' }}
                      />
                      <Chip
                        label={item.status.replace('_', ' ').toUpperCase()}
                        size="small"
                        color={getStatusColor(item.status) as any}
                      />
                    </Box>
                  }
                  secondary={`Tipo: ${item.tipo} • Status: ${item.status}`}
                />
                <Box display="flex" gap={1}>
                  {item.status === 'agendado' && (
                    <IconButton
                      color="primary"
                      onClick={(e) => {
                        e.stopPropagation();
                        handleStatusChange('em_andamento');
                      }}
                    >
                      <Edit />
                    </IconButton>
                  )}
                  {item.status === 'em_andamento' && (
                    <IconButton
                      color="success"
                      onClick={(e) => {
                        e.stopPropagation();
                        handleStatusChange('concluido');
                      }}
                    >
                      <CheckCircle />
                    </IconButton>
                  )}
                  <IconButton
                    color="error"
                    onClick={(e) => {
                      e.stopPropagation();
                      handleStatusChange('cancelado');
                    }}
                  >
                    <Cancel />
                  </IconButton>
                </Box>
              </ListItem>
              {index < agendaItems.length - 1 && <Divider />}
            </React.Fragment>
          ))}
        </List>
      </Paper>

      {/* Dialog de detalhes */}
      <Dialog open={openDialog} onClose={() => setOpenDialog(false)} maxWidth="md" fullWidth>
        <DialogTitle>
          Detalhes da Consulta - {selectedItem?.horario}
        </DialogTitle>
        <DialogContent>
          {selectedItem && (
            <Box sx={{ pt: 2 }}>
              <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Paciente"
                    value={selectedItem.paciente}
                    disabled
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Horário"
                    value={selectedItem.horario}
                    disabled
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <FormControl fullWidth>
                    <InputLabel>Status</InputLabel>
                    <Select
                      value={selectedItem.status}
                      onChange={(e) => handleStatusChange(e.target.value)}
                    >
                      <MenuItem value="agendado">Agendado</MenuItem>
                      <MenuItem value="em_andamento">Em Andamento</MenuItem>
                      <MenuItem value="concluido">Concluído</MenuItem>
                      <MenuItem value="cancelado">Cancelado</MenuItem>
                    </Select>
                  </FormControl>
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Tipo"
                    value={selectedItem.tipo}
                    disabled
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    fullWidth
                    multiline
                    rows={4}
                    label="Observações"
                    placeholder="Adicione observações sobre a consulta..."
                  />
                </Grid>
              </Grid>
            </Box>
          )}
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setOpenDialog(false)}>Fechar</Button>
          <Button variant="contained">Salvar</Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
}; 