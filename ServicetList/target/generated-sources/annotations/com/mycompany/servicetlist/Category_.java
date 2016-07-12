package com.mycompany.servicetlist;

import com.mycompany.servicetlist.Task;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-12T14:14:16")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile SingularAttribute<Category, Integer> idcategory;
    public static volatile SingularAttribute<Category, String> name;
    public static volatile ListAttribute<Category, Task> taskList;

}