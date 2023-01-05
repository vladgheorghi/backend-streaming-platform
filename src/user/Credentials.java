package user;

/**
 * @class class for credentials data
 * @details used for login, register, parsing JSON input data. Contains user credentials
 * */

public class Credentials {
    private String name; /** user name */
    private String password; /** user password */
    private String accountType; /** user account type ('standard' or 'premium') */
    private String country; /** user provenience country */
    private String balance; /** user account balance */

    /** Getters */
    public final String getName() {
        return name;
    }

    public final String getPassword() {
        return password;
    }

    public final String getAccountType() {
        return accountType;
    }

    public final String getCountry() {
        return country;
    }

    public final String getBalance() {
        return balance;
    }

    /** Setters */
    public final void setName(final String name) {
        this.name = name;
    }

    public final void setPassword(final String password) {
        this.password = password;
    }

    public final void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public final void setCountry(final String country) {
        this.country = country;
    }

    public final void setBalance(final String balance) {
        this.balance = balance;
    }
}
