package com.mycompany.servicetlist;

import com.mycompany.servicetlist.Category;
import com.mycompany.servicetlist.Member1;
import com.mycompany.servicetlist.Priority;
import com.mycompany.servicetlist.Tag;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-12T14:14:16")
@StaticMetamodel(Task.class)
public class Task_ { 

    public static volatile SingularAttribute<Task, Integer> idtask;
    public static volatile SingularAttribute<Task, Priority> idpriority;
    public static volatile SingularAttribute<Task, Short> complete;
    public static volatile SingularAttribute<Task, String> description;
    public static volatile SingularAttribute<Task, Category> idcategory;
    public static volatile SingularAttribute<Task, Date> duedate;
    public static volatile ListAttribute<Task, Member1> member1List;
    public static volatile ListAttribute<Task, Tag> tagList;

}