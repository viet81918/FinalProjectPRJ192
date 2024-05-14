package Controller;

import Model.AddList;
import Model.Admin;
import Model.Author;
import Model.AuthorList;
import Model.Chapter;
import Model.ChapterList;
import Model.Customer;
import Model.FollowList;
import Model.Format;
import Model.Genre;
import Model.Illustration;
import Model.IllustrationList;
import Model.Novel;
import Model.Theme;
import Model.Users;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp; // Import java.sql.Timestamp
import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Java_JDBC {

    public static Connection getConnectionWithSqlJdbc() throws Exception {
        Connection con = null;
        String dbUser = "sa";
        String dbPassword = "123";
        String port = "1433";
        String IP = "127.0.0.1";
        String ServerName = "DESKTOP-47R8QHN";
        String DBName = "HakoDex";
        String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        String dbURL = "jdbc:sqlserver://DESKTOP-47R8QHN;databaseName=HakoDex;encrypt=false;trustServerCertificate=false;loginTimeout=30";
        //String dbURL = "jdbc:sqlserver://DESKTOP-UEDQ7P6\\HOATNTT;databaseName=School;encrypt=false;trustServerCertificate=false;loginTimeout=30";         
        try {
            Class.forName(driverClass);
            //DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return con;
    }
    public static boolean deleteNovelAndAssociatedData(Connection con, String novelId) throws SQLException {
        try {
            // Disable auto-commit to handle transaction
            con.setAutoCommit(false);

            deleteFromTable(con, "Novel_Format", "NovelID", novelId);
            deleteFromTable(con, "Novel_Theme", "NovelID", novelId);
            deleteFromTable(con, "Novel_Genre", "NovelID", novelId);
            deleteFromTable(con, "AuthorList", "NovelID", novelId);
            deleteFromTable(con, "AddList", "NovelID", novelId);
            deleteFromTable(con,"FollowList","NovelID",novelId);

            // Delete records from linking tables
            deleteFromTable(con, "NovelChapter", "NovelID", novelId);
            deleteFromTable(con, "NovelIllustration", "NovelID", novelId);

            // Finally, delete from the Novel table
            deleteFromTable(con, "Novel", "NovelID", novelId);

            // Commit the transaction
            con.commit();
            return true;
        } catch (SQLException ex) {
            // Rollback transaction in case of exception
            con.rollback();
            ex.printStackTrace();
            throw ex;
        } finally {
            // Reset auto-commit to its default state
            con.setAutoCommit(true);
        }
    }
    

    // Method to delete records from a table based on a condition
    private static void deleteFromTable(Connection con, String tableName, String columnId, String novelId) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE " + columnId + " = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, novelId);
            ps.executeUpdate();
        }
    }
   public static boolean updateIlluStrationInDatabase(String novelId, String novelName, String illustrationId, String fileName, InputStream fileContent) throws SQLException, IOException, Exception {
    String directoryPath = "E:\\FPT UNI\\HoaiNTT40-SP24-PRJ301-Web-based Java Applications\\Final_Project_PRJ301\\web\\Illustration\\" + novelName;
    String filePath = directoryPath + "\\" + fileName;

    // Create directory if it doesn't exist
    Path directory = Paths.get(directoryPath);
    if (!Files.exists(directory)) {
        Files.createDirectories(directory);
    }

    // Save illustration file to disk
    try (OutputStream outputStream = new FileOutputStream(new File(filePath))) {
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = fileContent.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
    }
  

   

    try (Connection connection = Java_JDBC.getConnectionWithSqlJdbc();
         PreparedStatement illustrationStatement = connection.prepareStatement("INSERT INTO Illustration (IllustrationID, IllustrationLink) VALUES (?, ?)");
         PreparedStatement novelIllustrationStatement = connection.prepareStatement("INSERT INTO NovelIllustration (NovelID, IllustrationID) VALUES (?, ?)")) {

        // Insert illustration details into the Illustration table
        illustrationStatement.setString(1, illustrationId);
        illustrationStatement.setString(2, filePath);
        int rowsAffected = illustrationStatement.executeUpdate();

        if (rowsAffected > 0) {
            // Insert novel ID and illustration ID into the NovelIllustration table
            novelIllustrationStatement.setString(1, novelId);
            novelIllustrationStatement.setString(2, illustrationId);
            int novelIllustrationRowsAffected = novelIllustrationStatement.executeUpdate();

            return novelIllustrationRowsAffected > 0;
        }
    }

    return false;
}
public ArrayList<Novel> searchNovelByName(String NovelName) throws Exception{
        ArrayList<Novel> novelList = getAllNovels(getConnectionWithSqlJdbc());
        ArrayList<Novel> found = new ArrayList<>();
        for (Novel n : novelList){
            if(n.getNovelName().contains(NovelName)){
                found.add(n);
            }
        }
        return found;
    }
  public static void deleteFormat(Connection con, String typeId) throws SQLException {
    String query1 = "DELETE FROM NovelFormat WHERE TypeID = ?";
    String query2 = "DELETE FROM Novel_Format WHERE FormatTypeID = ?";
    try (PreparedStatement pstmt = con.prepareStatement(query2)) {
        pstmt.setString(1, typeId);
        pstmt.executeUpdate();
    }
    try (PreparedStatement pstmt = con.prepareStatement(query1)) {
        pstmt.setString(1, typeId);
        pstmt.executeUpdate();
    }
}

public static void deleteTheme(Connection con, String typeId) throws SQLException {
    String query1 = "DELETE FROM NovelTheme WHERE TypeID = ?";
    String query2 = "DELETE FROM Novel_Theme WHERE ThemeTypeID = ?";
    try (PreparedStatement pstmt = con.prepareStatement(query2)) {
        pstmt.setString(1, typeId);
        pstmt.executeUpdate();
    }
    try (PreparedStatement pstmt = con.prepareStatement(query1)) {
        pstmt.setString(1, typeId);
        pstmt.executeUpdate();
    }
}

public static void deleteGenre(Connection con, String typeId) throws SQLException {
    String query1 = "DELETE FROM NovelGenre WHERE TypeID = ?";
    String query2 = "DELETE FROM Novel_Genre WHERE GenreTypeID = ?";
    try (PreparedStatement pstmt = con.prepareStatement(query2)) {
        pstmt.setString(1, typeId);
        pstmt.executeUpdate();
    }
    try (PreparedStatement pstmt = con.prepareStatement(query1)) {
        pstmt.setString(1, typeId);
        pstmt.executeUpdate();
    }
}
public static void deleteAuthor(Connection con, String authorId) throws SQLException {
    String query1 = "DELETE FROM Author WHERE ID = ?";
    String query2 = "DELETE FROM AuthorList WHERE AuthorID = ?";
    try (PreparedStatement pstmt = con.prepareStatement(query2)) {
        pstmt.setString(1, authorId);
        pstmt.executeUpdate();
    }
    try (PreparedStatement pstmt = con.prepareStatement(query1)) {
        pstmt.setString(1, authorId);
        pstmt.executeUpdate();
    }
}

    
     public static boolean updateChapterInDatabase(String novelId, String novelName, String chapterId, String fileName, InputStream fileContent) throws SQLException, IOException, Exception {

    String directoryPath = "E:\\FPT UNI\\HoaiNTT40-SP24-PRJ301-Web-based Java Applications\\Final_Project_PRJ301\\web\\Novel\\" + novelName;
    String filePath = directoryPath + "\\" + fileName;

    // Create directory if it doesn't exist
    Path directory = Paths.get(directoryPath);
    if (!Files.exists(directory)) {
        Files.createDirectories(directory);
    }

    // Save text file to disk
    try (OutputStream outputStream = new FileOutputStream(new File(filePath))) {
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = fileContent.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
    }

    // Get current timestamp
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    try (Connection connection = Java_JDBC.getConnectionWithSqlJdbc();
         PreparedStatement statement = connection.prepareStatement("INSERT INTO Chapter (ChapterID, LinkContent, CreationDate) VALUES (?, ?, ?)");
         PreparedStatement novelChapterStatement = connection.prepareStatement("INSERT INTO NovelChapter (NovelID, ChapterID) VALUES (?, ?)");
         PreparedStatement updateChapterCountStatement = connection.prepareStatement("UPDATE Novel SET NumberOfChapter = NumberOfChapter + 1 WHERE NovelID = ?")) {

        // Insert chapter details into the Chapter table
        statement.setString(1, chapterId);
        statement.setString(2, filePath);
        statement.setTimestamp(3, timestamp); // Set current timestamp
        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            // Insert chapter ID and novel ID into the NovelChapter table
            novelChapterStatement.setString(1, novelId);
            novelChapterStatement.setString(2, chapterId);
            int novelChapterRowsAffected = novelChapterStatement.executeUpdate();

            if (novelChapterRowsAffected > 0) {
                // Increment the chapter count for the corresponding novel in the Novel table
                updateChapterCountStatement.setString(1, novelId);
                int updateCount = updateChapterCountStatement.executeUpdate();
                return updateCount > 0;
            }
        }
    }

    return false;
}
     public static Chapter getChapterById(String chapterId) throws SQLException, Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    Chapter chapter = null;

    try {
        connection = Java_JDBC.getConnectionWithSqlJdbc();
        String query = "SELECT * FROM Chapter WHERE ChapterID = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, chapterId);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Retrieve chapter details from the result set
            String linkContent = resultSet.getString("LinkContent");
            Date creationDate = resultSet.getDate("CreationDate");

            // Create a Chapter object
            chapter = new Chapter(chapterId, linkContent, creationDate);
        }
    } finally {
        // Close resources
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    return chapter;
}



public static boolean deleteChapterFromDatabase(String chapterId) throws SQLException, IOException, Exception {
    try (Connection connection = Java_JDBC.getConnectionWithSqlJdbc();
  
         PreparedStatement deleteChapterStatement =           connection.prepareStatement("UPDATE Novel SET NumberOfChapter = NumberOfChapter - 1 WHERE NovelID = (SELECT NovelID FROM NovelChapter WHERE ChapterID = ?)");
         PreparedStatement deleteNovelChapterStatement = connection.prepareStatement("DELETE FROM NovelChapter WHERE ChapterID = ?");
         PreparedStatement updateChapterCountStatement = connection.prepareStatement("DELETE FROM Chapter WHERE ChapterID = ?");
    ) {
        // Retrieve the linkContent from the Chapter table using chapterId
        String linkContent = getChapterById(chapterId).getLinkContent();
        
        // Delete associated file
        if (linkContent != null && !linkContent.isEmpty()) {
            File fileToDelete = new File(linkContent);
            if (fileToDelete.exists() && fileToDelete.delete()) {
                System.out.println("File deleted successfully.");
            } }

        // Set chapterId parameter for all prepared statements
        deleteChapterStatement.setString(1, chapterId);
        deleteNovelChapterStatement.setString(1, chapterId);
        updateChapterCountStatement.setString(1, chapterId);

        // Delete chapter entry from the Chapter table
        deleteChapterStatement.executeUpdate();

        // Delete chapter entry from the NovelChapter table
        deleteNovelChapterStatement.executeUpdate();

        // Decrement chapter count for the corresponding novel in the Novel table
        updateChapterCountStatement.executeUpdate();

        // Deletion successful
        return true;
    } catch (Exception e) {
        System.err.println("Error deleting chapter: " + e.getMessage());
        return false; // Deletion failed
    }
    
}

public static boolean deleteIllustrationFromDatabase(String illustrationId) throws SQLException, IOException, Exception {
    try (Connection connection = Java_JDBC.getConnectionWithSqlJdbc();
         PreparedStatement deleteIllustrationStatement = connection.prepareStatement("DELETE FROM NovelIllustration WHERE IllustrationID = ?");
         PreparedStatement deleteNovelIllustrationStatement = connection.prepareStatement("DELETE FROM Illustration WHERE IllustrationID = ?");
    ) {
        // Retrieve the illustration link from the Illustration table using illustrationId
        String illustrationLink = getIllustrationById(illustrationId).getIllutrastionLink();
        
        // Delete associated file
        if (illustrationLink != null && !illustrationLink.isEmpty()) {
            File fileToDelete = new File(illustrationLink);
            if (fileToDelete.exists() && fileToDelete.delete()) {
            }
        }

        // Set illustrationId parameter for the prepared statement
        deleteIllustrationStatement.setString(1, illustrationId);
        deleteNovelIllustrationStatement.setString(1, illustrationId);

        // Delete illustration entry from the Illustration table
        int illustrationDeleted = deleteIllustrationStatement.executeUpdate();
        // Delete illustration entry from the NovelIllustration table
        int novelIllustrationDeleted = deleteNovelIllustrationStatement.executeUpdate();

        // Check if illustration deletion was successful
        if (illustrationDeleted > 0 && novelIllustrationDeleted > 0) {
            // Deletion successful
            return true;
        } else {
            // Deletion failed
            return false;
        }
    } catch (Exception e) {
        System.err.println("Error deleting illustration: " + e.getMessage());
        return false; // Deletion failed
    }
}



    public boolean editNovel(Novel n) throws Exception {
        // Câu lệnh SQL để cập nhật thông tin tiểu thuyết
        String sql = "UPDATE Novel SET NovelLanguage = ?, NovelName = ?, Profit = ?, LicenseProfit = ?, "
                + "PublicationYear = ?, PublicationStatus = ?, [Description] = ? WHERE NovelID = ?";

        try {
            Connection con = getConnectionWithSqlJdbc();
            int rowsAffected;
            // Thiết lập các tham số cho câu lệnh SQL
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                // Thiết lập các tham số cho câu lệnh SQL
                pstmt.setString(1, n.getNovelLanguage());
                pstmt.setString(2, n.getNovelName());

                pstmt.setFloat(3, n.getProfit());
                pstmt.setFloat(4, n.getLicenseProfit());
                pstmt.setDate(5, new java.sql.Date(n.getPublicationYear().getTime()));
                pstmt.setString(6, n.getPublicationStatus());
                pstmt.setString(7, n.getDescription());
                pstmt.setString(8, n.getNovelId());
                rowsAffected = pstmt.executeUpdate();
            }

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Trả về false nếu có lỗi xảy ra hoặc không có hàng nào được cập nhật
        return false;
    }
public static Illustration getIllustrationById(String illustrationId) throws SQLException, Exception {
    Illustration illustration = null;
    String query = "SELECT * FROM Illustration WHERE IllustrationID = ?";
    try (Connection connection = Java_JDBC.getConnectionWithSqlJdbc();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, illustrationId);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                String illustrationLink = resultSet.getString("IllustrationLink");
                illustration = new Illustration(illustrationId, illustrationLink);
            }
        }
    }
    return illustration;
}
    public static void addNovelToDatabase(Connection connection, Novel novel) throws SQLException {
        String novelInsertQuery = "INSERT INTO Novel (NovelID, NovelLanguage, NovelName, NovelLength, NumberOfChapter, Profit, LicenseProfit, NumberRead, NumberWatch, NumberFollow, Rating, PublicationYear, PublicationStatus, [Description]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement novelStatement = connection.prepareStatement(novelInsertQuery)) {
            novelStatement.setString(1, novel.getNovelId());
            novelStatement.setString(2, novel.getNovelLanguage());
            novelStatement.setString(3, novel.getNovelName());
            novelStatement.setInt(4, novel.getNovelLength());
            novelStatement.setInt(5, novel.getNumberOfChapters());
            novelStatement.setFloat(6, novel.getProfit());
            novelStatement.setFloat(7, novel.getLicenseProfit());
            novelStatement.setInt(8, novel.getNumberRead());
            novelStatement.setInt(9, novel.getNumberWatch());
            novelStatement.setInt(10, novel.getNumberFollow());
            novelStatement.setInt(11, novel.getRating());
            if (novel.getPublicationYear() != null) {
                Date date = novel.getPublicationYear();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                novelStatement.setDate(12, sqlDate);
            } else {
                novelStatement.setNull(12, java.sql.Types.DATE);
            }
            novelStatement.setString(13, novel.getPublicationStatus());
            novelStatement.setString(14, novel.getDescription());
            novelStatement.executeUpdate();

        }
    }

    public static void addRelatedData(Connection connection, Novel novel, ArrayList<Format> formats, ArrayList<Genre> genres, ArrayList<Theme> themes, ArrayList<Author> authors) throws SQLException {
        String formatInsertQuery = "INSERT INTO Novel_Format (NovelID, FormatTypeID) VALUES (?, ?)";
        String genreInsertQuery = "INSERT INTO Novel_Genre (NovelID, GenreTypeID) VALUES (?, ?)";
        String themeInsertQuery = "INSERT INTO Novel_Theme (NovelID, ThemeTypeID) VALUES (?, ?)";
        String authorInsertQuery = "INSERT INTO AuthorList (NovelID, AuthorID) VALUES (?, ?)";
        try (PreparedStatement formatStatement = connection.prepareStatement(formatInsertQuery); PreparedStatement genreStatement = connection.prepareStatement(genreInsertQuery); PreparedStatement themeStatement = connection.prepareStatement(themeInsertQuery); PreparedStatement authorStatement = connection.prepareStatement(authorInsertQuery)) {

            // Insert into Novel_Format table
            for (Format format : formats) {
                formatStatement.setString(1, novel.getNovelId());
                formatStatement.setString(2, format.getTypeId());
                formatStatement.addBatch();
            }
            formatStatement.executeUpdate();

            // Insert into Novel_Genre table
            for (Genre genre : genres) {
                genreStatement.setString(1, novel.getNovelId());
                genreStatement.setString(2, genre.getTypeId());
                genreStatement.addBatch();
            }
            genreStatement.executeUpdate();

            // Insert into Novel_Theme table
            for (Theme theme : themes) {
                themeStatement.setString(1, novel.getNovelId());
                themeStatement.setString(2, theme.getTypeId());
                themeStatement.addBatch();
            }
            themeStatement.executeUpdate();
            for (Author author : authors) {
                authorStatement.setString(1, novel.getNovelId());
                authorStatement.setString(2, author.getAuthorId());
                authorStatement.addBatch();
            }
            authorStatement.executeUpdate();

        }
    }

    public static IllustrationList getIllustrationListByNovel(Connection con, Novel novel) throws SQLException {
        IllustrationList illustrations = new IllustrationList();
        String query = "SELECT i.* FROM Illustration i "
                + "JOIN NovelIllustration ni ON i.IllustrationID = ni.IllustrationID "
                + "WHERE ni.NovelID = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, novel.getNovelId());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String illustrationID = rs.getString("IllustrationID");
                    String illustrationLink = rs.getString("IllustrationLink");
                    Illustration illustration = new Illustration(illustrationID, illustrationLink);
                    illustrations.addIllustration(novel, illustration);
                }
            }
        }
        return illustrations;
    }

    public static void addFollowListByNovelIdAndCusId(Connection con, String customerId, String novelId) {
        try {
            String query = "INSERT INTO FollowList (CustomerID, NovelID) VALUES (?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, customerId);
            statement.setString(2, novelId);
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle any potential SQL exceptions
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteFollowListByNovelIdAndCusId(Connection con, String customerId, String novelId) {
        // Implement logic to delete record from the FollowList table in the database
        try {

            try (con) {
                String query = "DELETE FROM FollowList WHERE CustomerID = ? AND NovelID = ?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, customerId);
                statement.setString(2, novelId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            // Handle any potential SQL exceptions

        }
    }

   public static ChapterList getChapterFromNovel(Connection con, Novel novel) throws SQLException {
    ChapterList chapterList = new ChapterList();
    String query = "SELECT c.* FROM Chapter c "
                 + "JOIN NovelChapter nc ON c.ChapterID = nc.ChapterID "
                 + "WHERE nc.NovelID = ? "
                 + "ORDER BY c.CreationDate ASC"; // Sort by creation date

    try (PreparedStatement pstmt = con.prepareStatement(query)) {
        pstmt.setString(1, novel.getNovelId());
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String chapterId = rs.getString("ChapterID");
                String linkContent = rs.getString("LinkContent");
                Date creationDate = rs.getTimestamp("CreationDate");
                Chapter chapter = new Chapter(chapterId, linkContent,creationDate);
                chapterList.addChapter(novel, chapter);
            }
        }
    }
    return chapterList;
}
    public static ArrayList<Novel> getNovelByGenre(Connection con, Genre genre) throws SQLException {
        ArrayList<Novel> novels = new ArrayList<>();
        String query = "SELECT * FROM Novel_Genre WHERE GenreTypeID = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, genre.getTypeId());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String novelId = rs.getString("NovelID");
                    Novel novel = getNovelByNovelId(con, novelId);
                    if (novel != null) {
                        novels.add(novel);
                    }
                }
            }
        }
        return novels;
    }

    public static ArrayList<Author> getAuthorByNovel(Connection con, Novel novel) throws SQLException {
        ArrayList<Author> authors = new ArrayList<>();
        String query = "SELECT a.* FROM Author a "
                + "Inner JOIN AuthorList na ON a.ID = na.AuthorID "
                + "WHERE na.NovelID = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, novel.getNovelId());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Author author = new Author();
                    author.setAuthorId(rs.getString("ID"));
                    author.setAuthorName(rs.getString("Name"));
                    author.setAuthorDescription(rs.getString("Description"));
                    authors.add(author);
                }
            }
        }
        return authors; // If no author is associated with the given novel
    }

    public static ArrayList<Format> getFormatsByNovelId(Connection con, Novel novel) throws SQLException {
        ArrayList<Format> formats = new ArrayList<>();
        String query = "SELECT f.* FROM NovelFormat f "
                + "JOIN Novel_Format nf ON f.TypeID = nf.FormatTypeID "
                + "WHERE nf.NovelID = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, novel.getNovelId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Format format = new Format();
                    format.setTypeId(rs.getString("TypeID"));
                    format.setTypeOfFormat(rs.getString("TypeOfFormat"));
                    formats.add(format);
                }
            }
        }
        return formats;
    }

    public static ArrayList<Theme> getThemesByNovelId(Connection con, Novel novel) throws SQLException {
        ArrayList<Theme> themes = new ArrayList<>();
        String query = "SELECT t.* FROM NovelTheme t "
                + "JOIN Novel_Theme nt ON t.TypeID = nt.ThemeTypeID "
                + "WHERE nt.NovelID = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, novel.getNovelId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Theme theme = new Theme();
                    theme.setTypeId(rs.getString("TypeID"));
                    theme.setTypeOfTheme(rs.getString("TypeOfTheme"));
                    themes.add(theme);
                }
            }
        }
        return themes;
    }

    public static ArrayList<Genre> getGenresByNovelId(Connection con, Novel novel) throws SQLException {
        ArrayList<Genre> genres = new ArrayList<>();
        String query = "SELECT g.* FROM NovelGenre g "
                + "JOIN Novel_Genre ng ON g.TypeID = ng.GenreTypeID "
                + "WHERE ng.NovelID = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, novel.getNovelId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Genre genre = new Genre();
                    genre.setTypeId(rs.getString("TypeID"));
                    genre.setTypeOfGenre(rs.getString("TypeOfGenre"));
                    genres.add(genre);
                }
            }
        }
        return genres;
    }
    public static void addThemeGenreFormatAuthor(Connection con, Novel novel, ArrayList<Genre> genres, ArrayList<Theme> themes, ArrayList<Author> authors, ArrayList<Format> formats) throws SQLException {
    // First, add genres to the database
    for (Genre genre : genres) {
        String insertGenreQuery = "INSERT INTO Novel_Genre (NovelID, GenreTypeID) VALUES (?, ?)";
        try (PreparedStatement insertGenreStmt = con.prepareStatement(insertGenreQuery)) {
            insertGenreStmt.setString(1, novel.getNovelId());
            insertGenreStmt.setString(2, genre.getTypeId());
            insertGenreStmt.executeUpdate();
        }
    }

    // Then, add themes to the database
    for (Theme theme : themes) {
        String insertThemeQuery = "INSERT INTO Novel_Theme (NovelID, ThemeTypeID) VALUES (?, ?)";
        try (PreparedStatement insertThemeStmt = con.prepareStatement(insertThemeQuery)) {
            insertThemeStmt.setString(1, novel.getNovelId());
            insertThemeStmt.setString(2, theme.getTypeId());
            insertThemeStmt.executeUpdate();
        }
    }

    // Next, add authors to the database
    for (Author author : authors) {
        String insertAuthorQuery = "INSERT INTO AuthorList (NovelID, AuthorID) VALUES (?, ?)";
        try (PreparedStatement insertAuthorStmt = con.prepareStatement(insertAuthorQuery)) {
            insertAuthorStmt.setString(1, novel.getNovelId());
            insertAuthorStmt.setString(2, author.getAuthorId());
            insertAuthorStmt.executeUpdate();
        }
    }

    // Finally, add formats to the database
    for (Format format : formats) {
        String insertFormatQuery = "INSERT INTO Novel_Format (NovelID, FormatTypeID) VALUES (?, ?)";
        try (PreparedStatement insertFormatStmt = con.prepareStatement(insertFormatQuery)) {
            insertFormatStmt.setString(1, novel.getNovelId());
            insertFormatStmt.setString(2, format.getTypeId());
            insertFormatStmt.executeUpdate();
        }
    }
}
public static void deleteThemeGenreFormatAuthor(Connection con, Novel novel, ArrayList<Genre> genres, ArrayList<Theme> themes, ArrayList<Author> authors, ArrayList<Format> formats) throws SQLException {
    // First, delete genres associated with the novel
    for (Genre genre : genres) {
        String deleteGenreQuery = "DELETE FROM Novel_Genre WHERE NovelID = ? AND GenreTypeID = ?";
        try (PreparedStatement deleteGenreStmt = con.prepareStatement(deleteGenreQuery)) {
            deleteGenreStmt.setString(1, novel.getNovelId());
            deleteGenreStmt.setString(2, genre.getTypeId());
            deleteGenreStmt.executeUpdate();
        }
    }

    // Then, delete themes associated with the novel
    for (Theme theme : themes) {
        String deleteThemeQuery = "DELETE FROM Novel_Theme WHERE NovelID = ? AND ThemeTypeID = ?";
        try (PreparedStatement deleteThemeStmt = con.prepareStatement(deleteThemeQuery)) {
            deleteThemeStmt.setString(1, novel.getNovelId());
            deleteThemeStmt.setString(2, theme.getTypeId());
            deleteThemeStmt.executeUpdate();
        }
    }

    // Next, delete authors associated with the novel
    for (Author author : authors) {
        String deleteAuthorQuery = "DELETE FROM AuthorList WHERE NovelID = ? AND AuthorID = ?";
        try (PreparedStatement deleteAuthorStmt = con.prepareStatement(deleteAuthorQuery)) {
            deleteAuthorStmt.setString(1, novel.getNovelId());
            deleteAuthorStmt.setString(2, author.getAuthorId());
            deleteAuthorStmt.executeUpdate();
        }
    }

    // Finally, delete formats associated with the novel
    for (Format format : formats) {
        String deleteFormatQuery = "DELETE FROM Novel_Format WHERE NovelID = ? AND FormatTypeID = ?";
        try (PreparedStatement deleteFormatStmt = con.prepareStatement(deleteFormatQuery)) {
            deleteFormatStmt.setString(1, novel.getNovelId());
            deleteFormatStmt.setString(2, format.getTypeId());
            deleteFormatStmt.executeUpdate();
        }
    }
}
    
    public static ArrayList<Novel> getNovelByFormats(Connection con, Format format) throws SQLException {
        ArrayList<Novel> novels = new ArrayList<>();
        String query = "SELECT * FROM Novel_Format WHERE FormatTypeID = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, format.getTypeId());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String novelId = rs.getString("NovelID");
                    Novel novel = getNovelByNovelId(con, novelId);
                    if (novel != null) {
                        novels.add(novel);
                    }
                }
            }
        }
        return novels;
    }

    public static ArrayList<Format> getAllFormats(Connection con) throws SQLException {
        ArrayList<Format> formats = new ArrayList<>();
        String query = "SELECT * FROM NovelFormat";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Assuming the Format class has a constructor that accepts relevant parameters
                    String formatId = rs.getString("TypeID");
                    String formatType = rs.getString("TypeOfFormat");
                    // Assuming you have a constructor in Format class that takes these parameters
                    Format format = new Format(formatId, formatType);
                    formats.add(format);
                }
            }
        }
        return formats;
    }

    public static ArrayList<Genre> getAllGenres(Connection con) throws SQLException {
        ArrayList<Genre> genres = new ArrayList<>();
        String query = "SELECT * FROM NovelGenre";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String genreId = rs.getString("TypeID");
                    String genreType = rs.getString("TypeOfGenre");
                    Genre genre = new Genre(genreId, genreType);
                    genres.add(genre);
                }
            }
        }
        return genres;
    }

    public static ArrayList<Theme> getAllThemes(Connection con) throws SQLException {
        ArrayList<Theme> themes = new ArrayList<>();
        String query = "SELECT * FROM NovelTheme";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String themeId = rs.getString("TypeID");
                    String themeType = rs.getString("TypeOfTheme");
                    Theme theme = new Theme(themeId, themeType);
                    themes.add(theme);
                }
            }
        }
        return themes;
    }

    public static ArrayList<Novel> getNovelByTheme(Connection con, Theme theme) throws SQLException {
        ArrayList<Novel> novels = new ArrayList<>();
        String query = "SELECT * FROM Novel_Theme WHERE ThemeTypeID = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, theme.getTypeId());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String novelId = rs.getString("NovelID");
                    Novel novel = getNovelByNovelId(con, novelId);
                    if (novel != null) {
                        novels.add(novel);
                    }
                }
            }
        }
        return novels;
    }

    public static ArrayList<Novel> getAllNovels(Connection con) throws SQLException {
        ArrayList<Novel> novels = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Novel")) {
            while (rs.next()) {
                Novel novel = new Novel();
                novel.setNovelId(rs.getString("NovelId"));
                novel.setNovelLanguage(rs.getString("NovelLanguage"));
                novel.setNovelName(rs.getString("NovelName"));
                novel.setNovelLength(rs.getInt("NovelLength"));
                novel.setNumberOfChapters(rs.getInt("NumberOfChapter"));
                novel.setProfit(rs.getFloat("Profit"));
                novel.setLicenseProfit(rs.getFloat("LicenseProfit"));
                novel.setNumberRead(rs.getInt("NumberRead"));
                novel.setNumberWatch(rs.getInt("NumberWatch"));
                novel.setNumberFollow(rs.getInt("NumberFollow"));
                novel.setRating(rs.getInt("Rating"));
                novel.setPublicationYear(rs.getDate("PublicationYear"));
                novel.setPublicationStatus(rs.getString("PublicationStatus"));
                novel.setDescription(rs.getString("Description"));
                novels.add(novel);
            }
        }
        return novels;
    }



    public static ArrayList<Admin> getAllAdmins(Connection con) throws SQLException {
        ArrayList<Admin> admins = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Admins")) {
            while (rs.next()) {
                String adminId = rs.getString("AdminId");
                String userId = rs.getString("UserId");
                // Retrieve user data from Users table based on UserId
                Users user = getUserById(con, userId);
                admins.add(new Admin(adminId, user.getUserId(), user.getName(), user.getAge(), user.getGmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getRole()));
            }
        }
        return admins;
    }

    public static ArrayList<Users> getAllUsers(Connection con) throws SQLException {
        ArrayList<Users> users = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Users")) {
            while (rs.next()) {

                String userId = rs.getString("UserId");
                // Retrieve user data from Users table based on UserId
                Users user = getUserById(con, userId);
                users.add(new Users(user.getUserId(), user.getName(), user.getAge(), user.getGmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getRole()));
            }
        }
        return users;
    }

    public static ArrayList<Author> getAllAuthors(Connection con) throws SQLException {
        ArrayList<Author> authors = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Author")) {
            while (rs.next()) {
                Author author = new Author();
                author.setAuthorId(rs.getString("ID"));
                author.setAuthorName(rs.getString("Name"));
                author.setAuthorDescription(rs.getString("Description"));
                authors.add(author);
            }
        }
        return authors;
    }

    public static AuthorList getAuthorListByAuthorId(Connection con, Author author) throws SQLException {
        AuthorList authorList = new AuthorList();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM AuthorList WHERE AuthorID = '" + author.getAuthorId() + "'")) {
            while (rs.next()) {
                String novelId = rs.getString("NovelID");
                Novel novel = getNovelByNovelId(con, novelId);
                if (novel != null) {
                    authorList.addNovel(author, novel);
                }
            }
        }
        return authorList;
    }

    public static Author getAuthorById(Connection con, String authorId) throws Exception {
        Author author = null;
        String query = "SELECT * FROM Author WHERE ID = ?";
        System.out.println("SQL Query: " + query + " (Author ID: " + authorId + ")");
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, authorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    author = new Author();
                    author.setAuthorId(rs.getString("ID"));
                    author.setAuthorName(rs.getString("Name"));
                    author.setAuthorDescription(rs.getString("Description"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly in your application
        }
        return author;
    }public boolean addFormat(Format format) throws Exception {
        String sql = "INSERT INTO NovelFormat (TypeID, TypeOfFormat) VALUES (?, ?)";
        try (Connection conn = getConnectionWithSqlJdbc();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, format.getTypeId());
            preparedStatement.setString(2, format.getTypeOfFormat());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
 public boolean addGenre(Genre genre) throws Exception {
        String sql = "INSERT INTO NovelGenre (TypeID, TypeOfGenre) VALUES (?, ?)";
        try (Connection conn = getConnectionWithSqlJdbc();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, genre.getTypeId());
            preparedStatement.setString(2, genre.getTypeOfGenre());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean addTheme(Theme theme) throws Exception {
        String sql = "INSERT INTO NovelTheme (TypeID, TypeOfTheme) VALUES (?, ?)";
        try (Connection conn = getConnectionWithSqlJdbc();            
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, theme.getTypeId());
            preparedStatement.setString(2, theme.getTypeOfTheme());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean addAuthor(Author author) throws Exception {
        final String INSERT_AUTHOR_SQL = "INSERT INTO [dbo].[Author] ([ID], [Name], [Description]) VALUES (?, ?, ?)";
        try ( Connection con = getConnectionWithSqlJdbc();
             PreparedStatement preparedStatement = con.prepareStatement(INSERT_AUTHOR_SQL)) {
            preparedStatement.setString(1, author.getAuthorId());
            preparedStatement.setString(2, author.getAuthorName());
            preparedStatement.setString(3, author.getAuthorDescription());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            return false;
        }
    }
    public static FollowList getFollowListByCustomerId(Connection con, Customer customer) throws SQLException {
        FollowList followList = new FollowList();

        // SQL query to retrieve FollowList for a specific CustomerId
        String query = "SELECT NovelId FROM FollowList WHERE CustomerId = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, customer.getCustomerId()); // Set CustomerId parameter
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String novelId = rs.getString("NovelId");
                Novel novel = getNovelByNovelId(con, novelId);
                if (novel != null) {
                    followList.addNovel(customer, novel);
                }
            }
        }

        return followList;
    }
public static void addToAddList(Connection con, Admin admin, Novel novel) throws SQLException {
    // SQL query to insert a new row into AddList table
    String query = "INSERT INTO AddList (AdminId, NovelId) VALUES (?, ?)";

    try (PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setString(1, admin.getAdminId()); // Set AdminId parameter
        stmt.setString(2, novel.getNovelId()); // Set NovelId parameter
        stmt.executeUpdate(); // Execute the insert query
    }
}

    public static AddList getAddListByAdminId(Connection con, Admin admin) throws SQLException {
        AddList addList = new AddList();

        // SQL query to retrieve FollowList for a specific CustomerId
        String query = "SELECT NovelId FROM AddList WHERE AdminId = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, admin.getAdminId()); // Set CustomerId parameter
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String novelId = rs.getString("NovelId");
                Novel novel = getNovelByNovelId(con, novelId);
                if (novel != null) {
                    addList.addNovel(admin, novel);
                }
            }
        }

        return addList;
    }

    public static Novel getNovelByNovelId(Connection con, String novelId) throws SQLException {
        Novel novel = null;
        String query = "SELECT * FROM Novel WHERE NovelId = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, novelId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                novel = new Novel();
                novel.setNovelId(rs.getString("NovelId"));
                novel.setNovelLanguage(rs.getString("NovelLanguage"));
                novel.setNovelName(rs.getString("NovelName"));
                novel.setNovelLength(rs.getInt("NovelLength"));
                novel.setNumberOfChapters(rs.getInt("NumberOfChapter"));
                novel.setProfit(rs.getFloat("Profit"));
                novel.setLicenseProfit(rs.getFloat("LicenseProfit"));
                novel.setNumberRead(rs.getInt("NumberRead"));
                novel.setNumberWatch(rs.getInt("NumberWatch"));
                novel.setNumberFollow(rs.getInt("NumberFollow"));
                novel.setRating(rs.getInt("Rating"));
                novel.setPublicationYear(rs.getDate("PublicationYear"));
                novel.setPublicationStatus(rs.getString("PublicationStatus"));
                novel.setDescription(rs.getString("Description"));

            }
        }

        return novel;
    }

    public static ArrayList<Customer> getAllCustomers(Connection con) throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Customers")) {
            while (rs.next()) {
                String customerId = rs.getString("CustomerId");
                String userId = rs.getString("UserId");
                // Retrieve user data from Users table based on UserId
                Users user = getUserById(con, userId);
                customers.add(new Customer(customerId, user.getUserId(), user.getName(), user.getAge(), user.getGmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getRole()));
            }
        }
        return customers;
    }

    public static Customer getCustomerByUserId(Connection con, String userId) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM Customers WHERE UserId = ?")) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String customerId = rs.getString("CustomerId");
                    Users user = getUserById(con, userId);
                    return new Customer(customerId, user.getUserId(), user.getName(), user.getAge(), user.getGmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getRole());
                }
            }
        }
        return null;
    }

    public static Admin getAdminByUserId(Connection con, String userId) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM Admins WHERE UserId = ?")) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String AdminId = rs.getString("AdminID");
                    Users user = getUserById(con, userId);
                    return new Admin(AdminId, user.getUserId(), user.getName(), user.getAge(), user.getGmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getRole());
                }
            }
        }
        return null;
    }

    public static Users getUserById(Connection con, String userId) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM Users WHERE UserId = ?")) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Users(rs.getString("UserId"), rs.getString("Name"), rs.getInt("Age"), rs.getString("Gmail"), rs.getString("Password"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Role"));
                }
            }
        }
        return null;
    }

   public static void main(String[] args) throws Exception {
     java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc();
    List<Novel> novels = Java_JDBC.getAllNovels(con);
    for (Novel n : novels ){
        System.out.println(n);
    }
    }

}
