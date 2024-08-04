import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class Paciente implements Serializable {

    private String nome;
    private String cpf;
    private char sexo;
    private String dataDeNascimento;
    private String orgão;
    private LocalDateTime horarioDeEnfileiramento;
    private LocalDateTime horarioDesenfileiramento;
    private static final long serialVersionUID = 5775164107894522754l;

    public Paciente(String nome, String cpf, char sexo, String dataDeNascimento, String orgão) {
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataDeNascimento = dataDeNascimento;
        this.orgão = orgão;
        horarioDeEnfileiramento = LocalDateTime.now();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getOrgão() {
        return orgão;
    }

    public void setOrgão(String orgão) {
        this.orgão = orgão;
    }

    public String tempoDePermanencia(LocalDateTime horarioDesenfileiramento) {

        horarioDesenfileiramento = LocalDateTime.now();
        
        Duration duracao = Duration.between(horarioDeEnfileiramento, horarioDesenfileiramento);

        long dias = duracao.toDays();
        long horas = duracao.toHours() % 24;
        long minutos = duracao.toMinutes() % 60;

        return String.format(" %d dias, %d horas, %d minutos", dias, horas, minutos);
    }


    @Override
    public String toString() {
        return "Paciente [nome= " + nome + ", cpf= " + cpf + ", sexo= " + sexo + ", dataDeNascimento= " + dataDeNascimento
                + ", orgão= " + orgão + ", Tempo de permanencia na fila = " + tempoDePermanencia(horarioDesenfileiramento) + "]";
    }

}
