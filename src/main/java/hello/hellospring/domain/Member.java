package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {
    static int i = 0;
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 쿼리에 직접 주입하지않고 자동으로 생성해주는걸 identity전략이라함 오라클은 시퀀스 등등
    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
