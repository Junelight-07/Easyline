import java.sql.*;
import java.util.ArrayList;

public class BagageDAO extends DAO<Bagage> {

    /**
     * @return Connection
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/easyline",
                "root", "");
        // return
        // DriverManager.getConnection("jdbc:mysql://172.22.102.164:3306/easyline_alexy",
        // "alexy",
        // "alexy");
    }

    public Bagage select(long id) {
        Bagage bagage = null;
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM bagage WHERE id=" + id)) {
            if (resultSet.next()) {
                bagage = new Bagage();
                bagage.setWeight(resultSet.getDouble("weight"));
                bagage.setColor(resultSet.getString("color"));
                bagage.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bagage;
    }

    public ArrayList<Bagage> selectAll() {
        ArrayList<Bagage> bagages = new ArrayList<>();
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM bagage")) {
            while (resultSet.next()) {
                Bagage bagage = new Bagage();
                bagage.setWeight(resultSet.getDouble("weight"));
                bagage.setColor(resultSet.getString("color"));
                bagage.setId(resultSet.getLong("id"));
                bagages.add(bagage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bagages;
    }

    public boolean insert(Bagage bagage) {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO bagage (number, weight, color) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, bagage.getNumber());
            preparedStatement.setDouble(2, bagage.getWeight());
            preparedStatement.setString(3, bagage.getColor());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Bagage bagage) {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE bagage SET number=?, weight=?, color=? WHERE id=?")) {
            preparedStatement.setInt(1, bagage.getNumber());
            preparedStatement.setDouble(2, bagage.getWeight());
            preparedStatement.setString(3, bagage.getColor());
            preparedStatement.setLong(4, bagage.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(long id) {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM bagage WHERE id=?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        BagageDAO dao = new BagageDAO();
        Bagage bagage = new Bagage(43, 4.7, "rouge");

        // insertion d'un bagage
        dao.insert(bagage);

        // tests sur les bagages
        bagage = dao.select(7);
        System.out.println(bagage);

        bagage.setColor("jaune");
        dao.update(bagage);

        System.out.println(dao.select(7));
    }
}
