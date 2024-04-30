public class AdressePostale {
    private String street, city;
    private int postalCode;
    private long id;

    // initialisation d'un constructeur à 3 paramètres
    public AdressePostale(String street, String city, int postalCode) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    /**
     * @param street
     */
    // fonction qui permet de changer la rue d'une adresse
    public void setStreet(String street) {
        this.street = street;
    }

    // fonction qui permet de changer la ville d'une adresse
    public void setCity(String city) {
        this.city = city;
    }

    // fonction qui permet de changer le code postal d'une adresse
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setId(long id) {
        this.id = id;
    }

    // fonction qui permet de retourner la rue d'une adresse
    public String getStreet() {
        return this.street;
    }

    // fonction qui permet de retourner la ville d'une adresse
    public String getCity() {
        return this.city;
    }

    // fonction qui permet de retourner le code postal d'une adresse
    public int getPostalCode() {
        return this.postalCode;
    }

    public long getId() {
        return this.id;
    }

    // fonction qui affiche les informations d'un voyageur
    public void displayInfo() {
        System.out.println("Rue: " + this.street);
        System.out.println("Ville: " + this.city);
        System.out.println("Code postal: " + this.postalCode);
    }

    public String toString() {
        return "Rue: " + this.street + "\nVille: " + this.city + "\nCode postal: " + this.postalCode;
    }
}
