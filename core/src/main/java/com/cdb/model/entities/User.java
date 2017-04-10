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
@Table(name = "users", catalog = "test")
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
     * Hash code.
     *
     * @return entier du hascode
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (enabled ? 1231 : 1237);
        result = prime * result
                + ((password == null) ? 0 : password.hashCode());
        result = prime * result
                + ((userRole == null) ? 0 : userRole.hashCode());
        result = prime * result
                + ((username == null) ? 0 : username.hashCode());
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

        User other = (User) obj;

        if (enabled != other.enabled) {

            return false;

        }

        if (password == null) {

            if (other.password != null) {

                return false;

            }

        } else if (!password.equals(other.password)) {

            return false;

        }

        if (userRole == null) {

            if (other.userRole != null) {

                return false;

            }

        } else if (!userRole.equals(other.userRole)) {

            return false;

        }

        if (username == null) {

            if (other.username != null) {

                return false;

            }

        } else if (!username.equals(other.username)) {

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

        return "User [username=" + username + ", password=" + password
                + ", enabled=" + enabled + ", userRole=" + userRole + "]";

    }

}
