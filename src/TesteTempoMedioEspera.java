import java.time.LocalDateTime;
import java.util.ArrayDeque;

public class TesteTempoMedioEspera {

    public static void main(String[] args) {
        PacienteUtil.filaDePacientes = new ArrayDeque<>();

        Paciente paciente1 = new Paciente("Paciente 1", "12345678901", 'M', "01/01/1980", "Coracao");
        Paciente paciente2 = new Paciente("Paciente 2", "23456789012", 'F', "02/02/1990", "Rim");
        Paciente paciente3 = new Paciente("Paciente 3", "34567890123", 'M', "03/03/2000", "Pulmao");

        // Para funcionar descomente os setters na classe Paciente
        paciente1.setHorarioDeEnfileiramento(LocalDateTime.now().minusDays(2).minusHours(3).minusMinutes(30));
        paciente1.setHorarioDesenfileiramento(LocalDateTime.now());

        paciente2.setHorarioDeEnfileiramento(LocalDateTime.now().minusDays(1).minusHours(5).minusMinutes(20));
        paciente2.setHorarioDesenfileiramento(LocalDateTime.now());

        paciente3.setHorarioDeEnfileiramento(LocalDateTime.now().minusDays(3).minusHours(1).minusMinutes(15));
        paciente3.setHorarioDesenfileiramento(LocalDateTime.now());

        PacienteUtil.filaDePacientes.add(paciente1);
        PacienteUtil.filaDePacientes.add(paciente2);
        PacienteUtil.filaDePacientes.add(paciente3);

        // Para testar torne a classe publica em PacienteUtil
        PacienteUtil.qtdPacientesTempoMedioEspera();
    }
}
