package ar.com.javacuriosities.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="bosses")
public class Boss extends Person {

    private String mobilePhone;

    @Column(name="number_of_workers")
    private Integer numberOfWorkers;

    public Boss() {
    }

    public Boss(String name, String lastName, String mobilePhone, Integer numberOfWorkers) {
        super(name, lastName);
        this.mobilePhone = mobilePhone;
        this.numberOfWorkers = numberOfWorkers;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Integer getNumberOfWorkers() {
        return numberOfWorkers;
    }

    public void setNumberOfWorkers(Integer numberOfWorkers) {
        this.numberOfWorkers = numberOfWorkers;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "mobilePhone='" + mobilePhone + '\'' +
                ", numberOfWorkers=" + numberOfWorkers +
                '}';
    }
}
