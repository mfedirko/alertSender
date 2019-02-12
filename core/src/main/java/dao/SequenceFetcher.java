package dao;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "sequence", sequenceName = "mySequence")
public class SequenceFetcher
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private long                      id;

    public long getId() {
        return id;
    }
}