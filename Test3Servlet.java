import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/test3Servlet")
public class Test3Servlet extends HttpServlet {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://47.101.196.82:3306/linux_final";
    static final String USER = "root";
    static final String PASS = "zzw171413";

    static Connection conn = null;
    static Jedis jedis = null;
    public void init() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            jedis = new Jedis("47.101.196.82");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String name = jedis.get("name");
        if (name == null){
            Student6 stu = getStudent();
            jedis.set("name", stu.name);
            out.println("<h1>hello world, " + stu.name + "</h1>");
        }else {
            out.println("<h1>hello world, " + name + "</h1>");
        }

    }

    public Student6 getStudent() {
        Student6 stu = new Student6();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT `id`, `name` FROM `linux_final`.`t_student` WHERE  `id`=1";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                stu.id = rs.getInt("id");
                stu.name = rs.getString("name");
            }
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return stu;

    }
}

class Student6 {

    public String name;
    public int id;

}