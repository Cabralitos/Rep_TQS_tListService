package com.mycompany.servicetlist;

import com.mycompany.servicetlist.Task;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-12T14:14:16")
@StaticMetamodel(Priority.class)
public class Priority_ { 

    public static volatile SingularAttribute<Priority, Integer> idpriority;
    public static volatile SingularAttribute<Priority, String> name;
    public static volatile SingularAttribute<Priority, Integer> value;
    public static volatile ListAttribute<Priority, Task> taskList;

}