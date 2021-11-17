import com.google.gson.Gson;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloworldJson extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置响应的数据格式为JSON
        response.setContentType("application/json;charset=UTF-8");
       	//设置编码格式避免数据乱码
        response.setCharacterEncoding("UTF-8");
        //响应数据流
        PrintWriter out = response.getWriter();
        //谷歌的JSON解析器
        Gson gson = new Gson();
        //定义的一个对象，用于后台数据流动，简单的可以使用Map集合
        Student stu = new Student("dddd");
       	//Java对象转化为JSON数据
        String json = gson.toJson(stu);
        //将数据发往前台
        out.println(json);
        out.flush();
        out.close();
   }
       
}
class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
   
}
