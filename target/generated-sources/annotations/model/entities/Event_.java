package model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entities.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-20T05:56:14")
@StaticMetamodel(Event.class)
public class Event_ { 

    public static volatile SingularAttribute<Event, Date> date;
    public static volatile SingularAttribute<Event, Integer> idevent;
    public static volatile SingularAttribute<Event, String> type;
    public static volatile SingularAttribute<Event, User> user;

}