package com.esprit.weBank.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.esprit.weBank.util.PriorityEnumReclamtion;
import com.esprit.weBank.util.TypeReclamation;


@Entity
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    @Enumerated(EnumType.STRING)
    private TypeReclamation type;
    @Column
    @Enumerated(EnumType.STRING)
    private PriorityEnumReclamtion priorityEnum;
    @Column
    private String status;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;


    public Reclamation( String title, String description, TypeReclamation type,  PriorityEnumReclamtion priorityEnum, String status) {
        super();
        //this.product = product;
        this.title = title;
        this.description = description;
        this.type = type;
        this.priorityEnum= priorityEnum;
        this.status = status;
        this.user = user;
    }

    public Reclamation() {
        super();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeReclamation getType() {
        return type;
    }

    public void setType(TypeReclamation type) {
        this.type = type;
    }

    public PriorityEnumReclamtion getPriorityEnum() {
        return priorityEnum;
    }

    public void setPriorityEnum(PriorityEnumReclamtion priorityEnum) {
        this.priorityEnum = priorityEnum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}


