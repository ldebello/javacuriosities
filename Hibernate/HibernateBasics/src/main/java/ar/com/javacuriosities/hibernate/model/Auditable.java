package ar.com.javacuriosities.hibernate.model;

public interface Auditable {

    Audit getAudit();

    void setAudit(Audit audit);
}
