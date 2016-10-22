package guice.tomcat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@javax.inject.Singleton
public class MyServlet extends HttpServlet {

    private MyService myService;
    
    @javax.inject.Inject
    public MyServlet(MyService myService) {
		this.myService = myService;
	}

	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MyEntity myEntity = new MyEntity();
        myEntity.setTs(new Date());
        myEntity.setDesc(UUID.randomUUID().toString());
        
        myService.save(myEntity);
        
        List<MyEntity> entities = myService.findAll();

        PrintWriter writer = resp.getWriter();
        writer.write("<html><body><ul>");
        for(MyEntity e : entities) {
            writer.write("<li>");
            writer.write(e.toString());
            writer.write("</li>");
        }
        
        MyEntity e = entities.get(entities.size()-1);
        
        myService.delete(e);
        
        writer.write("</ul></body></html>");
    }
}
