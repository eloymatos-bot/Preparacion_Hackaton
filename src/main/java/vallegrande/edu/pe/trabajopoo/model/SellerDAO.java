package vallegrande.edu.pe.trabajopoo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellerDAO {

    public List<Seller> listar() throws SQLException {
        List<Seller> lista = new ArrayList<>();
        String sql = "SELECT * FROM seller";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public void insertar(Seller s) throws SQLException {
        String sql = "INSERT INTO seller (document_type, document_number, names, last_names, cellphone, email, salary, status) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getDocumentType());
            ps.setString(2, s.getDocumentNumber());
            ps.setString(3, s.getNames());
            ps.setString(4, s.getLastNames());
            ps.setString(5, s.getCellphone());
            ps.setString(6, s.getEmail());
            ps.setBigDecimal(7, s.getSalary());
            ps.setBoolean(8, s.isStatus());
            ps.executeUpdate();
        }
    }

    public void actualizar(Seller s) throws SQLException {
        String sql = "UPDATE seller SET document_type=?, document_number=?, names=?, last_names=?, cellphone=?, email=?, salary=?, status=? WHERE seller_id=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getDocumentType());
            ps.setString(2, s.getDocumentNumber());
            ps.setString(3, s.getNames());
            ps.setString(4, s.getLastNames());
            ps.setString(5, s.getCellphone());
            ps.setString(6, s.getEmail());
            ps.setBigDecimal(7, s.getSalary());
            ps.setBoolean(8, s.isStatus());
            ps.setInt(9, s.getSellerId());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM seller WHERE seller_id=?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Seller mapear(ResultSet rs) throws SQLException {
        return new Seller(
                rs.getInt("seller_id"),
                rs.getString("document_type"),
                rs.getString("document_number"),
                rs.getString("names"),
                rs.getString("last_names"),
                rs.getString("cellphone"),
                rs.getString("email"),
                rs.getBigDecimal("salary"),
                rs.getBoolean("status")
        );
    }
}
