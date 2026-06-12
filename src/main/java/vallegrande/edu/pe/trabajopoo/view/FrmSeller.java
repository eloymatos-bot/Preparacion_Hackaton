package vallegrande.edu.pe.trabajopoo.view;

import vallegrande.edu.pe.trabajopoo.controller.SellerController;
import vallegrande.edu.pe.trabajopoo.model.Seller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class FrmSeller extends JFrame {

    private SellerController controller = new SellerController();
    private DefaultTableModel modelo;
    private JTable tabla;
    private JTextField txtDocType, txtDocNum, txtNames, txtLastNames, txtCellphone, txtEmail, txtSalary;
    private JCheckBox chkStatus;
    private int idSeleccionado = -1;

    public FrmSeller() {
        setTitle("ADCAL - Gestión de Vendedores");
        setSize(950, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridLayout(8, 2, 8, 8));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Vendedor"));
        panelForm.add(new JLabel("Tipo Doc:")); txtDocType = new JTextField(); panelForm.add(txtDocType);
        panelForm.add(new JLabel("Nro. Documento:")); txtDocNum = new JTextField(); panelForm.add(txtDocNum);
        panelForm.add(new JLabel("Nombres:")); txtNames = new JTextField(); panelForm.add(txtNames);
        panelForm.add(new JLabel("Apellidos:")); txtLastNames = new JTextField(); panelForm.add(txtLastNames);
        panelForm.add(new JLabel("Celular:")); txtCellphone = new JTextField(); panelForm.add(txtCellphone);
        panelForm.add(new JLabel("Email:")); txtEmail = new JTextField(); panelForm.add(txtEmail);
        panelForm.add(new JLabel("Salario:")); txtSalary = new JTextField(); panelForm.add(txtSalary);
        panelForm.add(new JLabel("Activo:")); chkStatus = new JCheckBox(); chkStatus.setSelected(true); panelForm.add(chkStatus);

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnGuardar = new JButton("Guardar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnGuardar); panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar); panelBotones.add(btnLimpiar);

        JPanel panelNorth = new JPanel(new BorderLayout());
        panelNorth.add(panelForm, BorderLayout.CENTER);
        panelNorth.add(panelBotones, BorderLayout.SOUTH);

        modelo = new DefaultTableModel(new String[]{"ID", "Tipo Doc", "Nro Doc", "Nombres", "Apellidos", "Celular", "Email", "Salario", "Estado"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(panelNorth, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> guardar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiar());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                int fila = tabla.getSelectedRow();
                idSeleccionado = (int) modelo.getValueAt(fila, 0);
                txtDocType.setText((String) modelo.getValueAt(fila, 1));
                txtDocNum.setText((String) modelo.getValueAt(fila, 2));
                txtNames.setText((String) modelo.getValueAt(fila, 3));
                txtLastNames.setText((String) modelo.getValueAt(fila, 4));
                txtCellphone.setText((String) modelo.getValueAt(fila, 5));
                txtEmail.setText((String) modelo.getValueAt(fila, 6));
                txtSalary.setText(String.valueOf(modelo.getValueAt(fila, 7)));
                chkStatus.setSelected((Boolean) modelo.getValueAt(fila, 8));
            }
        });
    }

    private void cargarDatos() {
        try {
            modelo.setRowCount(0);
            List<Seller> lista = controller.listar();
            for (Seller s : lista)
                modelo.addRow(new Object[]{s.getSellerId(), s.getDocumentType(), s.getDocumentNumber(), s.getNames(), s.getLastNames(), s.getCellphone(), s.getEmail(), s.getSalary(), s.isStatus()});
        } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage()); }
    }

    private Seller buildSeller(int id) {
        return new Seller(id, txtDocType.getText(), txtDocNum.getText(), txtNames.getText(),
                txtLastNames.getText(), txtCellphone.getText(), txtEmail.getText(),
                new BigDecimal(txtSalary.getText()), chkStatus.isSelected());
    }

    private void guardar() {
        try { controller.insertar(buildSeller(0)); cargarDatos(); limpiar(); JOptionPane.showMessageDialog(this, "Guardado correctamente."); }
        catch (Exception ex) { JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage()); }
    }

    private void actualizar() {
        if (idSeleccionado == -1) { JOptionPane.showMessageDialog(this, "Selecciona un registro."); return; }
        try { controller.actualizar(buildSeller(idSeleccionado)); cargarDatos(); limpiar(); JOptionPane.showMessageDialog(this, "Actualizado correctamente."); }
        catch (Exception ex) { JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage()); }
    }

    private void eliminar() {
        if (idSeleccionado == -1) { JOptionPane.showMessageDialog(this, "Selecciona un registro."); return; }
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try { controller.eliminar(idSeleccionado); cargarDatos(); limpiar(); JOptionPane.showMessageDialog(this, "Eliminado correctamente."); }
            catch (Exception ex) { JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage()); }
        }
    }

    private void limpiar() {
        txtDocType.setText(""); txtDocNum.setText(""); txtNames.setText(""); txtLastNames.setText("");
        txtCellphone.setText(""); txtEmail.setText(""); txtSalary.setText("");
        chkStatus.setSelected(true); idSeleccionado = -1; tabla.clearSelection();
    }
}
