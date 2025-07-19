import React from 'react';
import {
  Grid,
  Card,
  CardContent,
  Typography,
  Box,
  Paper,
} from '@mui/material';
import {
  People,
  CalendarToday,
  LocalHospital,
  Assignment,
  TrendingUp,
} from '@mui/icons-material';
import { useAuth } from '../contexts/AuthContext';

interface StatCardProps {
  title: string;
  value: string | number;
  icon: React.ReactNode;
  color: string;
}

const StatCard: React.FC<StatCardProps> = ({ title, value, icon, color }) => (
  <Card>
    <CardContent>
      <Box display="flex" alignItems="center" justifyContent="space-between">
        <Box>
          <Typography color="textSecondary" gutterBottom variant="body2">
            {title}
          </Typography>
          <Typography variant="h4" component="div">
            {value}
          </Typography>
        </Box>
        <Box
          sx={{
            backgroundColor: color,
            borderRadius: '50%',
            p: 1,
            color: 'white',
          }}
        >
          {icon}
        </Box>
      </Box>
    </CardContent>
  </Card>
);

export const Dashboard: React.FC = () => {
  const { user } = useAuth();

  const renderAdminDashboard = () => (
    <Grid container spacing={3}>
      <Grid item xs={12} sm={6} md={3}>
        <StatCard
          title="Total de Pacientes"
          value="1,234"
          icon={<People />}
          color="#1976d2"
        />
      </Grid>
      <Grid item xs={12} sm={6} md={3}>
        <StatCard
          title="Consultas Hoje"
          value="48"
          icon={<CalendarToday />}
          color="#388e3c"
        />
      </Grid>
      <Grid item xs={12} sm={6} md={3}>
        <StatCard
          title="Funcionários Ativos"
          value="25"
          icon={<People />}
          color="#f57c00"
        />
      </Grid>
      <Grid item xs={12} sm={6} md={3}>
        <StatCard
          title="Emergências"
          value="3"
          icon={<LocalHospital />}
          color="#d32f2f"
        />
      </Grid>
      <Grid item xs={12}>
        <Paper sx={{ p: 2 }}>
          <Typography variant="h6" gutterBottom>
            Relatório Mensal
          </Typography>
          <Typography variant="body1">
            Resumo das atividades do mês atual com gráficos e estatísticas detalhadas.
          </Typography>
        </Paper>
      </Grid>
    </Grid>
  );

  const renderMedicoDashboard = () => (
    <Grid container spacing={3}>
      <Grid item xs={12} sm={6} md={4}>
        <StatCard
          title="Consultas Hoje"
          value="12"
          icon={<CalendarToday />}
          color="#1976d2"
        />
      </Grid>
      <Grid item xs={12} sm={6} md={4}>
        <StatCard
          title="Próxima Consulta"
          value="14:30"
          icon={<CalendarToday />}
          color="#388e3c"
        />
      </Grid>
      <Grid item xs={12} sm={6} md={4}>
        <StatCard
          title="Pacientes Atendidos"
          value="8"
          icon={<People />}
          color="#f57c00"
        />
      </Grid>
      <Grid item xs={12}>
        <Paper sx={{ p: 2 }}>
          <Typography variant="h6" gutterBottom>
            Agenda do Dia
          </Typography>
          <Typography variant="body1">
            Consultas e procedimentos agendados para hoje.
          </Typography>
        </Paper>
      </Grid>
    </Grid>
  );

  const renderEnfermeiroDashboard = () => (
    <Grid container spacing={3}>
      <Grid item xs={12} sm={6} md={4}>
        <StatCard
          title="Triagens Pendentes"
          value="5"
          icon={<Assignment />}
          color="#1976d2"
        />
      </Grid>
      <Grid item xs={12} sm={6} md={4}>
        <StatCard
          title="Exames Hoje"
          value="15"
          icon={<LocalHospital />}
          color="#388e3c"
        />
      </Grid>
      <Grid item xs={12} sm={6} md={4}>
        <StatCard
          title="Emergências"
          value="2"
          icon={<LocalHospital />}
          color="#d32f2f"
        />
      </Grid>
      <Grid item xs={12}>
        <Paper sx={{ p: 2 }}>
          <Typography variant="h6" gutterBottom>
            Atividades do Turno
          </Typography>
          <Typography variant="body1">
            Lista de procedimentos e triagens para o turno atual.
          </Typography>
        </Paper>
      </Grid>
    </Grid>
  );

  const renderAtendenteDashboard = () => (
    <Grid container spacing={3}>
      <Grid item xs={12} sm={6} md={4}>
        <StatCard
          title="Agendamentos Hoje"
          value="25"
          icon={<CalendarToday />}
          color="#1976d2"
        />
      </Grid>
      <Grid item xs={12} sm={6} md={4}>
        <StatCard
          title="Pacientes Cadastrados"
          value="1,234"
          icon={<People />}
          color="#388e3c"
        />
      </Grid>
      <Grid item xs={12} sm={6} md={4}>
        <StatCard
          title="Entradas Registradas"
          value="18"
          icon={<Assignment />}
          color="#f57c00"
        />
      </Grid>
      <Grid item xs={12}>
        <Paper sx={{ p: 2 }}>
          <Typography variant="h6" gutterBottom>
            Atividades do Dia
          </Typography>
          <Typography variant="body1">
            Resumo dos agendamentos e cadastros realizados.
          </Typography>
        </Paper>
      </Grid>
    </Grid>
  );

  const renderDashboardContent = () => {
    switch (user?.tipo) {
      case 'administrador':
        return renderAdminDashboard();
      case 'medico':
        return renderMedicoDashboard();
      case 'enfermeiro':
        return renderEnfermeiroDashboard();
      case 'atendente':
        return renderAtendenteDashboard();
      default:
        return (
          <Typography variant="h6">
            Bem-vindo ao sistema de gestão hospitalar!
          </Typography>
        );
    }
  };

  return (
    <Box>
        <Typography variant="h4" gutterBottom>
            Dashboard - {user?.tipo ? user.tipo.charAt(0).toUpperCase() + user.tipo.slice(1) : 'Usuário'}
        </Typography>

        <Typography variant="subtitle1" color="textSecondary" gutterBottom>
            Bem-vindo, {user?.nome || 'Visitante'}
        </Typography>
      <Box sx={{ mt: 3 }}>
        {renderDashboardContent()}
      </Box>
    </Box>
  );
}; 