package com.suphse.ecommerce.customer.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.suphse.ecommerce.customer.config.AccountType;
import com.suphse.ecommerce.customer.entity.manager.EntityManagerProvider;
import com.suphse.ecommerce.customer.listener.AccountDetailsListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name="account_details")
@EntityListeners({AuditingEntityListener.class})
public class AccountDetails implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", columnDefinition = "BINARY(16)")
	private UUID id;
	
	@Column(name="name")
	private String accountName;
	
	@NotNull(message = "Email is a manditory Field")
	@Email(message = "Invalid email")
	@Column(name="email", nullable = false)
	private String email;
	
	@NotNull(message = "firstName is a manditory Field")
	@Column(name="first_name", nullable = false)
	private String firstName;
	
	@NotNull(message = "lastName is a manditory Field")
	@Column(name="last_name", nullable = false)
	private String lastName;
	
	@Column(name = "account_type", columnDefinition = "VARCHAR(50) DEFAULT 'Personal Account'", nullable = false)
    private String accountType = AccountType.PERSONAL_ACCOUNT.getValue();
	
	
	@CreationTimestamp
	@Column(name = "creation_date", updatable = false, nullable = false)
    private LocalDateTime creationDate;
	
    @Column(name = "created_by", updatable = false, nullable = false)
	private String createdBy;

	@Column(name="last_update_date")
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;
	
	@Column(name="last_updated_by")
	private String lastUpdatedBy;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Override
	public String toString() {
		return "AccountDetails [id=" + id + ", accountName=" + accountName + ", email=" + email + ", firstName="
				+ firstName + ", lastName=" + lastName + ", accountType=" + accountType + ", creationDate="
				+ creationDate + ", createdBy=" + createdBy + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdatedBy="
				+ lastUpdatedBy + "]";
	}

	@PrePersist
    public void prePersist() {
        if (this.createdBy == null) {
            this.createdBy = "-1";
        }
        this.lastUpdatedBy = "-1";
        this.accountName = this.firstName+" "+this.lastName;
    }

    @PreUpdate
    public void preUpdate() {
    	this.lastUpdatedBy = "-1";
    	this.createdBy = "-1";
    	this.accountName = this.firstName+" "+this.lastName;
    }

	

}