package demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author mercy
 * 输入字符串，并输出
 */
public class EchoPlayer {
	public static String echo(String msg){
		return "echo:"+msg;
	}
	public void talk() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String msg=null;
		while((msg=br.readLine())!=null){
			System.out.println(echo(msg));
			if(msg.equals("bye")){
				break;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		new EchoPlayer().talk();
	}

}