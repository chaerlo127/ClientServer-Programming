/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import Components.Middle.MiddleFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;

public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter studentFilterSource = new SourceFilter("Students.txt");
            CommonFilter outputFilterSink = new SinkFilter("Output.txt");
            CommonFilter middleFilter = new MiddleFilter();
            
            studentFilterSource.connectOutputTo(middleFilter);
            middleFilter.connectOutputTo(outputFilterSink);
            
            Thread thread1 = new Thread(studentFilterSource);
            Thread thread2 = new Thread(outputFilterSink);
            Thread thread3 = new Thread(middleFilter);
            
            thread1.start();
            thread2.start();
            thread3.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
