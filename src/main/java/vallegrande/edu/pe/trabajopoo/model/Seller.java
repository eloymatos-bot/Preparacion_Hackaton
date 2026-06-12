package vallegrande.edu.pe.trabajopoo.model;

import java.math.BigDecimal;

public class Seller {
    private int sellerId;
    private String documentType;
    private String documentNumber;
    private String names;
    private String lastNames;
    private String cellphone;
    private String email;
    private BigDecimal salary;
    private boolean status;

    public Seller() {}

    public Seller(int sellerId, String documentType, String documentNumber, String names,
                  String lastNames, String cellphone, String email, BigDecimal salary, boolean status) {
        this.sellerId = sellerId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.names = names;
        this.lastNames = lastNames;
        this.cellphone = cellphone;
        this.email = email;
        this.salary = salary;
        this.status = status;
    }

    public int getSellerId() { return sellerId; }
    public void setSellerId(int sellerId) { this.sellerId = sellerId; }

    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public String getNames() { return names; }
    public void setNames(String names) { this.names = names; }

    public String getLastNames() { return lastNames; }
    public void setLastNames(String lastNames) { this.lastNames = lastNames; }

    public String getCellphone() { return cellphone; }
    public void setCellphone(String cellphone) { this.cellphone = cellphone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
}
