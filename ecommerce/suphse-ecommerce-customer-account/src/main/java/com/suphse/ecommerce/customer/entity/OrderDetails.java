package com.suphse.ecommerce.customer.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "account_id")
    private UUID accountId;
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AccountDetails accountDetails;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private double price;

    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "created_by")
    private String createdBy = "-1";

    @UpdateTimestamp
    @Column(name = "last_updated_date")
    private LocalDateTime lastUpdatedDate;

    @Column(name = "last_update_by")
    private String lastUpdatedBy = "-1";

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getAccountId() {
		return accountId;
	}

	public void setAccountId(UUID accountId) {
		this.accountId = accountId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", accountDetails=" + accountDetails + ", productName=" + productName
				+ ", price=" + price + ", creationDate=" + creationDate + ", createdBy=" + createdBy
				+ ", lastUpdatedDate=" + lastUpdatedDate + ", lastUpdatedBy=" + lastUpdatedBy + "]";
	}

/*	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", accountId=" + accountId + ", productName=" + productName + ", price="
				+ price + ", creationDate=" + creationDate + ", createdBy=" + createdBy + ", lastUpdatedDate="
				+ lastUpdatedDate + ", lastUpdatedBy=" + lastUpdatedBy + "]";
	}
*/
	
    
}

