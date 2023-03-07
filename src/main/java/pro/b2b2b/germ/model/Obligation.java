package pro.b2b2b.germ.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.Objects;

@Entity
public class Obligation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long debtorId;
    private Long creditorId;
    private Long cost;
    private ObligationStatus status;
    private Date paymentDate;

    public Obligation() {
    }

    public Obligation(Long debtorId, Long creditorId, Long cost, ObligationStatus status, Date paymentDate) {
        this.debtorId = debtorId;
        this.creditorId = creditorId;
        this.cost = cost;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Obligation that = (Obligation) o;
        return id.equals(that.id) && debtorId.equals(that.debtorId) && creditorId.equals(that.creditorId) && Objects.equals(cost, that.cost) && status == that.status && Objects.equals(paymentDate, that.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, debtorId, creditorId, cost, status, paymentDate);
    }

    @Override
    public String toString() {
        return "Obligation{" +
                "id=" + id +
                ", debtorId=" + debtorId +
                ", creditorId=" + creditorId +
                ", cost=" + cost +
                ", status=" + status +
                ", paymentDate=" + paymentDate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDebtorId() {
        return debtorId;
    }

    public void setDebtorId(Long debtorId) {
        this.debtorId = debtorId;
    }

    public Long getCreditorId() {
        return creditorId;
    }

    public void setCreditorId(Long creditorId) {
        this.creditorId = creditorId;
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
