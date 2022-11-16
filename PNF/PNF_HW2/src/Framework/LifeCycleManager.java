/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import Components.AddFilter.AddFilter;
import Components.Middle.MiddleFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;

public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter studentFilterSource = new SourceFilter("Students.txt");
            CommonFilter outputFilterSink = new SinkFilter("Output.txt");
            CommonFilter middleFilter = new MiddleFilter();
            CommonFilter addFilter = new AddFilter();
            
            studentFilterSource.connectOutputTo(middleFilter);
            middleFilter.connectOutputTo(addFilter);
            addFilter.connectOutputTo(outputFilterSink);
            
            Thread thread1 = new Thread(studentFilterSource);
            Thread thread2 = new Thread(outputFilterSink);
            Thread thread3 = new Thread(middleFilter);
            Thread thread4 = new Thread(addFilter);
            
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
