package com.cdb.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class User.
 */
@Entity
@Table(name = "users")
public class User {

    /** The username. */
    private String username;

    /** The password. */
    private String password;

    /** The enabled. */
    private boolean enabled;

    /** The user role. */
    private Set<UserRole> userRole = new HashSet<UserRole>(0);

    /**
     * Instantiates a new user.
     */
    public User() {
    }

    /**
     * Instantiates a new user.
     *
     * @param username
     *            the username
     * @param password
     *            the password
     * @param enabled
     *            the enabled
     */
    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    /**
     * Instantiates a new user.
     *
     * @param username
     *            the username
     * @param password
     *            the password
     * @param enabled
     *            the enabled
     * @param userRole
     *            the user role
     */
    public User(String username, String password, boolean enabled,
            Set<UserRole> userRole) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userRole = userRole;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    @Id
    @Column(name = "username", unique = true, nullable = false, length = 45)
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username.
     *
     * @param username
     *            the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    @Column(name = "password", nullable = false, length = 60)
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password.
     *
     * @param password
     *            the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Checks if is enabled.
     *
     * @return true, if is enabled
     */
    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Sets the enabled.
     *
     * @param enabled
     *            the new enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Gets the user role.
     *
     * @return the user role
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    /**
     * Sets the user role.
     *
     * @param userRole
     *            the new user role
     */
    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    /**
     * To string.
     *
     * @return la chaine de caractere a afficher
     */
    @Override
    public String toString() {

        return "User [username=" + username + ", password=" + password
                + ", enabled=" + enabled + ", userRole=" + userRole + "]";

    }

}
