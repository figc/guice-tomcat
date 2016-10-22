package guice.tomcat;

import java.util.List;

public interface MyService {
    void save(MyEntity e);
    void delete(MyEntity e);
    List<MyEntity> findAll();
    
}
