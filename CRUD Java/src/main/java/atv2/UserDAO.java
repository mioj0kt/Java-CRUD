package atv2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserir(User user) throws SQLException {
        String sql = "INSERT INTO pessoa (nome, cidade, telefone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getCidade());
            stmt.setString(3, user.getTelefone());
            stmt.executeUpdate();
        }
    }

    public List<User> listar() throws SQLException {
        String sql = "SELECT * FROM pessoa";
        List<User> lista = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cidade"),
                    rs.getString("telefone")
                );
                lista.add(user);
            }
        }
        return lista;
    }

    public void atualizar(User user) throws SQLException {
        String sql = "UPDATE pessoa SET nome = ?, cidade = ?, telefone = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getCidade());
            stmt.setString(3, user.getTelefone());
            stmt.setInt(4, user.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM pessoa WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
