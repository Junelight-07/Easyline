public class Voyage {
    private String lieuDepart, lieuArrivee, duree, date;
    private Float prix;
    private long id;

    public Voyage(String lieuDepart, String lieuArrivee, String duree, Float prix, String date) {
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.date = date;
        this.duree = duree;
        this.prix = prix;
    }

    public Voyage() {
        this.lieuDepart = "";
        this.lieuArrivee = "";
        this.duree = "";
        this.date = "";
        this.prix = 0.0f;
    }

    /**
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public void setLieuArrivee(String lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLieuDepart() {
        return this.lieuDepart;
    }

    public String getLieuArrivee() {
        return this.lieuArrivee;
    }

    public String getDuree() {
        return this.duree;
    }

    public Float getPrix() {
        return this.prix;
    }

    public String getDate() {
        return this.date;
    }

    public long getId() {
        return this.id;
    }

    public void displayInfo() {
        System.out.println("Lieu de départ: " + this.lieuDepart);
        System.out.println("Lieu d'arrivée: " + this.lieuArrivee);
        System.out.println("Date: " + this.date);
        System.out.println("Durée: " + this.duree);
        System.out.println("Prix: " + this.prix);
    }

    public String toString() {
        return "Lieu de départ: " + this.lieuDepart + "\nLieu d'arrivée: " + this.lieuArrivee + "\nDate: " + this.date
                + "\nDurée: " + this.duree + "\nPrix: " + this.prix + " €";
    }
}
