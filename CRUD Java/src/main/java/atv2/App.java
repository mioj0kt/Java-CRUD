package atv2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void menu() {
        System.out.println("\nMenu:");
        System.out.println("1. Listar");
        System.out.println("2. Inserir");
        System.out.println("3. Atualizar");
        System.out.println("4. Excluir");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CRUD", "postgres", "1201")) {
            UserDAO dao = new UserDAO(connection);
            Scanner scanner = new Scanner(System.in);
            int opcao;

            System.out.println("<-------- Bem Vindo -------->");

            do {
                menu();
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1: // Listar todos
                        List<User> lista = dao.listar();
                        if (lista.isEmpty()) {
                            System.out.println("Nenhum usuário encontrado.");
                        } else {
                            System.out.println("\nLista de Usuários:");
                            for (User user : lista) {
                                System.out.printf("ID: %d\nNome: %s\nCidade: %s\nTelefone: %s\n", 
                                    user.getId(), user.getNome(), user.getCidade(), user.getTelefone());
                                System.out.println("------------------------------");
                            }
                        }
                        break;

                    case 2: // Adicionar novo
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Cidade: ");
                        String cidade = scanner.nextLine();
                        System.out.print("Telefone: ");
                        String telefone = scanner.nextLine();
                        dao.inserir(new User(0, nome, cidade, telefone));
                        System.out.println("Inserido com sucesso!");
                        break;

                    case 3: // Atualizar existente
                        System.out.print("ID para atualizar: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Limpa o buffer
                        System.out.print("Novo Nome: ");
                        nome = scanner.nextLine();
                        System.out.print("Nova Cidade: ");
                        cidade = scanner.nextLine();
                        System.out.print("Novo Telefone: ");
                        telefone = scanner.nextLine();
                        dao.atualizar(new User(id, nome, cidade, telefone));
                        System.out.println("Atualizado com sucesso!");
                        break;

                    case 4: // Excluir
                        System.out.print("ID para excluir: ");
                        id = scanner.nextInt();
                        scanner.nextLine();
                        dao.excluir(id);
                        System.out.println("Excluído com sucesso!");
                        break;

                    case 5: // Sair do programa
                        System.out.println("Saindo com êxito...");
                        break;

                    default:
                        System.out.println("Opção inválida, tente novamente!");
                        break;
                }
            } while (opcao != 5);

            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
