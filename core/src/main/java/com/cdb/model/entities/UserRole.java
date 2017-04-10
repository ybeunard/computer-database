package com.cdb.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The Class UserRole.
 */
@Entity
@Table(name = "user_roles", catalog = "test", uniqueConstraints = @UniqueConstraint(columnNames = {
        "role", "username" }))
public class UserRole {

    /** The user role id. */
    private Integer userRoleId;

    /** The user. */
    private User user;

    /** The role. */
    private String role;

    /**
     * Instantiates a new user role.
     */
    public UserRole() {
    }

    /**
     * Instantiates a new user role.
     *
     * @param user
     *            the user
     * @param role
     *            the role
     */
    public UserRole(User user, String role) {

        this.user = user;
        this.role = role;

    }

    /**
     * Gets the user role id.
     *
     * @return the user role id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id", unique = true, nullable = false)
    public Integer getUserRoleId() {

        return this.userRoleId;

    }

    /**
     * Sets the user role id.
     *
     * @param userRoleId
     *            the new user role id
     */
    public void setUserRoleId(Integer userRoleId) {

        this.userRoleId = userRoleId;

    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    public User getUser() {

        return this.user;

    }

    /**
     * Sets the user.
     *
     * @param user
     *            the new user
     */
    public void setUser(User user) {

        this.user = user;

    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    @Column(name = "role", nullable = false, length = 45)
    public String getRole() {

        return this.role;

    }

    /**
     * Sets the role.
     *
     * @param role
     *            the new role
     */
    public void setRole(String role) {

        this.role = role;

    }

    /**
     * Hash code.
     *
     * @return entier du ascode
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result
                + ((userRoleId == null) ? 0 : userRoleId.hashCode());
        return result;

    }

    /**
     * Equals.
     *
     * @param obj
     *            de comparaison
     * @return vrai ou faux
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {

            return true;

        }

        if (obj == null) {

            return false;

        }

        if (getClass() != obj.getClass()) {

            return false;

        }

        UserRole other = (UserRole) obj;

        if (role == null) {

            if (other.role != null) {

                return false;

            }

        } else if (!role.equals(other.role)) {

            return false;

        }

        if (user == null) {

            if (other.user != null) {

                return false;

            }

        } else if (!user.equals(other.user)) {

            return false;

        }

        if (userRoleId == null) {

            if (other.userRoleId != null) {

                return false;

            }

        } else if (!userRoleId.equals(other.userRoleId)) {

            return false;

        }

        return true;

    }

    /**
     * To string.
     *
     * @return la chaine de caractere a afficher
     */
    @Override
    public String toString() {

        return "UserRole [userRoleId=" + userRoleId + ", user=" + user
                + ", role=" + role + "]";

    }

}
