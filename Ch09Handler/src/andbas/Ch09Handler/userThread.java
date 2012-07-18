package andbas.Ch09Handler;

public class userThread extends Thread {
	Ch09Handler activity;
	int what = 1;
	public userThread(Ch09Handler activity){
		this.activity = activity;
	}

	@Override
	public void run() {
		while(true){
			activity.myHandler.sendEmptyMessage((what++)%4);
			try{
				Thread.sleep(2000);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
