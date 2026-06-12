package vallegrande.edu.pe.trabajopoo.controller;

import vallegrande.edu.pe.trabajopoo.model.Seller;
import vallegrande.edu.pe.trabajopoo.model.SellerDAO;
import java.sql.SQLException;
import java.util.List;

public class SellerController {
    private final SellerDAO dao = new SellerDAO();

    public List<Seller> listar() throws SQLException {
        return dao.listar();
    }

    public void insertar(Seller obj) throws SQLException {
        dao.insertar(obj);
    }

    public void actualizar(Seller obj) throws SQLException {
        dao.actualizar(obj);
    }

    public void eliminar(int id) throws SQLException {
        dao.eliminar(id);
    }
}
