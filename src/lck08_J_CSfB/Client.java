package lck08_J_CSfB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class Client {
	public void doSomething(File file) {
		final Lock lock = new ReentrantLock(); 
		InputStream in = null;
		lock.lock();
		try {
			in = new FileInputStream(file);
			//Perform operations on the open file
			} catch (FileNotFoundException fnf) {
				//Forward to handler
				} finally {
					lock.unlock();
					if (in != null) { try {
						in.close();
					} catch (IOException e) {
						//Forward to handler 
						}
					} 
				}
	}
	
	public  void testCase(final File file){
		Thread test = new Thread(new Runnable() {
			public void run() {
				doSomething(file);
				}
			});
			   test.start();
	}
	
	public void main(String[] args) throws IOException { 
		File file1 = new File("a.txt");
		file1.createNewFile();
		File file2 = new File("b.txt");
		file2.createNewFile();
		testCase(file1); // starts thread 1 
		testCase(file2); // starts thread 2
	}
}