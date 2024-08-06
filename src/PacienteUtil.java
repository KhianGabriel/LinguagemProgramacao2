import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;
import java.util.Scanner;

public class PacienteUtil {

    final static String caminhoArquivo = "fila-de-pacientes.txt";
    static Queue<Paciente> filaDePacientes;
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {

        int op;
        filaDePacientes = new ArrayDeque<>();

        lerArquivo();

        do {
            menu();

            System.out.print("Digite a opção: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scanner.next();
                menu();
                System.out.println("Digite a opção: ");;
            }
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    try {
                        cadastrarPaciente();
                    } catch (ParseException e) {
                        System.out.println("Erro na formatação da data.");
                    }
                    
                    break;
                case 2:
                    Paciente paciente = chamarPaciente();
                    if (paciente != null) {
                        System.out.println(paciente);
                    } else {
                        System.out.println("Nenhum paciente na fila.");
                    }
                    
                    break;
                case 3:
                    Paciente proximoPaciente = consultarDadosPaciente();
                    if (proximoPaciente != null) {
                        System.out.println(proximoPaciente);
                    } else {
                        System.out.println("Nenhum paciente na fila.");
                    }
                    break;
                case 4:
                    qtdPacientesEmEspera();
                    break;
                case 5:
                    qtdPacientesEmEsperaPorPercentual();
                    break;
                case 6:
                    qtdPacientesTempoMedioEspera();
                    break;
                case 0:
                    encerrarAplicacao();
                    break;
                default:
                    System.out.println("Opção inválida, digite a opção correta.");
            }

        } while (op != 0);

        escreverArquivo();

        scanner.close();
    }

    private static void cadastrarPaciente() throws ParseException {

        System.out.println("Digite o nome do paciente : ");
        String nome = scanner.nextLine();
        boolean valorValido = false;

        String cpf;
        do {
            System.out.println("Digite o cpf do paciente : ");
            cpf = scanner.nextLine();

            if (validaCPF(cpf) == true) {
                System.out.println("Cpf já existente, digite novamente");
                System.out.println();
            } else {
                break;
            }
        } while (!valorValido);

        char sexo;
        while (true) {
            System.out.println("Digite o sexo do paciente ('M' ou 'F'):");
            sexo = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();
            if (sexo == 'M' || sexo == 'F') {
                break;
            }
            System.out.println("Sexo inválido. Tente novamente.");
        }

        int dia, mes, ano;
        while (true) {
            System.out.println("Digite o dia de nascimento do paciente : ");
            dia = scanner.nextInt();
            System.out.println("Digite o mês de nascimento do paciente : ");
            mes = scanner.nextInt();
            System.out.println("Digite o ano de nascimento do paciente : ");
            ano = scanner.nextInt();
            scanner.nextLine();

            if (dia > 0 && dia <= 31 && mes > 0 && mes <= 12 && ano > 1900) {
                break;
            }

            System.out.println("Data inválida. Tente novamente.");
        }
       
        String orgao;
        do {
            System.out.println("Qual orgão vai ser transplantado ? Coracao - Rim - Pulmao - Figado ");
            orgao = scanner.nextLine();

            if (validaOrgao(orgao)) {
                break;
            } else {
                System.out.println("Órgão inválido. Tente novamente.");
            }

        } while (!valorValido);

        String data = formataData(dia, mes, ano);

        Paciente paciente = new Paciente(nome, cpf, sexo, data, orgao);

        filaDePacientes.add(paciente);

    }

    private static Paciente chamarPaciente() {
        return filaDePacientes.poll();
    }

    private static Paciente consultarDadosPaciente() {
        return filaDePacientes.peek();
    }

    private static void qtdPacientesEmEspera() {
        if (filaDePacientes.isEmpty()) {
            System.out.println("Nenhum paciente na fila.");
            return;
        }

        int contCoracao = 0;
        int contRim = 0;
        int contPulmao = 0;
        int contFigado = 0;

        for (Paciente paciente : filaDePacientes) {

            switch (paciente.getOrgao()) {
                case "Coracao":
                    contCoracao++;
                    break;
                case "Rim":
                    contRim++;
                    break;
                case "Pulmao":
                    contPulmao++;
                    break;
                case "Figado":
                    contFigado++;
                    break;
            }
            System.out.println("Quantidade de pacientes por tipo de transplante : ");
            System.out.println("Coraçao : " + contCoracao);
            System.out.println("Rim : " + contRim);
            System.out.println("Pulmao : " + contPulmao);
            System.out.println("Figado : " + contFigado);
        }
    }

    private static void qtdPacientesEmEsperaPorPercentual() {
        if (filaDePacientes.isEmpty()) {
            System.out.println("Nenhum paciente na fila.");
            return;
        }
        int contCoracao = 0;
        int contRim = 0;
        int contPulmao = 0;
        int contFigado = 0;

        for (Paciente paciente : filaDePacientes) {

            switch (paciente.getOrgao()) {
                case "Coracao":
                    contCoracao++;
                    break;
                case "Rim":
                    contRim++;
                    break;
                case "Pulmao":
                    contPulmao++;
                    break;
                case "Figado":
                    contFigado++;
                    break;
            }

            int total = contCoracao + contFigado + contPulmao + contRim;
            double mediaCoracao = (contCoracao * 100) / total;
            double mediaFigado = (contFigado * 100) / total;
            double mediaPulmao = (contPulmao * 100) / total;
            double mediaRim = (contRim * 100) / total;

            System.out.println("Quantidade de pacientes por percentual de tipo de transplantes : ");
            System.out.println("Coraçao : " + mediaCoracao + " %");
            System.out.println("Rim : " + mediaFigado + " %");
            System.out.println("Pulmao : " + mediaPulmao + " %");
            System.out.println("Figado : " + mediaRim + " %");
        }
    }

    private static void qtdPacientesTempoMedioEspera() {
        if (filaDePacientes.isEmpty()) {
            System.out.println("Nenhum paciente na fila.");
            return;
        }
    
        long totalDias = 0;
        long totalHoras = 0;
        long totalMinutos = 0;
        int numPacientes = filaDePacientes.size();
    
        for (Paciente paciente : filaDePacientes) {
            Duration duracao = Duration.between(paciente.getHorarioDeEnfileiramento(), LocalDateTime.now());
            totalDias += duracao.toDays();
            totalHoras += duracao.toHours() % 24;
            totalMinutos += duracao.toMinutes() % 60;
        }
    
        totalHoras += totalMinutos / 60;
        totalMinutos = totalMinutos % 60;
        totalDias += totalHoras / 24;
        totalHoras = totalHoras % 24;
    
        long mediaDias = totalDias / numPacientes;
        long mediaHoras = totalHoras / numPacientes;
        long mediaMinutos = totalMinutos / numPacientes;
    
        System.out.println("Tempo médio de permanência na fila: " + mediaDias + " dias, " + mediaHoras + " horas, " + mediaMinutos + " minutos.");
    }

    public static void menu() {

        System.out.println("**************************** FILA DE TRANSPLANTES **************************");
        System.out.println("* 1 - CADASTRAR PACIENTE                                                   *");
        System.out.println("* 2 - CHAMAR PROXIMO PACIENTE                                              *");
        System.out.println("* 3 - CONSULTA DE DADOS DO PROXIMO PACIENTE                                *");
        System.out.println("* 4 - QUANTIDADE DE PACIENTES EM ESPERA POR TIPO DE TRANSPLANTE            *");
        System.out.println("* 5 - PERCENTUAL POR TIPO DE TRANSPLANTE DE PACIENTES EM ESPERA            *");
        System.out.println("* 6 - TEMPO MEDIO DE PERMANÊNCIA EM FILA DE ESPERA POR TIDO DE TRANSPLANTE *");
        System.out.println("* 0 - SAIR                                                                 *");
        System.out.println("****************************************************************************");

    }

    private static boolean validaCPF(String cpf) {
        boolean aux = false;
        for (Paciente paciente : filaDePacientes) {
            if (paciente.getCpf().equals(cpf)) {
                aux = true;
                break;
            } else {
                break;
            }
        }
        return aux;
    }

    private static boolean validaOrgao(String orgao) {
        boolean aux = false;
        if (orgao.equalsIgnoreCase("Coracao") || orgao.equalsIgnoreCase("Rim") || orgao.equalsIgnoreCase("Pulmao")
                || orgao.equalsIgnoreCase("Figado")) {
            aux = true;
        }
        return aux;
    }

    public static String formataData(int dia, int mes, int ano) throws ParseException {

        String dataFormatacao = dia + "/" + mes + "/" + ano;

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        Date data = formato.parse(dataFormatacao);

        String dataFormatada = formato.format(data);

        return dataFormatada;
    }

    public static void escreverArquivo() {
        FileOutputStream fluxoEscrita;
        ObjectOutputStream fluxoEscritaObjetos;
        try {
            fluxoEscrita = new FileOutputStream(caminhoArquivo);
            fluxoEscritaObjetos = new ObjectOutputStream(fluxoEscrita);

            fluxoEscritaObjetos.writeObject(filaDePacientes);

            fluxoEscritaObjetos.close();
            fluxoEscrita.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void lerArquivo() {
        FileInputStream fluxoLeitura = null;
        ObjectInputStream fluxoLeituraObjetos = null;
        try {
            fluxoLeitura = new FileInputStream(caminhoArquivo);
            fluxoLeituraObjetos = new ObjectInputStream(fluxoLeitura);

            filaDePacientes = (Queue<Paciente>) fluxoLeituraObjetos.readObject();

            if (fluxoLeituraObjetos != null) {
                fluxoLeituraObjetos.close();
            }
            if (fluxoLeitura != null) {
                fluxoLeitura.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void encerrarAplicacao() {
        System.out.println("Encerrando aplicação");
        escreverArquivo();
        System.exit(0);
    }

}