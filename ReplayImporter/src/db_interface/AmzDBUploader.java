package db_interface;

public class AmzDBUploader {
	public static void main(String args[]){
		DBManager.INSTANCE.importReplyData();
	}
}
