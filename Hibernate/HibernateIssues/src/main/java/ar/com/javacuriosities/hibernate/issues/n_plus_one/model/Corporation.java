package ar.com.javacuriosities.hibernate.issues.n_plus_one.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "corporations")
public class Corporation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    private List<Company> companies = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "corporation")
    @Fetch(FetchMode.JOIN)
    private List<CorporationOwner> corporationOwners = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<CorporationOwner> getCorporationOwners() {
        return corporationOwners;
    }

    public void setCorporationOwners(List<CorporationOwner> corporationOwners) {
        this.corporationOwners = corporationOwners;
    }

    public void addCompany(Company company) {
        companies.add(company);
    }

    public void addCorporationOwner(CorporationOwner corporationOwner) {
        corporationOwners.add(corporationOwner);
    }
}
