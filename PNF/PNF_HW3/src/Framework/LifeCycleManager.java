/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import Components.AddfilterStudentNo.AddFilterStudentNo;
import Components.DeleteCourseFilter.DeleteCourseFilter;
import Components.Middle.CheckMajorFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;

public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter studentFilterSource = new SourceFilter("Students.txt");
            CommonFilter outputFilterSink = new SinkFilter("Output.txt");
            CommonFilter checkMajorFilter = new CheckMajorFilter("CS");
            CommonFilter addFilter2 = new AddFilterStudentNo("2013");
            CommonFilter addFilter = new DeleteCourseFilter("17651", "17652");
            
            
            studentFilterSource.connectOutputTo(checkMajorFilter);
            checkMajorFilter.connectOutputTo(addFilter2);
            addFilter2.connectOutputTo(addFilter);
            addFilter.connectOutputTo(outputFilterSink);
            
            Thread thread1 = new Thread(studentFilterSource);
            Thread thread2 = new Thread(outputFilterSink);
            Thread thread3 = new Thread(checkMajorFilter);
            Thread thread4 = new Thread(addFilter);
            Thread thread5 = new Thread(addFilter2);
            
            thread1.start();
            thread2.start();
            thread3.start();  
            thread4.start();
            thread5.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
