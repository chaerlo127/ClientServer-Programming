/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

// pipe는 자바 파일에서 가지고 있지만, filter는 새롭게 만들어 줘야 한다. 
public abstract class CommonFilterImpl implements CommonFilter {
	protected PipedInputStream in = new PipedInputStream();
	protected PipedOutputStream out = new PipedOutputStream();

	public void connectOutputTo(CommonFilter nextFilter) throws IOException {
		out.connect(nextFilter.getPipedInputStream());
	}
	public void connectInputTo(CommonFilter previousFilter) throws IOException {
		in.connect(previousFilter.getPipedOutputStream());
	}
	public PipedInputStream getPipedInputStream() {
		return in;
	}
	public PipedOutputStream getPipedOutputStream() {
		return out;
	}
	
	abstract public boolean specificComputationForFilter() throws IOException;
	// Implementation defined in Runnable interface for thread
	public void run() {
		try {
			specificComputationForFilter();
		} catch (IOException e) {
			if (e instanceof EOFException) return;
			else System.out.println(e);
		} finally {
			closePorts();
		}
	}
	private void closePorts() {
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
