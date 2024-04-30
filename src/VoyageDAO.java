import java.sql.*;
import java.util.ArrayList;

public class VoyageDAO extends DAO<Voyage> {

    /**
     * @return Connection
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/easyline", "root",
                    "");
            // connection =
            // DriverManager.getConnection("jdbc:mysql://172.22.102.164:3306/easyline_alexy",
            // "alexy",
            // "alexy");
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }

    public Voyage select(long id) {
        Voyage voyage = new Voyage();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM voyage WHERE id=" + id);
            if (resultSet.next()) {
                voyage.setLieuDepart(resultSet.getString("lieu_depart"));
                voyage.setLieuArrivee(resultSet.getString("lieu_arrivee"));
                voyage.setDate(resultSet.getString("date"));
                voyage.setDuree(resultSet.getString("duree"));
                voyage.setPrix(resultSet.getFloat("prix"));
                voyage.setId(resultSet.getLong("id"));
                return voyage;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean insert(Voyage voyage) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "INSERT INTO voyage (lieu_depart, lieu_arrivee, duree, prix, date) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, voyage.getLieuDepart());
            preparedStatement.setString(2, voyage.getLieuArrivee());
            preparedStatement.setString(3, voyage.getDuree());
            preparedStatement.setFloat(4, voyage.getPrix());
            preparedStatement.setString(5, voyage.getDate());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean update(Voyage voyage) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "UPDATE voyage SET lieu_depart=?, lieu_arrivee=?, duree=?, prix=?, date=? WHERE id=?");
            preparedStatement.setString(1, voyage.getLieuDepart());
            preparedStatement.setString(2, voyage.getLieuArrivee());
            preparedStatement.setString(3, voyage.getDuree());
            preparedStatement.setFloat(4, voyage.getPrix());
            preparedStatement.setString(5, voyage.getDate());
            preparedStatement.setLong(6, voyage.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean delete(long id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM voyage WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<Voyage> selectAll() {
        ArrayList<Voyage> voyages = new ArrayList<Voyage>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM voyage");
            while (resultSet.next()) {
                Voyage voyage = new Voyage();
                voyage.setLieuDepart(resultSet.getString("lieu_depart"));
                voyage.setLieuArrivee(resultSet.getString("lieu_arrivee"));
                voyage.setDate(resultSet.getString("date"));
                voyage.setDuree(resultSet.getString("duree"));
                voyage.setPrix(resultSet.getFloat("prix"));
                voyage.setId(resultSet.getLong("id"));
                voyages.add(voyage);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return voyages;
    }
}