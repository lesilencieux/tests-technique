package adeo.leroymerlin.cdp.Band;

import adeo.leroymerlin.cdp.Event.Event;
import adeo.leroymerlin.cdp.Member.Member;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany
    private Set<Member> members;

    @ManyToMany
    @JoinTable(name = "EVENT_BANDS")
    private Set<Event> event;

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
