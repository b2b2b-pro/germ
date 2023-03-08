package pro.b2b2b.germ.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Obligation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    private Organization debtor;
//    private Long debtorId;
    @ManyToOne
    private Organization creditor;
//    private Long creditorId;
    private Long cost;
    private ObligationStatus status;
    private Date paymentDate;

    public Obligation() {
    }

    public Obligation(Organization debtor, Organization creditor, Long cost, ObligationStatus status, Date paymentDate) {
        this.debtor = debtor;
        this.creditor = creditor;
        this.cost = cost;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Obligation that = (Obligation) o;
        return id.equals(that.id) && debtor.equals(that.debtor) && creditor.equals(that.creditor) && Objects.equals(cost, that.cost) && status == that.status && Objects.equals(paymentDate, that.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, debtor, creditor, cost, status, paymentDate);
    }

    public Organization getDebtor() {
        return debtor;
    }

    public void setDebtor(Organization debtor) {
        this.debtor = debtor;
    }

    public Organization getCreditor() {
        return creditor;
    }

    public void setCreditor(Organization creditor) {
        this.creditor = creditor;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public ObligationStatus getStatus() {
        return status;
    }

    public void setStatus(ObligationStatus status) {
        this.status = status;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
