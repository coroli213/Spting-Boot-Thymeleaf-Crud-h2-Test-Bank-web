package com.howtodoinjava.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class User_Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id")
    private Long id;

   // @OneToMany(mappedBy = "user_role")
   // private Collection<Role> roles;

   // @OneToMany(mappedBy = "user_role")
   // private Collection<User> users;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

 //   public Collection<Role> getRoles() {
 //       return roles;
 //   }
 //   public void setRoles(Collection<Role> roles) {
 //       this.roles = roles;
 //   }
//
 //   public Collection<User> getUsers() {
 //       return users;
 //   }
 //   public void setUsers(Collection<User> users) {
 //       this.users = users;
 //   }
}

