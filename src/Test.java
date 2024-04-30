public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        VoyageurDAO vdao = new VoyageurDAO();

        Voyageur voyageur1 = new Voyageur("Peter Parker", 23);
        AdressePostale ad1 = new AdressePostale("Daily Bugle", "New York", 54321);

        vdao.insertVoyageurWithAdresse(voyageur1, ad1);
    }
}
