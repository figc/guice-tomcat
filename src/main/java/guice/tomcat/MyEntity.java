package guice.tomcat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity

@NamedQueries({
	@NamedQuery(name = "MyEntity.findAll", query = "SELECT e FROM MyEntity e ORDER BY e.id DESC"),
	@NamedQuery(name = "MyEntity.deleteById", query = "DELETE FROM MyEntity e WHERE e.id = :id")
})
public class MyEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts;
    
    @Column(name="description")
    private String desc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }
    
    public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyEntity myEntity = (MyEntity) o;

        return id.equals(myEntity.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "MyEntity{" +
                "id=" + id +
                ", ts=" + ts +
                ", desc=" + desc +
                '}';
    }
}
