package zip;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

public class TextUrl {

	public static void main(String[] args) {
		File file = new File("F:\\aaa.txt");
		try {   
			// String string = Base64.getEncoder().encodeToString(file2byte(file));
			String string = file2byte(file);
			byte[] decode = Base64.getDecoder().decode(string);
			for (int i = 0; i < decode.length; i++) {
				if (decode[i] < 0) {
					decode[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(new File("F:\\aa.zip"));
			out.write(decode);
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	private static String file2byte(File file) throws Exception {
		InputStream inputStream = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		return baos.toString();
	}

}
