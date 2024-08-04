import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class Paciente implements Serializable {

    private String nome;
    private String cpf;
    private char sexo;
    private String dataDeNascimento;
    private String orgao;
    private LocalDateTime horarioDeEnfileiramento;
    private LocalDateTime horarioDesenfileiramento;
    private static final long serialVersionUID = 5775164107894522754l;

    protected Paciente(String nome, String cpf, char sexo, String dataDeNascimento, String orgao) {
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataDeNascimento = dataDeNascimento;
        this.orgao = orgao;
        horarioDeEnfileiramento = LocalDateTime.now();
    }

    protected String getNome() {
        return nome;
    }

    protected void setNome(String nome) {
        this.nome = nome;
    }

    protected String getCpf() {
        return cpf;
    }

    protected void setCpf(String cpf) {
        this.cpf = cpf;
    }

    protected char getSexo() {
        return sexo;
    }

    protected void setSexo(char sexo) {
        this.sexo = sexo;
    }

    protected String getDataDeNascimento() {
        return dataDeNascimento;
    }

    protected void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    protected String getOrgao() {
        return orgao;
    }

    protected void setOrgao(String orgao) {
        this.orgao = orgao;
    }

    protected String tempoDePermanencia(LocalDateTime horarioDesenfileiramento) {

        horarioDesenfileiramento = LocalDateTime.now();
        
        Duration duracao = Duration.between(horarioDeEnfileiramento, horarioDesenfileiramento);

        long dias = duracao.toDays();
        long horas = duracao.toHours() % 24;
        long minutos = duracao.toMinutes() % 60;

        return String.format(" %d dias, %d horas, %d minutos", dias, horas, minutos);
    }
    
    protected LocalDateTime getHorarioDeEnfileiramento() {
        return horarioDeEnfileiramento;
    }

    // protected void setHorarioDeEnfileiramento(LocalDateTime horarioDeEnfileiramento) {
    //     this.horarioDeEnfileiramento = horarioDeEnfileiramento;
    // }

    // protected void setHorarioDesenfileiramento(LocalDateTime horarioDesenfileiramento) {
    //     this.horarioDesenfileiramento = horarioDesenfileiramento;
    // }

    @Override
    public String toString() {
        
        return String.format("Paciente:%n" +
                             "Nome: %s%n" +
                             "CPF: %s%n" +
                             "Sexo: %c%n" +
                             "Data de Nascimento: %s%n" +
                             "Órgão: %s%n" +
                             "Data e Hora de Inclusão: %s%n",
                             nome, cpf, sexo, dataDeNascimento, orgao, tempoDePermanencia(horarioDesenfileiramento));
    }

}
